package cn.lx.jk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	FeedbackService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 12:24:46
 * 意见反馈管理类
 */

public class Feedback extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String inputBy;		//提出人	
	private Date inputTime;		//提出时间
	private String title;		//标题
	private String content;		//内容
	private String classType;	//分类	
	private String tel;			//联系电话
	private String answerBy;	//解决人		
	private Date answerTime;	//解决时间
	private String solveMethod;	//解决办法
	private String resolution;	//解决方式
	private String difficulty;	//解决难度
	private Integer isShare;	   //是否公开
	private Integer state;		//状态  0 表示未解决 1表示已解决
	private String answerName; //解决人姓名
	

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getInputBy() {
		return this.inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}	
	
	public Date getInputTime() {
		return this.inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}	
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	
	public String getClassType() {
		return this.classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}	
	
	public String getTel() {
		return this.tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}	
	
	public String getAnswerBy() {
		return this.answerBy;
	}
	public void setAnswerBy(String answerBy) {
		this.answerBy = answerBy;
	}	
	
	public Date getAnswerTime() {
		return this.answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}	
	
	public String getSolveMethod() {
		return this.solveMethod;
	}
	public void setSolveMethod(String solveMethod) {
		this.solveMethod = solveMethod;
	}	
	
	public String getResolution() {
		return this.resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}	
	
	public String getDifficulty() {
		return this.difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}	
	
	
	public Integer getIsShare() {
		return isShare;
	}
	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAnswerName() {
		return answerName;
	}
	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}	
	
	
	
	
	
}
