package cn.lx.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.dao.BaseDao;

import cn.lx.jk.domain.PackingList;


import cn.lx.jk.domain.Finance;

import cn.lx.jk.domain.ShippingOrder;
import cn.lx.jk.service.ShippingOrderService;
import cn.lx.jk.utils.Page;

/**
 * @Description:	ShippingOrderService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 11:37:31
 */

public class ShippingOrderServiceImpl implements ShippingOrderService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ShippingOrder> find(String hql, Class<ShippingOrder> entityClass, Object[] params) {
		return baseDao.find(hql, ShippingOrder.class, params);
	}

	public ShippingOrder get(Class<ShippingOrder> entityClass, Serializable id) {
		return baseDao.get(ShippingOrder.class, id);
	}

	public Page<ShippingOrder> findPage(String hql, Page<ShippingOrder> page, Class<ShippingOrder> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, ShippingOrder.class, params);
	}

	public void saveOrUpdate(ShippingOrder entity) {
		
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<ShippingOrder> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<ShippingOrder> entityClass, Serializable id) {
		ShippingOrder sp =baseDao.get(entityClass, id);
		if(sp != null){
			sp.setState(-1);
			baseDao.saveOrUpdate(sp);
		}
		PackingList pl = baseDao.get(PackingList.class, id);
		if(pl != null){
			pl.setState(1);
			baseDao.saveOrUpdate(pl);
		}
	}

	public void delete(Class<ShippingOrder> entityClass, Serializable[] ids) {

		for(Serializable id :ids){
			deleteById(entityClass,id);
		}

		
	}

}
