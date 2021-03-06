package cn.lx.jk.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 * @author Administrator
 *
 */
public class Role extends BaseEntity {
	private String id;
	
	private Set<User> users = new HashSet<User>(0);//角色与用户  多对多
	private Set<Module> modules = new HashSet<Module>(0);// 角色与模块  多对多
	private String name;//角色名
	private String remark;//备注
	private Integer orderNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	
	
	

}
