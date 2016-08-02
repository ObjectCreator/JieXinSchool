package cn.lx.jk.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.lx.jk.domain.User;
import cn.lx.jk.utils.ModuleUtil;
import cn.lx.jk.utils.SysConstant;

/**自定义拦截器功能，实现功能，用户访问某些路径时，判断是否有相应的权限，没有权限，不能访问
 * @author liuxuan
 *
 */
public class MyInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//先获取登陆的用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		//判断用户的权限
		String path = ActionContext.getContext().getName();
		
		//如果是登陆方法就进行放行
		if(path.contains("login")){
			return invocation.invoke();
		}
		if(path.contains("upLonin")){
			return invocation.invoke();
		}
		if(path.contains("feedback")){
			return invocation.invoke();
		}
		//如果没有登陆，提示登陆
		if(user == null){
			ServletActionContext.getRequest().setAttribute("errorInfo", "请登陆");
			//全局逻辑视图
			return "login";
		}
		
		//，如果有权限，就进行放行
		if(ModuleUtil.checkUserPath(user, path)){
			return invocation.invoke();
		}
		//否则进行拦截
		return "noRight";
	}

}
