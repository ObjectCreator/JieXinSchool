package cn.lx.jk.domain;


/**
 * @Description:	TypeService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-17 22:54:12
 */

public class Type extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String typeName;			
	private Integer typeSort;	//0表示意见反馈分类		

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getTypeName() {
		return this.typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}	
	
	public Integer getTypeSort() {
		return this.typeSort;
	}
	public void setTypeSort(Integer typeSort) {
		this.typeSort = typeSort;
	}	
}
