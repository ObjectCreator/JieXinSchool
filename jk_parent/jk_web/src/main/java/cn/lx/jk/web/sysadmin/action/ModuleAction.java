package cn.lx.jk.web.sysadmin.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Module;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.ModuleService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

public class ModuleAction extends BaseAction implements ModelDriven<Module> {
	private Module module = new Module();
	@Override
	public Module getModel() {
		// TODO Auto-generated method stub
		return module;
	}
	private Page page = new Page();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	private ModuleService moduleService;
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	/**显示角色管理界面
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		moduleService.findByPage("from Module where state = 1 ",page);
		page.setUrl("moduleAction_list");
		
		ActionContext.getContext().getValueStack().push(page);
		return "list";
	}
	
	/**显示新增角色页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		//查询所有的模块，并进行显示
		List<Module> moduleList = moduleService.findAll();
		
		
		request.put("moduleList", moduleList);
		return "addUI";
	}
	
	/**保存角色信息
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		User user  = super.getCurUser();
		//根据id查询module 父模块
		Module module1 = moduleService.findById(module.getParentId());
		module.setCreateTime(new Date());
		module.setCreateBy(user.getId());
		module.setCreateDept(user.getDept().getId());
		if(module1!=null){
			module.setParentName(module1.getName());
		}

		moduleService.save(module);
		return "relist";
	}

	/**查看角色信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//根据id查询部门信息
		module = moduleService.findById(module.getId());
		//放到栈顶
		super.push(module);
		return "view";
	}
  
	/**显示更新角色页面
	 * @return
	 * @throws Exception
	 */
	public String updateUI() throws Exception {
		// TODO Auto-generated method stub
		//根据id查询部门信息
		module = moduleService.findById(module.getId());
		//查询所有的模块，并进行显示
		List<Module> moduleList = moduleService.findAll();
		request.put("moduleList", moduleList);
		//放到栈顶
		super.push(module);
		return "updateUI";
	}
	
	/**更新角色信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		User user = super.getCurUser();
		//先从数据库进行查询，
		Module myModule = moduleService.findById(module.getId());
		
		if(myModule != null){
			//查询父模块
			Module module1 = moduleService.findById(module.getParentId());
			myModule.setName(module.getName());
			myModule.setLayerNum(module.getLayerNum());
			myModule.setCpermission(module.getCpermission());
			myModule.setCurl(module.getCurl());
			myModule.setCtype(module.getCtype());
			myModule.setState(module.getState());
			myModule.setCwhich(module.getCwhich());
			myModule.setRemark(module.getRemark());
			myModule.setOrderNo(module.getOrderNo());
			myModule.setParentId(module.getParentId());
			myModule.setUpdateBy(user.getId());
			myModule.setUpdateTime(new Date());
			if(module1!=null){
				myModule.setParentName(module1.getName());
			}
			
			moduleService.update(myModule);
		}
		return "relist";
	}
	
	/**删除角色信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		String[] ids = module.getId().split(", ");
		
		 moduleService.delete(ids);
		
		return "relist";
	}
}
