package cn.lx.jk.service.impl;

import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Contract;
import cn.lx.jk.domain.ContractProduct;
import cn.lx.jk.service.ContractService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.UtilFuns;

public class ContractServiceImpl implements ContractService {
	//注入DAO
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void findContractByPage(String hql, Page page, Object... params) {
		baseDao.findPage(hql, page, Contract.class, params);
		
	}


	public Contract findContractById(String id) {
		return baseDao.get(Contract.class, id);
	}


	public List<Contract> findAll() {
		return baseDao.find("from Contract where state=1", Contract.class, null);
	}


	public void saveOrUpdate(Contract model) {
		System.out.println("id"+model.getId());
		//如果model的id为null执行保存工作，否则，执行修改工作
		if(UtilFuns.isEmpty(model.getId())){
			model.setTotalAmount(0.0D);
			model.setState(0);
		}
		baseDao.saveOrUpdate(model);
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
		
		Contract obj = baseDao.get(Contract.class, id);
	
		baseDao.deleteById(Contract.class, id);
		
	}


	@Override
	public List<Contract> findByCondition(String hql, Object... params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, Contract.class, params);
	}

}
