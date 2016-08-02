package cn.lx.jk.web.cargo.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.ExtCproduct;
import cn.lx.jk.domain.Factory;
import cn.lx.jk.service.ContractProductService;
import cn.lx.jk.service.ExtCproductService;
import cn.lx.jk.service.FactoryService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;



public class ExtCproductAction extends BaseAction implements ModelDriven<ExtCproduct> {
	private ExtCproduct model = new ExtCproduct();
	
	public ExtCproduct getModel() {
		return model;
	}
	
	//注入ExtCproductService
	private ExtCproductService ExtCproductService ;
	public void setExtCproductService(ExtCproductService ExtCproductService) {
		this.ExtCproductService = ExtCproductService;
	}
	private FactoryService factoryService;
	
	
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
	private ContractProductService contractProductService;
	
	

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
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
	 * 查看详情
	 * <input type="checkbox" name="id" value="100"/>
	 * model对象：  ExtCproduct类型
	 *      id  ：100
	 */
	public String toview() throws Exception {
		//1.根据id,得到对象
		ExtCproduct ExtCproduct = ExtCproductService.findExtCproductById(model.getId());
		
		//2.放入栈顶
		super.push(ExtCproduct);
		
		//3.跳页面
		return "toview";
	}
	
	/**
	 * 进入新增页面
	 */
	public String tocreate() throws Exception {
		//查询生产附件生产厂家
		List<Factory> factoryList = factoryService.find(" from Factory where state=1 and ctype='附件' ", Factory.class);
		super.put("factoryList", factoryList);
		//查询当前货物的所有附件
		ExtCproductService.findExtCproductByPage("from ExtCproduct where contractProduct.id=? ", page, model.getContractProduct().getId());
		super.push(page);
		return "tocreate";
	}
	
	/**
	 * 保存
	 * 
	 */
	public String insert() throws Exception {
		
		ExtCproductService.saveOrUpdate(model);
		return tocreate();
	}
	
	/**
	 *  进入更新页面
	 */
	public String toupdate() throws Exception {
		
		//查询所有的生产厂家
		List<Factory> factoryList = factoryService.findAll();
		super.put("factoryList", factoryList);
		//查询该附件
		ExtCproduct extCproduct = ExtCproductService.findExtCproductById(model.getId());
		super.push(extCproduct);
	
		return "toupdate";
	}
	
	public String update() throws Exception {
		//1.根据id,得到要更新的对象
		ExtCproduct extCproduct = ExtCproductService.findExtCproductById(model.getId());
		if(extCproduct != null){
			extCproduct.setFactoryName(model.getFactoryName());
			extCproduct.setFactory(model.getFactory());
			extCproduct.setProductNo(model.getProductNo());
			extCproduct.setProductImage(model.getProductImage());
			extCproduct.setCnumber(model.getCnumber());
			extCproduct.setPackingUnit(model.getPackingUnit());
			extCproduct.setPrice(model.getPrice());
			extCproduct.setOrderNo(model.getOrderNo());
			extCproduct.setProductDesc(model.getProductDesc());
			extCproduct.setProductRequest(model.getProductRequest());
			ExtCproductService.saveOrUpdate(extCproduct);
		}
		//只能使用方法，跳转，因为需要传递参数id，此时，如果使用重定向action,值传不进去
		return tocreate();
	}
	
	
	public String delete() throws Exception {
		//根据id进行删除
		ExtCproductService.delete(model);
		
		return tocreate();
	}
	
}
