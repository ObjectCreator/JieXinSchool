package cn.lx.jk.domain;

public class Manager {
	
	private String id;//用于标识行的Id
	private String type;//标识所管理的是部门，还是员工
	private String typeId;//标识管理对象的id
	private String userId;//标识管理者的额id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
