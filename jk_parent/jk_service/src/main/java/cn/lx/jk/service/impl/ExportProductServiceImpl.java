package cn.lx.jk.service.impl;

import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.ExportProduct;

import cn.lx.jk.service.ExportProductService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.UtilFuns;

public class ExportProductServiceImpl implements ExportProductService {
	//注入DAO
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void findExportProductByPage(String hql, Page page, Object... params) {
		baseDao.findPage(hql, page, ExportProduct.class, params);
		
	}


	public ExportProduct findExportProductById(String id) {
		return baseDao.get(ExportProduct.class, id);
	}


	public List<ExportProduct> findAll() {
		return baseDao.find("from ExportProduct ", ExportProduct.class, null);
	}


	public void saveOrUpdate(ExportProduct model) {
		
	}


	public void delete(String[] ids) {
		for(String id :ids){
			this.delete(id);
		}
		
	}


	/**
	 * 递归删除父项子项
	 */
	public void delete(String id) {
		
		ExportProduct obj = baseDao.get(ExportProduct.class, id);
	
		baseDao.deleteById(ExportProduct.class, id);
		
	}


	@Override
	public List<ExportProduct> findByCondition(String hql, Object... params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, ExportProduct.class, params);
	}

}
