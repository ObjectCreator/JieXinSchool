package cn.lx.jk.web.sysadmin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.Module;
import cn.lx.jk.domain.Node;
import cn.lx.jk.domain.Role;
import cn.lx.jk.service.ModuleService;
import cn.lx.jk.service.RoleService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

public class RoleAction extends BaseAction  implements ModelDriven<Role>{
	private Role role = new Role();
	@Override
	public Role getModel() {
		// TODO Auto-generated method stub
		return role;
	}
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	private ModuleService moduleService;
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	private Page page = new Page();
	/**显示角色管理界面
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
	//	int i = 10/0;
		roleService.findByPage("from Role ",page);
		page.setUrl("roleAction_list");
		ActionContext.getContext().getValueStack().push(page);
		return "list";
	}
	
	/**显示新增角色页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		
		return "addUI";
	}
	
	/**保存角色信息
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		// TODO Auto-generated method stub
	
		roleService.save(role);
		return "relist";
	}

	/**查看角色信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//根据id查询部门信息
		role = roleService.findById(role.getId());
		//放到栈顶
		super.push(role);
		return "view";
	}
  
	/**显示更新角色页面
	 * @return
	 * @throws Exception
	 */
	public String updateUI() throws Exception {
		// TODO Auto-generated method stub
		//根据id查询部门信息
		role = roleService.findById(role.getId());
		//放到栈顶
		super.push(role);
	
	
		return "updateUI";
	}
	
	/**更新角色信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//先查询数据库中的用户
		Role myRole = roleService.findById(role.getId());
		//更新指定方法
		if(myRole !=null){
			myRole.setName(role.getName());
			myRole.setRemark(role.getRemark());
		}
		roleService.update(myRole);
		return "relist";
	}
	
	/**删除角色信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		String[] ids = role.getId().split(", ");
		
		 roleService.delete(ids);
		
		return "relist";
	}
	
	/**显示权限管理页面
	 * @return
	 * @throws Exception
	 */
	public String moduleUI() throws Exception {
	/*	// 查询所所有模块
		List<Module> moduleList =  moduleService.findAll();
		request.put("moduleList", moduleList);*/
		//查询当前角色
		role = roleService.findById(role.getId());
		//放到栈顶
		super.push(role);
		return "moduleUI";
	}
	

	/**ajax 请求实现节点树，不使用递归，使用双层for循环实现
	 * @return
	 * @throws Exception
	 */
	public String roleModuleJsonStr() throws Exception {
		//设置响应乱码
		ServletActionContext.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
		// 查询所所有模块
		List<Module> moduleList =  moduleService.findAll();
		//拼接Json
		//定义根节点
		Map<String,Object> rootNodes = new HashMap<String,Object>();
		//name表示节点名称
		rootNodes.put("name", "权限管理");
		//open表示节点是否打开
		rootNodes.put("open", true);
		//查询当前角色
		role = roleService.findById(role.getId());
		Set<Module> modules = role.getModules();
		//定义set存储一级节点
		List<Map> first = new ArrayList<Map>();
		for(Module module:moduleList){
			
			//如果为1级节点
			if(module.getLayerNum()==1){
				//用于存储二级节点
				List<Map> second = new ArrayList<Map>();
				//定义一级节点 
				Map<String,Object> firstNodes = new HashMap<String,Object>();
				//节点名称
				firstNodes.put("name", module.getName());
				//节点自定义属性
				firstNodes.put("id", module.getId());
				for(Module module2:modules){
					if(module.getParentName().equals(module2.getName())){
						//如果和当前用户的权限一样，就选中
						firstNodes.put("checked", true);
					}
				}
				for(Module module1:moduleList){
					//如果为2级节点
					if(module1.getLayerNum()==2){
						if(module1.getParentName().equals(module.getName())){
							//二级节点
							Map<String,Object> secondNodes = new HashMap<String,Object>();
							secondNodes.put("name", module1.getName());
							secondNodes.put("id", module1.getId());
							for(Module module2:modules){
								if(module1.getParentName().equals(module2.getName())){
									//如果和当前用户的权限一样，就选中
									secondNodes.put("checked", true);
								}
							}
							second.add(secondNodes);
						}
					}	
				}
				firstNodes.put("children", second);
				first.add(firstNodes);
			}
		}
		rootNodes.put("children", first);
		String str = JSON.toJSONString(rootNodes);
		ServletActionContext.getResponse().getWriter().write(str);
		return NONE;
	}
	/**ajax 请求实现节点树,递归实现
	 * @return
	 * @throws Exception
	 */
	public String roleModuleJsonStr1() throws Exception {
		//设置响应乱码
		ServletActionContext.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
		// 查询所所有模块
		List<Module> moduleList =  moduleService.findByCondition("from Module where parentId is null",null);
		
		role = roleService.findById(role.getId());
		//获取当前角色的模块
		Set<Module> modules = role.getModules();
		//定义集合节点
		List<Map> list = new ArrayList<Map>();
		//遍历所有模块，存储到list集合中
		findChildren(list,"0",moduleList,modules);
		//将集合转化为json
		String str = JSON.toJSONString(list);
		ServletActionContext.getResponse().getWriter().write(str);
		return NONE;
	}
	
	/**ajax 请求实现节点树,完全使用表中属性
	 * @return
	 * @throws Exception
	 */
	public String roleModuleJsonStr2() throws Exception {
		
		// 查询所所有模块
		List<Module> moduleList =  moduleService.findAll();
		
		role = roleService.findById(role.getId());
		//获取当前角色的模块
		Set<Module> modules = role.getModules();
		//定义集合节点
		ArrayList<Map> list = new ArrayList<Map>();
		
		for(Module module:moduleList){
			System.out.println(module.getRoles());
			Map<String,Object> node = new HashMap<String,Object>();
			node.put("name", module.getName());
			node.put("id", module.getId());
			//设置父节点
			node.put("pId", module.getParentId());
			if(modules.contains(module)){
				node.put("checked", true);
			}
	
			list.add(node);
		}
		//将集合转化为json
		String str = JSON.toJSONString(list);
		//设置响应乱码
		ServletActionContext.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
		//控制输出时，不使用缓存
		ServletActionContext.getResponse().setHeader("cache-Control","no-cache");
		ServletActionContext.getResponse().getWriter().write(str);
		System.out.println(str);
		return NONE;
	}
	
	/**遍历模块，并将节点存储到list集合中
	 * @param list     用于存储节点
	 * @param pid     //父节点
	 * @param moduleList //查询到的模块集合
	 * @param modules   //当前用户的所有模块
	 */
	public void findChildren(List<Map> list,String pid,List<Module> moduleList,Set<Module> modules){
		
		for(Module module:moduleList ){
			//节点，用于存储节点属性
			Map<String,Object> node = new HashMap<String,Object>();
			//设置节点的名称属性
			node.put("name",module.getName());
			//设置节点的id属性
			node.put("id", module.getId());
			//设置父节点的id属性
			node.put("pId",pid);
			//遍历当前角色的模块，如果一样，就选中
			if(modules.contains(module)){
				node.put("checked", true);
			}
			//将节点添加到集合中
			list.add(node);
			//查询该模块的所有子模块
			List<Module> mList = moduleService.findByCondition("from Module where parentId = ?",module.getId());
			//递归调用
			findChildren(list,module.getId(),mList,modules);
		}
		
	}
	
	
	/**使用手动拼接json数据的方式，生成json数据
	 * @return
	 * @throws Exception
	 */
	public String roleModuleJsonStr3() throws Exception {
		//查询所有的权限
		List<Module> moduleList = moduleService.findAll();
		//获取当前角色
		role = roleService.findById(role.getId());
		Set<Module> modules = role.getModules();
		//用于拼接json数据
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int  size = moduleList.size();
		for(Module module:moduleList){
			size--;
			sb.append("{id:\"").append(module.getId());
			sb.append("\",pId:\"").append(module.getParentId());
			sb.append("\",name:\"").append(module.getName()+"\"");
			if(modules.contains(module)){
				sb.append(",checked:\"").append("true\"");
			}
			sb.append("}");
			if(size>0){
				sb.append(",");
			}
		}
		sb.append("]");
		//设置响应乱码
		ServletActionContext.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
		//控制输出时，不使用缓存
		ServletActionContext.getResponse().setHeader("cache-Control","no-cache");
		ServletActionContext.getResponse().getWriter().write(sb.toString());
		System.out.println(sb.toString());
		return NONE;
	}
	
	/**使用javabean实现json数据的实现
	 * @return
	 * @throws Exception
	 */
	public String roleModuleJsonStr4() throws Exception {
		//查询所有的权限
		List<Module> moduleList = moduleService.findAll();
		//获取当前角色
		role = roleService.findById(role.getId());
		Set<Module> modules = role.getModules();
		//用于存储所有的节点
		List<Node> nodes = new ArrayList<Node>();
		for(Module module:moduleList){
			Node node = new Node();
			node.setId(module.getId());
			node.setPId(module.getParentId());
			node.setName(module.getName());
			if(modules.contains(module)){
				node.setChecked("true");
			}else{
				node.setChecked("false");
			}
			nodes.add(node);
		}
		//设置响应乱码
		ServletActionContext.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
		//控制输出时，不使用缓存
		ServletActionContext.getResponse().setHeader("cache-Control","no-cache");
		ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(nodes));
		System.out.println(JSON.toJSONString(nodes));
		return NONE;
	
	}
	/**更新角色权限信息
	 * @return
	 * @throws Exception
	 */
	private String moduleIds;
	
	public String module() throws Exception {
		//获取所有的id
		
		/*String[] ids = ServletActionContext.getRequest().getParameterValues("moduleIds");
		Set<Module> modules = new HashSet<Module>();*/
		Set<Module> modules = new HashSet<Module>();
		//String moduleIds = (String) request.get("moduleIds");
		String[] ids = moduleIds.split(",");
		Role myRole = roleService.findById(role.getId());
		if(ids !=null && ids.length>0){
			for(String id :ids){
				//根据Id查询Module
				Module module = moduleService.findById(id);
				modules.add(module);
			}
			myRole.setModules(modules);
			roleService.update(myRole);
		}
		return "relist";
	}
	public String getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
	
	
}
