package cn.lx.jk.web.action;


import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import cn.lx.jk.domain.LoginLog;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.UserService;
import cn.lx.jk.utils.SysConstant;

/**
 * @Description: 登录和退出类
 * @Author:		传智播客 java学院	传智.宋江
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月31日
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String login() throws Exception {
		
		//解决登陆之后访问主页面还需要登陆的问题
		if(session.get(SysConstant.CURRENT_USER_INFO) != null){
			return SUCCESS;
		}
		//如果没有登陆，且没有填写用户名，就跳转到登陆页面
		if(username ==null){
			request.put("errorInfo", "请先登陆");
			return "login";
		}
		try {
			//shrio认证
			//1 得到Subject
			Subject subject =SecurityUtils.getSubject();
			//2 实现登陆
			//2.1将用户名和密码进行封装，交给shiro安全框架，并实现登陆过程
			UsernamePasswordToken uptoken = new UsernamePasswordToken(username, password);
			//3该方法执行后，就会进入AuthRealm的用户认证方法doGetAuthenticationInfo，该方法执行会调用密码比较器
			subject.login(uptoken);
			//4当认证成功后，将shrio中的用户对象取出来，放到session域中
			User user = (User) subject.getPrincipal();
			//登陆成功后，将登陆信息存到日志中
			LoginLog loginLog = new LoginLog();
			loginLog.setLoginName(user.getUserName());
			loginLog.setLoginTime(new Date());
			loginLog.setIpAddress(this.getRemoteHost(ServletActionContext.getRequest()));
			//保存登陆日志
			userService.saveLoginLog(loginLog);
			//SysConstant用于存储项目常量，好处时，需要修改常量时，只需要修改一处即可。
			session.put(SysConstant.CURRENT_USER_INFO, user);
			//5实现页面跳转
			return SUCCESS;
				
		} catch (Exception e) {
			//6 当输入的用户名和密码错误，会抛出异常
			e.printStackTrace();
			request.put("errorInfo","用户名或密码错误");
			return "login";
		}
	}
	
	/**登出方法
	 * @return
	 */
	public String logout(){
		//移除session中存储的用户
		session.remove(SysConstant.CURRENT_USER_INFO);	
		
		Subject subject =SecurityUtils.getSubject();
		//调用登出方法
		subject.logout();
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/**用于获取ip的方法
	 * @param request
	 * @return
	 */
	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
}

