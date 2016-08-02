package cn.lx.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Product;
import cn.lx.jk.service.ProductService;
import cn.lx.jk.utils.Page;

/**
 * @Description:	ProductService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 11:48:51
 */

public class ProductServiceImpl implements ProductService {
	//spring注入DAO
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Product> find(String hql, Class<Product> entityClass, Object[] params) {
		return baseDao.find(hql, Product.class, params);
	}

	public Product get(Class<Product> entityClass, Serializable id) {
		return baseDao.get(Product.class, id);
	}

	public Page<Product> findPage(String hql, Page<Product> page, Class<Product> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Product.class, params);
	}

	public void saveOrUpdate(Product entity) {
		if(entity.getId()==null){								//代表新增
			entity.setState(1);									//状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Product> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Product> entityClass, Serializable id) {
		Product product = baseDao.get(entityClass, id);
		product.setState(-1);
		baseDao.saveOrUpdate(product);
	}

	public void delete(Class<Product> entityClass, Serializable[] ids) {
		for(Serializable id:ids){
			deleteById(entityClass,id);
		}
	
	}

	@Override
	public void save(Product entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> productList() {
		// TODO Auto-generated method stub
		return null;
	}

}
