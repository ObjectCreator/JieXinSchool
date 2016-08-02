package cn.lx.jk.web.export.action;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.export.webservice.IEpService;
import cn.lx.jk.domain.Export;
import cn.lx.jk.domain.ExportProduct;
import cn.lx.jk.domain.Mr_ExportProduct;
import cn.lx.jk.service.ContractService;
import cn.lx.jk.service.ExportProductService;
import cn.lx.jk.service.ExportService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;



public class ExportAction extends BaseAction implements ModelDriven<Export> {
	private Export model = new Export();
	
	public Export getModel() {
		return model;
	}
	private IEpService exportClient;
	
	public void setExportClient(IEpService exportClient) {
		this.exportClient = exportClient;
	}
	//注入ExportService
	private ExportService exportService ;
	private ContractService contractService;
	private ExportProductService exportProductService;
	
	
	public void setExportProductService(ExportProductService exportProductService) {
		this.exportProductService = exportProductService;
	}
	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	//分页组件
	private Page page = new Page();
	public Page getPage() {
		
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	//用于接收修改页面传递来的数据
	private Mr_ExportProduct mr_Exportproduct;
	
	public Mr_ExportProduct getMr_Exportproduct() {
		return mr_Exportproduct;
	}
	public void setMr_Exportproduct(Mr_ExportProduct mr_Exportproduct) {
		this.mr_Exportproduct = mr_Exportproduct;
	}
	/**
	 * 分页查询所有报运单
	 */
	
	public String list() throws Exception {
	
		exportService.findExportByPage("from Export order by state", page);
		page.setUrl("exportAction_list");
		super.push(page);
		
		return "list";
	}
	
	/**显示所有购销合同
	 * @return
	 * @throws Exception
	 */
	public String contractList() throws Exception {
		//查询所有的已提交的合同
		contractService.findContractByPage("from Contract where state = 1", page);
		page.setUrl("exportAction_contractList");
		super.push(page);
		return "contractList";
	}
	
	/**
	 * 查看详情
	 * <input type="checkbox" name="id" value="100"/>
	 * model对象：  Export类型
	 *      id  ：100
	 */
	public String toview() throws Exception {
		model = exportService.findExportById(model.getId());
		
		super.push(model);
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
		exportService.saveOrUpdate(model);
		return contractList();
	}
	
	/**
	 *  进入更新页面
	 */
	public String toupdate() throws Exception {
		
		//根据Id查询报运单
		model = exportService.findExportById(model.getId());
		//push到栈顶
		super.push(model);
		return "toupdate";
	}

	/**更新报运单的方法，主要是更新报运单信息以及更新报运货物的信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		//报运货物的id
		String[] ids = mr_Exportproduct.getMr_id().split(", ");
		//报运货物的cnumber
		Integer[] mr_cnumbers = mr_Exportproduct.getMr_cnumber();
		String[] mr_changeds = mr_Exportproduct.getMr_changed();
		//报运货物的exPrice
		Double[] mr_exPrices = mr_Exportproduct.getMr_exPrice();
		//报运货物的grossWeight
		Double[] mr_grossWeights = mr_Exportproduct.getMr_grossWeight();
		//报运货物的mr_netWeight
		Double[] mr_netWeights = mr_Exportproduct.getMr_netWeight();
		
	
		//报运货物的sizeHeight
		Double[] mr_sizeHeights = mr_Exportproduct.getMr_sizeHeight();
		//报运货物的izeLength
		Double[] mr_sizeLengths = mr_Exportproduct.getMr_sizeLength();
		//报运货物的sizeWidth
		Double[] mr_sizeWidths = mr_Exportproduct.getMr_sizeWidth();
		//报运货物的id
		Double[] mr_taxs = mr_Exportproduct.getMr_tax();
	
		//获得报运单
		Export export = exportService.findExportById(model.getId());
		if(export != null){
			//创建集合用于存储报运货物
			Set<ExportProduct> exportProducts = new HashSet<ExportProduct>();
			export.setCreateTime(model.getCreateTime());
			export.setLcno(model.getLcno());
	        export.setConsignee(model.getConsignee());
	        export.setShipmentPort(model.getShipmentPort());
	        export.setDestinationPort(model.getDestinationPort());
	        export.setTransportMode(model.getTransportMode());
	        export.setPriceCondition(model.getPriceCondition());
	        export.setMarks(model.getMarks());
	        export.setRemark(model.getRemark()); 
	        
	        //更新每一个报运货物信息
	        for(int i = 0;i<ids.length;i++){
	        	//判断，内容进行了修改则更新数据  修改   mr_changerd=1;
	        
	        	if("1".equals(mr_changeds[i])){
	        		ExportProduct ep = exportProductService.findExportProductById(ids[i]);
		        	//更新报运货物的属性
		        	ep.setCnumber(mr_cnumbers[i]);
		        	ep.setExPrice(mr_exPrices[i]);
		        	ep.setGrossWeight(mr_grossWeights[i]);
		        	ep.setNetWeight(mr_netWeights[i]);
		        	ep.setSizeHeight(mr_sizeHeights[i]);
		    
		    		ep.setSizeLength(mr_sizeLengths[i]);
		    		ep.setSizeWidth(mr_sizeWidths[i]);
		    		ep.setTax(mr_taxs[i]);
		    		exportProducts.add(ep);
	        	}
	        	
	        }
		}
		//进行更新操作
		exportService.saveOrUpdate(export);
		return list();
	}
	
	
	public String delete() throws Exception {
		//1.获取删除记录的id集合
		String ids[] = model.getId().split(", ");
		
		//2.调用业务方法，删除记录
		exportService.delete(ids);
		
		return list();
	}

	/**提交报运单
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		// TODO Auto-generated method stub
		changeState(1);
		return list();
	}
	
	/**取消报运单提交
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		changeState(0);
		return list();
	}
	/**用于修改报运单的状态
	 * @param i
	 */
	private void changeState(int i) {
		// TODO Auto-generated method stub
		//根据id查询报运单
		String[] ids = model.getId().split(", ");
		if(ids != null && ids.length>0){
			for(String id :ids){
				model = exportService.findExportById(id);
				model.setState(i);
				exportService.saveOrUpdate(model);
			}
		}

	}

	/**用于显示货物信息
	 * @return
	 * @throws Exception
	 */
	public String ajax() throws Exception {
		/**data[i].id, data[i].productNo, data[i].cnumber,
	data[i].grossWeight, data[i].netWeight,data[i].sizeLength, 
	data[i].sizeHeight,data[i].sizeWidth, data[i].exPrice, data[i].tax)
		 * */
		//查询当前报运单下面的所有货物
		List<ExportProduct> list = exportProductService.findByCondition("from ExportProduct where export.id=?",model.getId());
		//定义list集合用于存储每一个json对象
		List<Map<String,Object>> jsonList  = new ArrayList<Map<String,Object>>();
		//遍历所有报运货物
		for(ExportProduct ep :list){
			//定义map用于拼接json对象
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", check(ep.getId()));
			map.put("productNo", check(ep.getProductNo()));
			map.put("cnumber", check(ep.getCnumber()));
			map.put("grossWeight", check(ep.getGrossWeight()));
			map.put("netWeight", check(ep.getNetWeight()));
			map.put("sizeLength", check(ep.getSizeLength()));
			map.put("sizeHeight", check(ep.getSizeHeight()));
			map.put("sizeWidth", check(ep.getSizeWidth()));
			map.put("exPrice", check(ep.getExPrice()));
			map.put("tax", check(ep.getTax()));
			jsonList.add(map);
			
		}
		
		//转化为json响应给浏览器
		ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(jsonList));
		return	NONE;
	}
	/**
	 * 如果为空，返回""
	 */
	public Object check(Object obj) {
		if(obj == null){
			return "";
		}else{
			return obj;
		}
	}
	
	public String export() throws Exception {
		//int i=10/0;
		
		model = exportService.findExportById(model.getId());
		//查询对应的报运单
		if(model != null){
			//设置报运单的状态,已上报
			model.setState(2);
			/*将报运单转换为json数据
			 * */
			String exportStr = JSON.toJSONString(model);
			/*接收返回的json数据
			 * */
			String exportJson = exportClient.exportE(exportStr);	
			Export export = JSON.parseObject(exportJson,Export.class);
			//表示报运成功
			if(export.getState() ==2){
				System.out.println("报运成功");
				model.setState(2);
				
				//更新货物记录
				for(ExportProduct ep :model.getExportProducts()){
					for(ExportProduct epe :export.getExportProducts()){
						if(ep.getId().equals(epe.getId())){
							ep.setTax(epe.getTax());
						}
					}
				}
				exportService.saveOrUpdate(model);
			}else if(export.getState()==3){
				System.out.println("报运失败");
				System.out.println("失败原因："+export.getRemark());
			}	
		}	
		return NONE;
	}
	
}
