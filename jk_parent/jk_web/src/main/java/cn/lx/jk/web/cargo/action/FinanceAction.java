package cn.lx.jk.web.cargo.action;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Finance;
import cn.lx.jk.domain.Invoice;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.FinanceService;
import cn.lx.jk.service.InvoiceService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.SysConstant;
import cn.lx.jk.utils.UtilFuns;
import cn.lx.jk.web.action.BaseAction;

/**
 * @Description: FinanceService接口
 * @Author: 麦芽的香气
 * @Company: truest me
 * @CreateDate: 2016-7-16 11:30:03
 */

@SuppressWarnings("serial")
public class FinanceAction extends BaseAction implements ModelDriven<Finance> {
	// 注入service
	private FinanceService financeService;

	private InvoiceService invoiceService;

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

	// model驱动
	private Finance model = new Finance();

	public Finance getModel() {
		return this.model;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page<Finance> page = new Page<Finance>(); // 封装页面的参数，主要当前页参数

	public Page<Finance> getPage() {
		return page;
	}

	public void setPage(Page<Finance> page) {
		this.page = page;
	}

	// 列表展示
	public String list() {
		String hql = "from Finance o  where state <> -1 order by inputDate desc"; // 查询所有内容
		// 给页面提供分页数据
		HttpServletRequest request = ServletActionContext.getRequest();
		String url=this.getUrl(request);
		page.setUrl(url); // 配置分页按钮的转向链接
		page = financeService.findPage(hql, page, Finance.class, null);
		super.put("order0", 0);
		super.push(page);
		
		return "plist"; // page list
	}

	// 转向新增页面
	public String tocreate() {
		// 准备数据
		List<Invoice> list = invoiceService.find("from Invoice where state=?", Invoice.class, new Object[] { 1 });
		super.put("list", list); // 页面就可以访问financeList
		return "pcreate";
	}

	// 新增保存
	public String insert() {
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		model.setCreateBy(user.getId());// 创建人
		model.setCreateTime(new Date());// 创建时间
		model.setCreateDept(user.getDept().getId());// 创建人所在部门
		financeService.save(model);
		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toupdate() {
		// 准备修改的数据
		Finance obj = financeService.get(Finance.class, model.getId());
		super.push(obj);

		return "pupdate";
	}

	// 修改保存
	public String update() {
		Finance finance = financeService.get(Finance.class, model.getId());

		// 设置修改的属性，根据业务去掉自动生成多余的属性
		finance.setInputDate(model.getInputDate());
		finance.setInputBy(model.getInputBy());
		finance.setState(model.getState());
		financeService.saveOrUpdate(finance);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		financeService.deleteById(Finance.class, model.getId());

		return "alist";
	}

	// 删除多条
	public String delete() {
		financeService.delete(Finance.class, model.getId().split(", "));
		return "alist";
	}

	// 查看
	public String toview() {
		Finance obj = financeService.get(Finance.class, model.getId());
		super.push(obj);
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());// 共享主键
		super.put("invoice", invoice);
		return "pview"; // 转向查看页面
	}
	
	
	/**
	 * 获得 url
	 * 
	 * @param request
	 * @return
	 */
	private String getUrl(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		if (queryString != null) {
			if (queryString.contains("page.pageNo=")) {
				int index = queryString.indexOf("page.pageNo=");
				queryString = queryString.substring(0, index);
			}
			return uri +"?"+queryString;
		}
		return uri+"?";
	}
	/**
	 * 创建人搜索
	 * @throws IOException
	 */
	public void findByInput() throws IOException{
		input =model.getInputBy();
		//2 拼凑
		if(input != null){
			String hql="from Finance where  state <> -1 and inputBy like ?";
			List<Finance> find = financeService.find(hql, Finance.class, new Object[]{"%"+input+"%"});
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			String jsonString = JSON.toJSONString(find);
			response.getWriter().write(jsonString);
		}
		
	}
	
	/**
	 * 高级搜索
	 * 
	 * @return
	 */
	public String query() {
		try {
			input = model.getInputBy();
			zt=model.getState();
			StringBuilder sb=new StringBuilder("from Finance where state <> -1  ");
			List<Object> list=new LinkedList<Object>();
 			if (UtilFuns.isNotEmpty(input)) {
 				input = input.trim();
				sb.append(" and inputBy like ?");
				list.add("%" + input + "%");
				super.put("input", input);
			} 
 			if(zt!=null){
				sb.append(" and state =?");
				list.add(zt);
				super.put("zhuangtai", zt);
			}
 			if("date1".equals(order)){
 				sb.append(" order by inputDate asc");
 				super.put("order1", 1);
 			}else {
 				sb.append(" order by inputDate desc");
 				super.put("order0", 0);
 			}
 			financeService.findPage(sb.toString(), page, Finance.class, list.toArray());
 			HttpServletRequest request = ServletActionContext.getRequest();
 			String url=this.getUrl(request);
 			if(!url.endsWith("&")){
 				url+="&";
 			}
 			page.setUrl(url);
 			super.push(page);
			return "plist";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	private String input;
	private Integer zt;
	private String order;
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input = input;
	}

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

}
