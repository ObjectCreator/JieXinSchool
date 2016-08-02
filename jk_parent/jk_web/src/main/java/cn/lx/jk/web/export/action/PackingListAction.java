package cn.lx.jk.web.export.action;


import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Export;
import cn.lx.jk.domain.PackingList;
import cn.lx.jk.domain.ShippingOrder;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.ExportService;
import cn.lx.jk.service.PackingListService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

/**
 * @Description:	PackingListService接口
 * @Author:			黑马程序员	    无德皇叔
 * @Company:		http://java.lx.cn
 * @CreateDate:		2016-7-15 15:44:05
 */

public class PackingListAction extends BaseAction implements ModelDriven<PackingList> {
	//注入service
	private PackingListService packingListService;
	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}
	
	//model驱动
	private PackingList model = new PackingList();
	public PackingList getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}
	private ExportService exportService;
	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	//列表展示
	public String list(){
		String hql = "from PackingList o where state !=-1 order by state ";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("packingListAction_list");		//配置分页按钮的转向链接
		packingListService.findPage(hql, page, PackingList.class, null);
		super.put("page", page);
		
		return "plist";						//page list
	}
	
	//转向新增页面
	public String tocreate(){

		//准备报运单数据
		List<Export> exportList = exportService.findByCondition("from Export where state = 2");
		//将报运单放到request
		super.put("exportList",exportList);
		return "pcreate";
	}
	
	//新增保存
	public String insert(){
		//设置创建人和创建部门
		User user = super.getCurUser();
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getDept().getId());
		model.setCreateTime(new Date());
		packingListService.saveOrUpdate(model);
		
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
		//准备数据
	//	List<PackingList> packingListList = packingListService.packingListList();
	//	super.put("packingListList", packingListList);		//页面就可以访问packingListList
				
		//准备修改的数据
		PackingList packingList = packingListService.get(PackingList.class, model.getId());
		super.push(packingList);
		
		return "pupdate";
	}
	
	//修改保存
	public String update(){
		PackingList packingList = packingListService.get(PackingList.class, model.getId());
		
		//设置修改的属性，根据业务去掉自动生成多余的属性

		packingList.setSeller(model.getSeller());
		packingList.setBuyer(model.getBuyer());
		packingList.setInvoiceNo(model.getInvoiceNo());
		packingList.setInvoiceDate(model.getInvoiceDate());
		packingList.setMarks(model.getMarks());
		packingList.setDescriptions(model.getDescriptions());
	/*	packingList.setExportIds(model.getExportIds());
		packingList.setExportNos(model.getExportNos());*/
	/*	packingList.setState(model.getState());
		packingList.setCreateBy(model.getCreateBy());
		packingList.setCreateDept(model.getCreateDept());
		packingList.setCreateTime(model.getCreateTime());*/
		
		packingListService.saveOrUpdate(packingList);
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		packingListService.deleteById(PackingList.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		packingListService.delete(PackingList.class, model.getId().split(", "));
		
		return "alist";
	}
	
	//查看
	public String toview(){
		PackingList obj = packingListService.get(PackingList.class, model.getId());
		super.push(obj);
		
		return "pview";			//转向查看页面
	}
	
	
	public String submit() throws Exception {
		// TODO Auto-generated method stub
		changeState(1);
		return "alist";
	}
	public String cancel() throws Exception {
		changeState(0);
		return "alist";
	}
	private void changeState(int i) {
		//根据id查询委托单
		String[] ids = model.getId().split(", ");
		if(ids != null && ids.length>0){
			for(String id :ids){
				model = packingListService.get(PackingList.class, id);
				model.setState(i);
				packingListService.saveOrUpdate(model);
			}
		}

	}
}
