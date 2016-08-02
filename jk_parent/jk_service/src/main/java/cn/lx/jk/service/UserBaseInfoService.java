package cn.lx.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.lx.jk.domain.LoginLog;
import cn.lx.jk.domain.User;
import cn.lx.jk.domain.UserBaseInfo;
import cn.lx.jk.utils.Page;


/**
 * @Description:	UserBaseInfoService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-17 23:38:37
 */

public interface UserBaseInfoService {

	public UserBaseInfo get(Class<UserBaseInfo> entityClass, Serializable id);
	

	public void saveOrUpdate(UserBaseInfo entity, User user);


	public Page findPage(String hql,Page page, Class<LoginLog> class1, Object[] objects);
	

}
