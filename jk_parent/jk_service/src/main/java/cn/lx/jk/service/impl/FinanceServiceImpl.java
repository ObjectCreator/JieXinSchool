package cn.lx.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Finance;
import cn.lx.jk.domain.Invoice;
import cn.lx.jk.service.FinanceService;
import cn.lx.jk.utils.Page;

/**
 * @Description:	FinanceService接口
 * @Author:			麦芽的香气
 * @Company:		truest me
 * @CreateDate:		2016-7-16 11:30:02
 */

public class FinanceServiceImpl implements FinanceService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Finance> find(String hql, Class<Finance> entityClass, Object[] params) {
		return baseDao.find(hql, Finance.class, params);
	}

	public Finance get(Class<Finance> entityClass, Serializable id) {
		return baseDao.get(Finance.class, id);
	}

	public Page<Finance> findPage(String hql, Page<Finance> page, Class<Finance> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Finance.class, params);
	}

	public void saveOrUpdate(Finance entity) {
		if(entity.getId()==null){								//代表新增
			entity.setState(1);									//状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Finance> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Finance> entityClass, Serializable id) {
		baseDao.deleteById(Finance.class, id);
	}

	public void delete(Class<Finance> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			Finance finance = baseDao.get(entityClass, id);
			if(finance != null ){
				finance.setState(-1);//删除状态
				baseDao.saveOrUpdate(finance);
			}
		
			//同时将发票的状态还原
			
			Invoice invoice  = baseDao.get(Invoice.class, id);
			if(invoice != null){
				invoice.setState(1);
				
			}
		
			baseDao.saveOrUpdate(invoice);
		}
		//删除发票
	}

	@Override
	public void save(Finance model) {
		/*
		 * 共享主键,查询出发票 把状态设置2 已财务报运
		 */
		Invoice invoice = baseDao.get(Invoice.class,model.getId());
		invoice.setState(2);//已财务报运
		baseDao.saveOrUpdate(invoice);
		
		model.setState(1);//设置财务报运状态为启用
		baseDao.saveOrUpdate(model);
	}

}

