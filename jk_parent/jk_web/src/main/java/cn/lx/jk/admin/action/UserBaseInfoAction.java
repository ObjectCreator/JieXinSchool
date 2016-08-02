package cn.lx.jk.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;

import cn.lx.jk.domain.LoginLog;
import cn.lx.jk.domain.User;
import cn.lx.jk.domain.UserBaseInfo;
import cn.lx.jk.service.UserBaseInfoService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.web.action.BaseAction;

/**
 * @Description:	UserBaseInfoService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-17 23:38:37
 */

public class UserBaseInfoAction extends BaseAction implements ModelDriven<UserBaseInfo> {
	//注入service
	private UserBaseInfoService userBaseInfoService;
	public void setUserBaseInfoService(UserBaseInfoService userBaseInfoService) {
		this.userBaseInfoService = userBaseInfoService;
	}
	
	//model驱动
	private UserBaseInfo model = new UserBaseInfo();
	public UserBaseInfo getModel() {
		return this.model;
	}
	
	private Page page=new Page();
	



	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	//转向修改页面
	public String toupdate(){
		//获取当前登陆用户
		User user = super.getCurUser();
		model = userBaseInfoService.get(UserBaseInfo.class, user.getId());
	
		if(model != null){
		
			
		}else{
			model = new UserBaseInfo();
			model.setId(user.getId());
	
		}
		super.push(model);
		
		return "pupdate";
	}
	
	//修改保存
	public String update(){
		UserBaseInfo userBaseInfo = userBaseInfoService.get(UserBaseInfo.class, model.getId());
		
		//设置修改的属性，根据业务去掉自动生成多余的属性
		if(userBaseInfo != null){
		
		}else{
			userBaseInfo = new UserBaseInfo();
			userBaseInfo.setId(model.getId());
			
		}
		userBaseInfo.setNickName(model.getNickName());
		userBaseInfo.setTel(model.getTel());
		//同时更新user的电话
		User user = super.getCurUser();
		user.getUserInfo().setTelephone(model.getTel());
		userBaseInfo.setNation(model.getNation());
		userBaseInfo.setHomeAddress(model.getHomeAddress());
		userBaseInfo.setPoliticsStatus(model.getPoliticsStatus());
		userBaseInfo.setSpeciality(model.getSpeciality());
	
		userBaseInfoService.saveOrUpdate(userBaseInfo,user);
		return toview();
	}
	
	
	
	//查看
	public String toview(){
		User user = super.getCurUser();
		model = userBaseInfoService.get(UserBaseInfo.class, user.getId());
		super.put("user", user);
		
		if(model != null){
		
			super.push(model);
		}
		return "pview";			//转向查看页面
	}
	/**
	 * 查看登录历史
	 * @return
	 * @throws Exception
	 */
	public String history()throws Exception{
		User curUser = this.getCurUser();
		String hql="from LoginLog where loginName=? order by loginTime desc";
		userBaseInfoService.findPage(hql,page, LoginLog.class, new Object[]{curUser.getUserName()});
		page.setUrl("userBaseInfoAction_history");
		super.push(page);
		return "history";
	}
	/**
	 * 上次登录日志
	 * @throws IOException
	 */
	public void upLonin() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(session.get("flag")!=null){
			response.getWriter().write("login");
			return ;
		}
		User curUser = this.getCurUser();
		String hql="from LoginLog where loginName=? order by loginTime desc";
		userBaseInfoService.findPage(hql, page,  LoginLog.class, new Object[]{curUser.getUserName()});
		LoginLog login =(LoginLog) page.getResults().get(1);
		response.getWriter().println("您好:"+login.getLoginName()+" 您上次登录时间为:"+login.getLoginTime()+" 登录IP为:"+login.getIpAddress());
		session.put("flag", "true");
	}
}
