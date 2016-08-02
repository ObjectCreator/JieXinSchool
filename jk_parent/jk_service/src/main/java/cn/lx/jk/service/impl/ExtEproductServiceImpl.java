package cn.lx.jk.service.impl;

import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.ExtEproduct;

import cn.lx.jk.service.ExtEproductService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.UtilFuns;

public class ExtEproductServiceImpl implements ExtEproductService {
	//注入DAO
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void findExtEproductByPage(String hql, Page page, Object... params) {
		baseDao.findPage(hql, page, ExtEproduct.class, params);
		
	}


	public ExtEproduct findExtEproductById(String id) {
		return baseDao.get(ExtEproduct.class, id);
	}


	public List<ExtEproduct> findAll() {
		return baseDao.find("from ExtEproduct where state=1", ExtEproduct.class, null);
	}


	public void saveOrUpdate(ExtEproduct model) {
	
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
		
		ExtEproduct obj = baseDao.get(ExtEproduct.class, id);
	
		baseDao.deleteById(ExtEproduct.class, id);
		
	}

}
