package cn.lx.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	InvoiceService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 13:51:09
 */

public class Invoice extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String scNo;			//packing.getExportNos S/C就是报运的合同号
	private String blNo;			
	private String tradeTerms;			
	private Integer state;			//0草稿 1已上报
	private String createBy;			
	private String createDept;			
	private Date createTime;			

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getScNo() {
		return this.scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}	
	
	public String getBlNo() {
		return this.blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}	
	
	public String getTradeTerms() {
		return this.tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}	
	
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}	
	
	
	
}
