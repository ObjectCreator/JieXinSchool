package cn.lx.jk.domain;

/**ztree节点的javaBean
 * @author liuxuan
 *
 */
public class Node {
	private String id;//节点的id
	private String pId;//节点的父节点
	private String name;//节点的名称
	private String checked;//节点是否选中
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPId() {
		return pId;
	}
	public void setPId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}
