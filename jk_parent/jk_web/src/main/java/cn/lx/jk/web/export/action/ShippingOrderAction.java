package cn.lx.jk.web.export.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Finance;
import cn.lx.jk.domain.PackingList;
import cn.lx.jk.domain.ShippingOrder;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.PackingListService;
import cn.lx.jk.service.ShippingOrderService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

/**
 * @Description:	ShippingOrderService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 11:37:31
 */

public class ShippingOrderAction extends BaseAction implements ModelDriven<ShippingOrder> {
	//注入ShippingOrderService
	private ShippingOrderService shippingOrderService;
	public void setShippingOrderService(ShippingOrderService shippingOrderService) {
		this.shippingOrderService = shippingOrderService;
	}
	//注入PackingListService
	private PackingListService packingListService;
	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}
	
	//model驱动
	private ShippingOrder model = new ShippingOrder();
	public ShippingOrder getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	/**
	 * 列表展示
	 * @return
	 */
	public String list(){

		String hql = "from ShippingOrder o where state!=-1 order by state ";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("shippingOrderAction_list");		//配置分页按钮的转向链接

		//给页面提供分页数据
		page.setUrl("shippingOrderAction_list");		//配置分页按钮的转向链接
		page = shippingOrderService.findPage(hql, page, ShippingOrder.class, null);
		//3.放入到栈顶
		super.push(page);
		//4.展示
		return "plist";	
	}
	
	/**
	 * 转向新增 页面
	 * @return
	 */
	public String tocreate(){
		//准备数据
		String hql = "from PackingList where state=1 ";
		//获得装箱单集合
		List<PackingList> packingList = packingListService.find(hql, PackingList.class,null);
		
		super.put("packingList", packingList);//页面就可以访问shippingOrderList
		//跳转
		return "pcreate";
	}
	
	/**
	 * 新增保存
	 * @return
	 */
	public String insert(){
		//获得request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		//获得页面所选中单选框的id
		String str = request.getParameter("clId");
		//设置创建人和创建部门
		User user = super.getCurUser();//获得用户对象
		model.setCreateBy(user.getUserName());//创建人
		model.setCreateDept(user.getDept().getDeptName());//所属部门
		model.setCreateTime(new Date());//创建时间
		
		model.setId(str);
		model.setState(0);//草稿
		PackingList pl = packingListService.get(PackingList.class, model.getId());
		
		pl.setState(2);
		shippingOrderService.saveOrUpdate(model);
		
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
				
		//准备修改的数据
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class, model.getId());
		//放入栈顶
		super.push(obj);
		//跳转
		return "pupdate";
	}
	
	/**
	 * 修改保存
	 * @return
	 */
	public String update(){
		//通过id获得托运单对象
		ShippingOrder shippingOrder = shippingOrderService.get(ShippingOrder.class, model.getId());
		
		//设置修改的属性
		shippingOrder.setOrderType(model.getOrderType());//海运/空运
		shippingOrder.setShipper(model.getShipper());//货主
		shippingOrder.setConsignee(model.getConsignee());//提单抬头
		shippingOrder.setNotifyParty(model.getNotifyParty());//正本通知人
		shippingOrder.setLcNo(model.getLcNo());//信用证
		shippingOrder.setPortOfLoading(model.getPortOfLoading());//装运港
		shippingOrder.setPortOfTrans(model.getPortOfTrans());//转船港
		shippingOrder.setPortOfDischarge(model.getPortOfDischarge());//卸货港	
		shippingOrder.setLoadingDate(model.getLoadingDate());//装期
		shippingOrder.setLimitDate(model.getLimitDate());//效期
		shippingOrder.setIsBatch(model.getIsBatch());//是否分批
		shippingOrder.setIsTrans(model.getIsTrans());//是否转船
		shippingOrder.setCopyNum(model.getCopyNum());//份数
		shippingOrder.setRemark(model.getRemark());//扼要说明
		shippingOrder.setSpecialCondition(model.getSpecialCondition());//运输要求
		shippingOrder.setFreight(model.getFreight());//运费说明
		shippingOrder.setCheckBy(model.getCheckBy());//复核人
		shippingOrder.setState(model.getState());//状态
		shippingOrder.setCreateBy(model.getCreateBy());//创建人
		shippingOrder.setCreateDept(model.getCreateDept());//创建部门
		shippingOrder.setCreateTime(model.getCreateTime());//创建日期
		//更新数据
		shippingOrderService.saveOrUpdate(shippingOrder);
		//跳转
		return "alist";
	}
	
	
	/**
	 * 刪除一条
	 * @return
	 */

	
	/**
	 * 删除多条
	 */
	public String delete() {
		shippingOrderService.delete(ShippingOrder.class, model.getId().split(", "));
		return "alist";
	}
	
	/**
	 * 查看
	 * @return
	 */
	public String toview(){
		//通过id获得托运单对象
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class, model.getId());
		//放入栈顶
		super.push(obj);
		//转向查看页面
		return "pview";	
	}
	
	/**提交委托单
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		//修改状态
		changeState(1);
		return list();
	}
	
	/**取消委托单提交
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		//修改状态
		changeState(0);
		return list();
	}
	/**用于修改报运单的状态
	 * @param i
	 */
	private void changeState(int i) {
		//根据id查询委托单
		String[] ids = model.getId().split(", ");//获得托运单id数组
		if(ids != null && ids.length>0){
			//遍历id
			for(String id :ids){
				model = shippingOrderService.get(ShippingOrder.class, id);
				model.setState(i);
				shippingOrderService.saveOrUpdate(model);
			}
		}

	}

	
}
