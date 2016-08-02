package cn.lx.jk.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.lx.jk.domain.Module;
import cn.lx.jk.domain.Role;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.UserService;

/**realm作用域，主要实现用户认证和权限授权操作。
 * 规范，需要实现AuthorizingRealm类
 * 重写doGetAuthorizationInfo 权限授权方法
 * 重现doGetAuthenticationInfo 角色认证方法
 * @author liuxuan
 *
 */
public class AuthRealm extends AuthorizingRealm{
	//注入userService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 当页面有shiro标签时，就会调用授权方法
	 * */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1、获取登陆认证时，存入到shiro的realm域中的User对象
		//fromRealm是获取当前域中的PrincipalCollection的集合，进行迭代，获取第一个元素，就是我们存储的用户
		User user = (User) principals.fromRealm(this.getName()).iterator().next();
		//2、用于存储，用户模块的权限集合
		Set<String> userModule =  new HashSet<String>();
		//2、1获取当前用户的角色
		for(Role role:user.getRoles()){
			for(Module module:role.getModules()){
				//2、2加载用户顶端菜单
				if(module.getCtype()==0){
					//2、3添加模块名称
					userModule.add(module.getName());
				}
				//2、2加载用户顶端菜单
				if(module.getCtype()==1){
					//2、3添加模块名称
					userModule.add(module.getName());
				}
				//2、2加载用户顶端菜单
				if(module.getCtype()==2){
					//2、3添加模块名称
					userModule.add(module.getName());
				}
				
			}
		}
		//2、3 创建权限授权对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(userModule);
		return info;
	}

	/**
	 * 当用户登陆时，自动调用该方法，即为认证方法
	 * 当这个方法返回Null时，就会出现异常
	 * 当认证成功后，返回的对象不为null时，就会自动进入密码比较器
	 * 使用密码比较器，是在配置文件中配置的
	 * AuthenticationToken 封装用户输入的用户名和密码
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//获取用户输入的用户名
		String userName = upToken.getUsername();
		//根据用户名查询用户是否存在
		User user = userService.findByName(userName);
		//如果用户不存在返回Null
		if(user == null){
			return null;
		}
		//如果用户存在返回AuthenticationInfo  三个 参数，第一个参数为Object principal用户，第二个参数为密码getCredentials，第三个参数为当前realm域的名称
		
		AuthenticationInfo info  = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}

}
