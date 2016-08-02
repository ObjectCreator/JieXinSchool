package cn.lx.jk.web.cargo.action;

import java.util.List;


import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Factory;
import cn.lx.jk.service.FactoryService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;


public class FactoryAction extends BaseAction implements ModelDriven<Factory> {
	private Factory model = new Factory();	
	public Factory getModel() {
		return model;
	}
	
	//注入FactoryService
	private FactoryService factoryService ;
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
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
		factoryService.findFactoryByPage("from Factory where state!=3 order by state", page);//分页查询,1为可使用的工厂
		
		//设置分页组件的url		
		page.setUrl("factoryAction_list");//相对定位
		
		//将page组件手动放入值栈
		super.put("page",page);
		return "list";
	}
	
	
	/**
	 * 查看详情
	 * <input type="checkbox" name="id" value="100"/>
	 * model对象：  Factory类型      
	 */
	public String toview() throws Exception {
		//1.根据id,得到对象
		Factory factory = factoryService.findFactoryById(model.getId());		
		//2.放入栈顶
		super.push(factory);		
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
	 * 保存新增
	 * 
	 */
	public String insert() throws Exception {		
		factoryService.saveOrUpdate(model);
		return list();
	}
	
	/**
	 *  进入更新页面
	 */
	public String toupdate() throws Exception {
		
		//1.加载当前要更新的对象
		Factory factory = factoryService.findFactoryById(model.getId());
		super.push(factory);
		
		return "toupdate";
	}
	
	/**
	 * 更新
	 * @return
	 * @throws Exception
	 *  <input type="hidden" name="id" value=""/>
	 */
	public String update() throws Exception {
		//1.根据id,得到要更新的对象
		Factory factory = factoryService.findFactoryById(model.getId());
		BeanUtils.copyProperties(model, factory);
		factoryService.saveOrUpdate(factory);
		return list();
	}
	
	/**
	 * 删除(一个，多个)
	 * <input type="checkbox" name="id" value="4028cdd255a3f7030155a3f835fd0000"/>
	 * <input type="checkbox" name="id" value="4028cdd955a079b60155a07cc6dc0000"/>
	 * 
	 *   如：客户端给id传了三个值，   id=3   id=4    id=5------------>服务器只能接收最后一个值，就是id=5
	 *   如何解决：  ids=3   ids=4    ids=5   private List<Integer> ids ;//生成它的getter与setter
	 *   
	 */
	public String delete() throws Exception {
		
		//1.获取删除记录的id集合
		String ids[] = model.getId().split(", ");
		
		//2.调用业务方法，删除记录
		factoryService.delete(ids);
		
		return list();
	}
	
}
