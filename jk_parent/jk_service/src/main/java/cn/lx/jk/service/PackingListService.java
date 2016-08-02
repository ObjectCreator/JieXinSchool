package cn.lx.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.domain.PackingList;
import cn.lx.jk.utils.Page;


/**
 * @Description:	PackingListService接口
 * @Author:			黑马程序员	    无德皇叔
 * @Company:		http://java.lx.cn
 * @CreateDate:		2016-7-15 15:44:05
 */

public interface PackingListService {

	public List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params);
	public PackingList get(Class<PackingList> entityClass, Serializable id);
	public Page<PackingList> findPage(String hql, Page<PackingList> page, Class<PackingList> entityClass, Object[] params);
	

	public void saveOrUpdate(PackingList entity);
	public void saveOrUpdateAll(Collection<PackingList> entitys);
	
	public void deleteById(Class<PackingList> entityClass, Serializable id);
	public void delete(Class<PackingList> entityClass, Serializable[] ids);
}
