package cn.lx.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.domain.Invoice;
import cn.lx.jk.utils.Page;

/**
 * @Description:	InvoiceService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 13:51:10
 */

public interface InvoiceService {

	public List<Invoice> find(String hql, Class<Invoice> entityClass, Object[] params);
	public Invoice get(Class<Invoice> entityClass, Serializable id);
	public Page<Invoice> findPage(String hql, Page<Invoice> page, Class<Invoice> entityClass, Object[] params);
	
	public void save(Invoice entity);
	public void saveOrUpdate(Invoice entity);
	public void saveOrUpdateAll(Collection<Invoice> entitys);
	
	public void deleteById(Class<Invoice> entityClass, Serializable id);
	public void delete(Class<Invoice> entityClass, Serializable[] ids);
}
