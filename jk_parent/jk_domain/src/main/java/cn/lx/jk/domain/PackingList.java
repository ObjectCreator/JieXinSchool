package cn.lx.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	PackingListService接口
 * @Author:			黑马程序员	    无德皇叔
 * @Company:		http://java.lx.cn
 * @CreateDate:		2016-7-15 15:41:59
 */

public class PackingList extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String seller;			    //卖方
	private String buyer;				//买方
	private String invoiceNo;			//发票号
	private Date invoiceDate;			//发票日期
	private String marks;				//唛头
	private String descriptions;		//描述
	private String exportIds;			//报运ID集合  用,分割
	private String exportNos;			//报运NO集合用|分割
	private Integer state;				//0草稿 1已上报 
	private String createBy;			//数据库
	private String createDept;			
	private Date createTime;			

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getSeller() {
		return this.seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}	
	
	public String getBuyer() {
		return this.buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}	
	
	public String getInvoiceNo() {
		return this.invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}	
	
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}	
	
	public String getMarks() {
		return this.marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}	
	
	public String getDescriptions() {
		return this.descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}	
	
	public String getExportIds() {
		return this.exportIds;
	}
	public void setExportIds(String exportIds) {
		this.exportIds = exportIds;
	}	
	
	public String getExportNos() {
		return this.exportNos;
	}
	public void setExportNos(String exportNos) {
		this.exportNos = exportNos;
	}	
	
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}	
	
	
	
}
