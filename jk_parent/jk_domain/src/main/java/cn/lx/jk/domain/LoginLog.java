package cn.lx.jk.domain;

import java.util.Date;

public class LoginLog {
	/* LOGIN_LOG_ID varchar2(40) PRIMARY KEY NOT NULL,
  LOGIN_NAME varchar2(30) default NULL,
  IP_ADDRESS varchar2(20) default NULL,
  LOGIN_TIME date default NULL
	 * */
	private String id;
	private String loginName;
	private String ipAddress;
	private Date loginTime;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
