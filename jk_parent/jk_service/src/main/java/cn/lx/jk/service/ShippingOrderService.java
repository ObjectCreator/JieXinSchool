package cn.lx.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.domain.ShippingOrder;
import cn.lx.jk.utils.Page;

/**
 * @Description:	ShippingOrderService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 11:37:31
 */

public interface ShippingOrderService {

	public List<ShippingOrder> find(String hql, Class<ShippingOrder> entityClass, Object[] params);
	public ShippingOrder get(Class<ShippingOrder> entityClass, Serializable id);
	public Page<ShippingOrder> findPage(String hql, Page<ShippingOrder> page, Class<ShippingOrder> entityClass, Object[] params);
	
	//public void save(ShippingOrder entity);
	public void saveOrUpdate(ShippingOrder entity);
	public void saveOrUpdateAll(Collection<ShippingOrder> entitys);
	
	public void deleteById(Class<ShippingOrder> entityClass, Serializable id);
	public void delete(Class<ShippingOrder> entityClass, Serializable[] ids);
}
