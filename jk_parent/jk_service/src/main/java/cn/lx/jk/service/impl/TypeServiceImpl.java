package cn.lx.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Type;
import cn.lx.jk.service.TypeService;
import cn.lx.jk.utils.Page;


/**
 * @Description:	TypeService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-17 22:54:12
 */

public class TypeServiceImpl implements TypeService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Type> find(String hql, Class<Type> entityClass, Object[] params) {
		return baseDao.find(hql, Type.class, params);
	}

	public Type get(Class<Type> entityClass, Serializable id) {
		return baseDao.get(Type.class, id);
	}

	public Page<Type> findPage(String hql, Page<Type> page, Class<Type> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Type.class, params);
	}





}

