package cn.lx.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Invoice;
import cn.lx.jk.domain.PackingList;
import cn.lx.jk.domain.ShippingOrder;
import cn.lx.jk.service.InvoiceService;
import cn.lx.jk.utils.Page;

/**
 * @Description:	InvoiceService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 13:51:10
 */

public class InvoiceServiceImpl implements InvoiceService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Invoice> find(String hql, Class<Invoice> entityClass, Object[] params) {
		return baseDao.find(hql, Invoice.class, params);
	}

	public Invoice get(Class<Invoice> entityClass, Serializable id) {
		return baseDao.get(Invoice.class, id);
	}

	public Page<Invoice> findPage(String hql, Page<Invoice> page, Class<Invoice> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Invoice.class, params);
	}

	public void saveOrUpdate(Invoice entity) {
		if(entity.getId()==null){								//代表新增
			entity.setState(1);									//状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Invoice> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Invoice> entityClass, Serializable id) {
		PackingList packingList = baseDao.get(PackingList.class, id);
		if(packingList !=null){
			packingList.setInvoiceNo("");
			packingList.setInvoiceDate(null);
			
			baseDao.saveOrUpdate(packingList);
			
		}
		
		ShippingOrder shippingOrder = baseDao.get(ShippingOrder.class, id);
		if(shippingOrder!=null){
			shippingOrder.setState(1);
			//保存委托单
			baseDao.saveOrUpdate(shippingOrder);
		}
		
		//5:删除发票单
		Invoice invoice = baseDao.get(Invoice.class, id);
		if(invoice!=null){
			invoice.setState(-1);
			baseDao.saveOrUpdate(invoice);
		}
		
	}

	public void delete(Class<Invoice> entityClass, Serializable[] ids) {
		for(Serializable id:ids){
			deleteById(entityClass,id);
		}
		
		
	}

	
	public void save(Invoice entity) {
		// TODO Auto-generated method stub
	}

}


