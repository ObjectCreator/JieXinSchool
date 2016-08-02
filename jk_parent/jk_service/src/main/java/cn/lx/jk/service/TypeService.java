package cn.lx.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.domain.Type;
import cn.lx.jk.utils.Page;


/**
 * @Description:	TypeService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-17 22:54:12
 */

public interface TypeService {

	public List<Type> find(String hql, Class<Type> entityClass, Object[] params);
	public Type get(Class<Type> entityClass, Serializable id);
	public Page<Type> findPage(String hql, Page<Type> page, Class<Type> entityClass, Object[] params);
	

}
