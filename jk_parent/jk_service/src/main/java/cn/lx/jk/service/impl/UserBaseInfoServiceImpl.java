package cn.lx.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.LoginLog;
import cn.lx.jk.domain.User;
import cn.lx.jk.domain.UserBaseInfo;
import cn.lx.jk.service.UserBaseInfoService;
import cn.lx.jk.utils.Page;


/**
 * @Description:	UserBaseInfoService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-17 23:38:37
 */

public class UserBaseInfoServiceImpl implements UserBaseInfoService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public UserBaseInfo get(Class<UserBaseInfo> entityClass, Serializable id) {
		return baseDao.get(UserBaseInfo.class, id);
	}


	public void saveOrUpdate(UserBaseInfo entity) {
		
		baseDao.saveOrUpdate(entity);
	}


	@Override
	public void saveOrUpdate(UserBaseInfo entity, User user) {
		// TODO Auto-generated method stub
		saveOrUpdate(entity);
		baseDao.saveOrUpdate(user);
	}


	@Override
	public Page findPage(String hql, Page page,Class<LoginLog> class1, Object[] objects) {
		return baseDao.findPage(hql, page, class1, objects);
	}


	



	

}

