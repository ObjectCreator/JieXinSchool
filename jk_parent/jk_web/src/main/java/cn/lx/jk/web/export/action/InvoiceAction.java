package cn.lx.jk.web.export.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Invoice;
import cn.lx.jk.domain.PackingList;
import cn.lx.jk.domain.ShippingOrder;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.InvoiceService;
import cn.lx.jk.service.PackingListService;
import cn.lx.jk.service.ShippingOrderService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

/**
 * @Description:	InvoiceService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 13:51:10
 */

public class InvoiceAction extends BaseAction implements ModelDriven<Invoice> {
	//注入service
	private InvoiceService invoiceService;
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	//装箱单的service
	private PackingListService packingListService;
	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}
	
	//委托单的service				shippingOrderService
	private ShippingOrderService shippingOrderService;
	public void setShippingOrderService(ShippingOrderService shippingOrderService) {
		this.shippingOrderService = shippingOrderService;
	}

	//model驱动
	private Invoice model = new Invoice();
	public Invoice getModel() {
		return this.model;
	}
	


	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	//列表展示
	public String list(){
		String hql = "from Invoice o where state !=-1 order by state";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("invoiceAction_list");		//配置分页按钮的转向链接
		page = invoiceService.findPage(hql, page, Invoice.class, null);
		super.put("page", page);
		
		return "plist";						//page list
	}
	
	//转向新增页面
	public String tocreate(){
		
		//1：查询出状态为 1的委托单委托单放入值栈中
		List<ShippingOrder> shippingOrderList = shippingOrderService.find("from ShippingOrder where state=1", ShippingOrder.class, null);
		super.put("shippingOrderList", shippingOrderList);
		
		//2:跳转页面
		return "pcreate";
	}
	
	//新增保存
	public String insert(){
		//1:设置创建人和创建部门
		User user = super.getCurUser();
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getDept().getId());
	
		//设置 发票单的状态,id——id为委托单的id,模型驱动中已经 接收了

		model.setState(0);
		
		//2:设置装箱单的  发票号 和发票日期
		PackingList packingList = packingListService.get(PackingList.class, model.getId());
	
		
		packingList.setInvoiceNo(model.getScNo());
		packingList.setInvoiceDate(model.getCreateTime());
		//保存装箱单
		packingListService.saveOrUpdate(packingList);
		
		//2:获取选中的委托单   设置委托单的 状态为2
		ShippingOrder shippingOrder = shippingOrderService.get(ShippingOrder.class, model.getId());
		shippingOrder.setState(2);
		//保存委托单
		shippingOrderService.saveOrUpdate(shippingOrder);
		
		//5:保存发票单
		invoiceService.saveOrUpdate(model);
		
		return "alist";			//返回列表，重定向action_list
	}

	
	//转向修改页面
	public String toupdate(){
		//查询出选中的 发票单，放入栈顶
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		super.push(invoice);
		
		return "pupdate";
	}
	
	//修改保存
	public String update(){
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		
		//获取要修改的内容，修改
		invoice.setScNo(model.getScNo());
		invoice.setBlNo(model.getBlNo());
		invoice.setTradeTerms(model.getTradeTerms());
		invoice.setCreateTime(model.getCreateTime());
		//保存
		invoiceService.saveOrUpdate(invoice);
		//跳转页面
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		invoiceService.deleteById(Invoice.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		//1:获得当前所选中的id
		String[] ids = model.getId().split(", ");
		invoiceService.delete(Invoice.class, ids);

		return "alist";
	}
	
	//查看
	public String toview(){
		Invoice obj = invoiceService.get(Invoice.class, model.getId());
		super.push(obj);
		//查询出与当前发票相关联的 委托单
		ShippingOrder shippingOrder = shippingOrderService.get( ShippingOrder.class,model.getId() );
		//放入值栈中
		super.put("shippingOrder", shippingOrder);
		
		return "pview";			//转向查看页面
	}
	
	//提交
	public String tosubmit() throws Exception {
		
		updateState(1);
		
		
		return "alist";
	}

	//取消
	public String tocancel() throws Exception {
		
		updateState(0);
		
		
		return "alist";
	}

	//修改状态方法
	private void updateState(int i) {
		//获取当前选中的发票单
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		//修改当前发票单的状态
		invoice.setState(i);
		//保存发票单
		invoiceService.saveOrUpdate(invoice);
	}
}
