package cn.lx.jk.domain;

import java.util.Date;

/**
 * @Description:	FinanceService接口
 * @Author:			麦芽的香气
 * @Company:		truest me
 * @CreateDate:		2016-7-16 11:30:02
 */

public class Finance extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private Date inputDate;			
	private String inputBy;			
	private Integer state;			

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public Date getInputDate() {
		return this.inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}	
	
	public String getInputBy() {
		return this.inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}	
	
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}	
	
	
	
}
