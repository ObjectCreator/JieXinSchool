package cn.lx.jk.web.sysadmin.action;


import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Dept;
import cn.lx.jk.domain.Manager;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.DeptService;
import cn.lx.jk.service.ManagerService;
import cn.lx.jk.service.UserService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

public class DeptAction extends BaseAction implements ModelDriven<Dept>{
	//使用模型驱动，封装部门信息
	private Dept dept = new Dept();
	
	@Override
	public Dept getModel() {
		// TODO Auto-generated method stub
		return dept;
	}
	//管理表
	private Manager manager;
	
	public Manager getManager() {
		return manager;
	}


	public void setManager(Manager manager) {
		this.manager = manager;
	}
	private ManagerService managerService;
	

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	private DeptService deptService;

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private Page page = new Page();
	
	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	/**查询数据库，显示所有部门部门信息，并进行显示
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {                    
		//查询所有部门并进行显示
		//分页查询
		deptService.fingByPage("from Dept where state =1 ",page);
		
		//request.put("results", results);
		//设置分页访问路径，使用相对路径的方式
		page.setUrl("deptAction_list");
		
		//将page放到栈顶
		//ActionContext.getContext().getValueStack().push(page);方法已经进行提取
		super.push(page);
		return "list";
	}
	
	
	/**显示新增页面，同时查询数据库，显示已经存在的部门信息
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		//查询所有部门信息
		List<Dept> deptList= deptService.findAll();
		//ServletActionContext.getRequest().setAttribute("deptList", deptList);
		//查询所有的用户
		List<User> userList = userService.findAll();
		request.put("deptList", deptList);
		request.put("userList", userList);
		return "addUI";
	}
	
	
	/**插入部门信息到数据库
	 * @return
	 * @throws Exception
	 */
	public String insert()  throws Exception{
		User user = super.getCurUser();
		user.setCreateTime(new Date());
		user.setCreateBy(user.getId());
		user.setCreateDept(user.getDept().getId());
		dept.setState(1);
		if(manager ==null){
			deptService.save(dept);
		}else{
			deptService.save(dept,manager);
		}
		
		return "relist";
		
	}
	
	/**显示编辑部门信息页面
	 * @return
	 * @throws Exception
	 */
	public String updateUI() throws Exception {
		// 查询所有客户信息
		List<Dept> deptList= deptService.findAll();
		
		
		//根据id查询部门信息
		dept = deptService.findById(dept.getId());
		//解决自己是自己父类的问题,
		deptList.remove(dept);
		ActionContext.getContext().getValueStack().push(dept);;
		//查询所有的用户
		List<User> userList = userService.findAll();
		
		request.put("userList", userList);
		//回显管理领导
		List<Manager> list = managerService.findByCondition("from Manager where typeId=?",dept.getId());
		if(list !=null && list.size()>0){
			manager = list.get(0);
		}
		request.put("deptList", deptList);
		return "editUI";
	}
	
	/**更新部门信息的方法
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		User user = super.getCurUser();
		//根据数据库查询部门信息
		Dept myDept = deptService.findById(dept.getId());
		if(myDept != null){
			
			myDept.setParent(dept.getParent());
			myDept.setDeptName(dept.getDeptName());
		}
		//更新信息
		if(manager == null){
			deptService.update(myDept);
		}else{
			Manager myManager = managerService.get(manager.getId());
			if(myManager != null){
				myManager.setUserId(manager.getUserId());
				deptService.update(myDept,myManager);
			}else{
				deptService.update(myDept);
				myManager = new Manager();
				myManager.setType("部门");
				myManager.setTypeId(dept.getId());
				myManager.setUserId(manager.getUserId());
				managerService.save(myManager);
			}
			
			
			
		}
		
		return "relist";
	}
	
	/**删除部门信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
	
		//String[] ids = (String[]) request.get("id");
	//	String[] ids = ServletActionContext.getRequest().getParameterValues("id");
		//
		String id = dept.getId();
		String[] ids = id.split(", ");
		//如果选中需要删除的项，就执行删除
		if(ids != null){
			deptService.deleteByIds(ids);
		}
		return "relist";
	}
	
	/**显示部门信息的详细页面
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		dept = deptService.findById(dept.getId());
		super.push(dept);
		return "view";
	}
}
