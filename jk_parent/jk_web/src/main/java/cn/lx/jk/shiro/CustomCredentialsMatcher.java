package cn.lx.jk.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import cn.lx.jk.utils.Encrypt;

/**密码比较器
 * 规范，需要继承SimpleCredentialsMatcher
 * 重写doCredentialsMatch密码比较方法
 * @author liuxuan
 *
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher{

	/**
	 * 密码比较方法
	 * AuthenticationToken 里面存储了，用户登录时，填写的账号和密码
	 * AuthenticationInfo 存储的是用户的原始密码
	 * */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//向下转型
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//获取用户输入的密码
		String password = new String(upToken.getPassword());
		//对密码进行加密，使用Md5Hash进行加密  
		String newPassword = Encrypt.md5(password, upToken.getUsername());
		//获取原始密码
		Object oldPassword = info.getCredentials();
		//进行比较
		return equals(newPassword, oldPassword);
	}
	
}
