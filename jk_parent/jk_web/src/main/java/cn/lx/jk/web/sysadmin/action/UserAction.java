package cn.lx.jk.web.sysadmin.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Dept;
import cn.lx.jk.domain.Manager;
import cn.lx.jk.domain.Role;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.DeptService;
import cn.lx.jk.service.ManagerService;
import cn.lx.jk.service.RoleService;
import cn.lx.jk.service.UserService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

public class UserAction extends BaseAction implements ModelDriven<User>{
	 private User user  = new User();
	 @Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	private UserService userService;
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	} 
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
	private Page page = new Page();
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	private RoleService roleService;
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**显示用户管理界面
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		userService.findByPage("from User where state = 1 ",page);
		page.setUrl("userAction_list");
		ActionContext.getContext().getValueStack().push(page);
		
	
		return "list";
	}
	
	/**显示新增用户页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		//查询所有部门
		List<Dept> deptList = deptService.findAll();
		request.put("deptList", deptList);
		//查询所有的用户
		List<User> userList = userService.findAll();
		request.put("userList",userList);
		return "addUI";
	}
	
	/**保存用户信息，以及对应的用户扩展信息
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		// TODO Auto-generated method stub
		user.setPassword("123456");
		
		
		if(manager ==null){
			//如果没有管理领导，执行保存用户操作
			userService.save(user);
		}else{
			//否则同时保存管理领导信息
			userService.save(user,manager);
		}
		
		return "relist";
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//根据id查询部门信息
		user = userService.findById(user.getId());
		//放到栈顶
		super.push(user);
		return "view";
	}
  
	/**显示更新用户页面
	 * @return
	 * @throws Exception
	 */
	public String updateUI() throws Exception {
		// TODO Auto-generated method stub
		//根据id查询部门信息
		user = userService.findById(user.getId());
		System.out.println(user.getUserInfo().getBirthday());
		//放到栈顶
		super.push(user);
		//获取所有部门
		List<Dept> deptList = deptService.findAll();
		//获取所有用户
		List<User> userList = userService.findAll();
		userList.remove(user);
		request.put("deptList", deptList);
		request.put("userList", userList);
		//查询该用户对应的管理信息，进行回显
		List<Manager> list = managerService.findByCondition("from Manager where typeId=?",user.getId());
		if(list !=null && list.size()>0){
			manager = list.get(0);
		}
		return "updateUI";
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//先查询数据库中的用户
		User myUser = userService.findById(user.getId());
		//更新指定方法
		if(myUser != null){
			myUser.setUserName(user.getUserName());
			myUser.setState(user.getState());
			myUser.setDept(user.getDept());
			if(manager==null){
				userService.update(myUser);
			}else{
				Manager myManager = managerService.get(manager.getId());
				//如果用户之前没有管理领导，则新建一个管理对象，执行保存操作
				if(myManager == null){
					myManager = new Manager();
					myManager.setType("员工");
					myManager.setTypeId(user.getId());
				}
				myManager.setUserId(manager.getUserId());
				userService.update(myUser,myManager);
			}
			
		}
		return "relist";
	}
	
	public String delete() throws Exception {
		// 删除用户，以及删除对应的用户扩展信息
		String[] ids = user.getId().split(", ");
		
		 userService.delete(ids);
		
		return "relist";
	}
	
	/**显示角色界面
	 * @return
	 * @throws Exception
	 */
	public String roleUI() throws Exception {
		// TODO Auto-generated method stub
		//查询所有角色
		List<Role> roleList = roleService.findAll();
		//查询当前用户
		user = userService.findById(user.getId());
		super.push(user);
		request.put("roleList", roleList);
		return "roleUI";
	}
	/**更新用户的角色信息
	 * @return
	 * @throws Exception
	 */
	public String role() throws Exception {
		//获取所有的role集合
		String[] ids = ServletActionContext.getRequest().getParameterValues("roleId");
		//遍历ids找到所有的role放到一个set集合中
		Set<Role> roles = new HashSet<Role>();
		User myUser = userService.findById(user.getId());
		if(ids !=null && ids.length>0){
			for(String id:ids){
				Role role = roleService.findById(id);
				roles.add(role);
			}
			
			if(myUser != null){
				myUser.setRoles(roles);
				userService.update(myUser);
			}
		}				
			return "relist";
	}

	/**使用异步请求，判断登陆用户名是否存在
	 * @return
	 * @throws Exception
	 */
	public String ajax() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(" text/html;charset=UTF-8");
		System.out.println("userNmae="+user.getUserName());
		user = userService.findByCondition("from User where userName=? ",user.getUserName());
		if(user != null){
			response.getWriter().write("false");
		}else{
			response.getWriter().write("true");
		}
		return NONE;
	}
}
