package cn.lx.jk.utils;

import java.util.HashSet;
import java.util.Set;

import cn.lx.jk.domain.Module;
import cn.lx.jk.domain.Role;
import cn.lx.jk.domain.User;

/**模块工具类，作用判断用户是否具有某个action的访问权限
 * @author liuxuan
 *
 */
public class ModuleUtil {
	
	/**判断用户是否具有某个路径的访问权限
	 * @param user
	 * @param path
	 * @return
	 */
	public static Boolean checkUserPath(User user,String path){
		//创建StringBuffer用于存储用户可以访问的所有路径
		StringBuffer stringBuffer = new StringBuffer();
		//创建set用于存储用户的所有的模块信息
		Set<Module> modules = new HashSet<Module>();
		for(Role role:user.getRoles()){
			for(Module module:role.getModules()){
				//将用户的模块存储到set中
				modules.add(module);
			}
		}
		
		for(Module module:modules){
			//将用户可以访问的路径存储到StringBuffer
			stringBuffer.append(module.getCurl()+",");
		}
		String paths = stringBuffer.toString();
	
		//path  userAction_list,将其转换为userAction
		path = path.split("_")[0];
		//判断用户是否可以访问该路径
		return paths.contains(path);
	}
}
