 package cn.lx.jk.web.cargo.action;



import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Contract;
import cn.lx.jk.domain.Manager;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.ContractService;
import cn.lx.jk.service.ManagerService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;
import cn.lx.jk.web.export.action.ContractPrint;



public class ContractAction extends BaseAction implements ModelDriven<Contract> {
	private Contract model = new Contract();
	
	public Contract getModel() {
		return model;
	}
	private ContractPrint contractPrint;
	
	public void setContractPrint(ContractPrint contractPrint) {
		this.contractPrint = contractPrint;
	}
	//注入ContractService
	private ContractService contractService ;
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	private ManagerService managerService;
	
	
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	//分页组件
	private Page page = new Page();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
	/**
	 * 分页查询
	 */
	
	public String list() throws Exception {
		String hql = "from Contract  c where 1=1 ";
		//根据用户等级显示不同的信息
		User user = getCurUser();
		//获取用户等级
		Integer degree = user.getUserInfo().getDegree();
		//拼装hql语句，根据用户的不同等级
		/*
		 *  0超级管理员
	        1跨部门跨人员
	        2管理所有下属部门和人员
	        3管理本部门
	        4普通员工
		 * */
		System.out.println(degree);
		if(degree==4){
			//普通员工
			hql+=" and c.createBy='"+user.getId()+"' ";
		}else if(degree==3){
			//部门经理，管理本部门，不包括下属机构
			hql+=" and c.createDept='"+user.getDept().getId()+"' ";
		}else if(degree==2){
			//管理本部门及下属部门   例如本部门 100  下属部门10001 10002
			
			hql+=" and c.createDept like '"+user.getDept().getId()+"%'";
		}else if(degree==1){
			//跨部门跨人员管理，需要利用中间表来实现
			List<Manager> managers = managerService.findByCondition("from Manager where userId=? ",user.getId());
	
			//用于拼装所有的管理用户id
			StringBuilder userIds = new StringBuilder();
			//aa,无效数据，为了使hql语句不报错
			userIds.append("'aa',");
			//用于拼装所有的管理部门id
			StringBuilder deptIds = new StringBuilder();
			//bb,无效数据，为了使hql语句不报错
			deptIds.append("'bb',");
			if(managers != null && managers.size()>0){
				for(Manager manager:managers){
					if("员工".equals(manager.getType())){
						userIds.append("'"+manager.getTypeId()+"',");
					}else{
						deptIds.append("'"+manager.getTypeId()+"',");
					}
				}
			}
			//去掉最后一个","
			if(userIds.length()>0){
				userIds.delete(userIds.length()-1, userIds.length());
			}
			if(deptIds.length()>0){
				deptIds.delete(deptIds.length()-1, deptIds.length());
			}
			hql += " and c.createBy in("+userIds.toString()+")"+" or c.createDept in ("+deptIds.toString()+")";
		}else if(degree==0){
			//管理所有人
			//因为可以查看所有记录，不需要添加条件
		}
		hql+=" order by state ";
		contractService.findContractByPage(hql, page);//分页查询
		//更新购销合同的货物数和附件数
		/*List<Contract> list = page.getResults();
		
		for(Contract contract:list){
			int num = 0;
			contract.setProductNum(contract.getContractProducts().size());
			for(ContractProduct contractProduct:contract.getContractProducts()){
				num+=contractProduct.getExtCproducts().size();
			}
			contract.setExtCproductNum(num);
		}*/
		//设置分页组件的url
		//page.setUrl(ServletActionContext.getRequest().getContextPath()+"/sysadmin/ContractAction_list");//绝对定位
		page.setUrl("contractAction_list");//相对定位
		
		//将page组件手动放入栈顶
		super.push(page);
		return "list";
	}
	
	
	/**
	 * 查看详情
	 * <input type="checkbox" name="id" value="100"/>
	 * model对象：  Contract类型
	 *      id  ：100
	 */
	public String toview() throws Exception {
		//1.根据id,得到对象
		Contract Contract = contractService.findContractById(model.getId());
		
		//2.放入栈顶
		super.push(Contract);
		
		//3.跳页面
		return "toview";
	}
	
	/**
	 * 进入新增页面
	 */
	public String tocreate() throws Exception {

		return "tocreate";
	}
	
	/**
	 * 保存
	 * 
	 */
	public String insert() throws Exception {
		//设置model的创建用户
		User user  = getCurUser();
		
		model.setCreateBy(user.getId());
	
		//设置model的创建部门
		model.setCreateDept(user.getDept().getId());

		model.setExtCproductNum(0);
		model.setProductNum(0);
		contractService.saveOrUpdate(model);
		return list();
	}
	
	/**
	 *  进入更新页面
	 */
	public String toupdate() throws Exception {
		//1.加载当前要更新的对象
		Contract Contract = contractService.findContractById(model.getId());
		super.push(Contract);
		return "toupdate";
	}
	
	/**
	 * 更新
	 * @return
	 * @throws Exception
	 *  <input type="hidden" name="id" value="4028827c4fb645b0014fb64668550000"/>
	 */
	public String update() throws Exception {
		//1.根据id,得到要更新的对象
		Contract contract = contractService.findContractById(model.getId());
		if(contract != null ){
			contract.setCustomName(model.getCustomName());
			contract.setPrintStyle(model.getPrintStyle());;
			contract.setContractNo(model.getContractNo());
			contract.setOfferor(model.getOfferor());
			contract.setInputBy(model.getInputBy());
			contract.setCheckBy(model.getCheckBy());
			contract.setInspector(model.getInspector());
			contract.setSigningDate(model.getSigningDate());
			contract.setImportNum(model.getImportNum());
			contract.setShipTime(model.getShipTime());
			contract.setTradeTerms(model.getTradeTerms());
			contract.setDeliveryPeriod(model.getDeliveryPeriod());
			contract.setCrequest(model.getCrequest());
			contract.setRemark(model.getRemark());
			contractService.saveOrUpdate(contract);
		}
	
		return list();
	}
	
	
	public String delete() throws Exception {
		//1.获取删除记录的id集合
		String ids[] = model.getId().split(", ");
		
		//2.调用业务方法，删除记录
		contractService.delete(ids);
		
		return list();
	}
	
	/**提交合同
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		changeState(1);
		return list();
	}

	
	/**取消提交
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
	
		changeState(0);
		return list();
	}
	/**改变状态的方法
	 * @param state
	 */
	public void changeState(int state){
		System.out.println(1);
		String[] ids = model.getId().split(", ");
		System.out.println(2);
		for(String id :ids){
			Contract contract = contractService.findContractById(id);
			System.out.println(3);
			contract.setState(state);
			contractService.saveOrUpdate(contract);
			System.out.println(4);
		}
	}

	/**打印购销合同，在页面进行处理，保证只能选中一个购销合同进行打印
	 * @throws Exception
	 */
	public void print() throws Exception {
		// TODO Auto-generated method stub
		String[] ids = model.getId().split(", ");
		int i = 0;
		for(String id:ids){
			i++;
			Contract contract = contractService.findContractById(id);
			System.out.println("ccc"+i+contract);
			if(contract != null){
	
				String path = ServletActionContext.getServletContext().getRealPath("/");
				contractPrint.print(contract, path, ServletActionContext.getResponse(),i);
				Thread.sleep(1000*10);
			}
		}
		
	}
}
