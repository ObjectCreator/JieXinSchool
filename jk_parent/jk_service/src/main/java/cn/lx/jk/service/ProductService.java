package cn.lx.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.domain.Product;
import cn.lx.jk.utils.Page;

/**
 * @Description:	ProductService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 11:48:51
 */

public interface ProductService {

	public List<Product> find(String hql, Class<Product> entityClass, Object[] params);
	public Product get(Class<Product> entityClass, Serializable id);
	public Page<Product> findPage(String hql, Page<Product> page, Class<Product> entityClass, Object[] params);
	
	public void save(Product entity);
	public void saveOrUpdate(Product entity);
	public void saveOrUpdateAll(Collection<Product> entitys);
	
	public void deleteById(Class<Product> entityClass, Serializable id);
	public void delete(Class<Product> entityClass, Serializable[] ids);
	public List<Product> productList();
}
