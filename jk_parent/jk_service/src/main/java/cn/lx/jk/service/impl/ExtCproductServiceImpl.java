package cn.lx.jk.service.impl;

import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Contract;
import cn.lx.jk.domain.ExtCproduct;
import cn.lx.jk.service.ExtCproductService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.UtilFuns;

public class ExtCproductServiceImpl implements ExtCproductService {
	//注入DAO
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void findExtCproductByPage(String hql, Page page, Object... params) {
		baseDao.findPage(hql, page, ExtCproduct.class, params);
		
	}


	public ExtCproduct findExtCproductById(String id) {
		return baseDao.get(ExtCproduct.class, id);
	}


	public List<ExtCproduct> findAll() {
		return baseDao.find("from ExtCproduct where state=1", ExtCproduct.class, null);
	}


	public void saveOrUpdate(ExtCproduct model) {
		Double amount = 0.0d;
		Contract contract  = baseDao.get(Contract.class, model.getContractProduct().getContract().getId());;
		//执行新增
		if(UtilFuns.isEmpty(model.getId())){
			//计算当前获取的金额
			if(UtilFuns.isNotEmpty(model.getPrice())&&UtilFuns.isNotEmpty(model.getCnumber())){
				amount = model.getPrice()*model.getCnumber();
			}
			//获取购销合同的总金额，新增货物的金额
			model.setAmount(amount);
			contract.setTotalAmount(contract.getTotalAmount()+amount);
			//维护附件数
			contract.setExtCproductNum(contract.getExtCproductNum()+1);
		}else{
			//执行修改
			Double oldAmount =model.getAmount();
			//计算当前获取的金额
			if(UtilFuns.isNotEmpty(model.getPrice())&&UtilFuns.isNotEmpty(model.getCnumber())){
				amount = model.getPrice()*model.getCnumber();
				model.setAmount(amount);
			}
			//获取购销合同的总金额，减去货物的原金额，加上货物的现金额
			contract.setTotalAmount(contract.getTotalAmount()-oldAmount+model.getAmount());
		}
		//更新购销合同
		baseDao.saveOrUpdate(contract);
		//更新或新增货物
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
		//获取父类
		ExtCproduct obj = baseDao.get(ExtCproduct.class, id);
		
		baseDao.saveOrUpdate(obj);
		
	}


	@Override
	public void delete(ExtCproduct model) {
		// TODO Auto-generated method stub
		//获取购销合同
		Contract contract = baseDao.get(Contract.class, model.getContractProduct().getContract().getId());
		model = baseDao.get(ExtCproduct.class, model.getId());
		//购销合同的总金额中减去附件的金额
		contract.setTotalAmount(contract.getTotalAmount()-model.getAmount());
		//维护附件数
		contract.setExtCproductNum(contract.getExtCproductNum()-1);
		//删除附件
		baseDao.deleteById(ExtCproduct.class, model.getId());
		//更新购销合同
		baseDao.saveOrUpdate(contract);
	}

}
