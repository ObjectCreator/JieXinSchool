package cn.lx.jk.service.impl;

import java.util.List;
import java.util.Set;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Contract;
import cn.lx.jk.domain.ContractProduct;
import cn.lx.jk.domain.ExtCproduct;
import cn.lx.jk.service.ContractProductService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.UtilFuns;


public class ContractProductServiceImpl implements ContractProductService {
	//注入DAO
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void findContractProductByPage(String hql, Page page, Object... params) {
		baseDao.findPage(hql, page, ContractProduct.class, params);
		
	}


	public ContractProduct findContractProductById(String id) {
		return baseDao.get(ContractProduct.class, id);
	}


	public List<ContractProduct> findAll() {
		return baseDao.find("from ContractProduct ", ContractProduct.class, null);
	}


	public void saveOrUpdate(ContractProduct model) {
		Double amount = 0.0d;
		Contract contract  = baseDao.get(Contract.class, model.getContract().getId());;
		//执行新增
		if(UtilFuns.isEmpty(model.getId())){
			//计算当前获取的金额
			if(UtilFuns.isNotEmpty(model.getPrice())&&UtilFuns.isNotEmpty(model.getCnumber())){
				amount = model.getPrice()*model.getCnumber();
			}
			//获取购销合同的总金额，新增货物的金额
			model.setAmount(amount);
			System.out.println(contract.getProductNum());
			contract.setProductNum(contract.getProductNum()+1);
			contract.setTotalAmount(contract.getTotalAmount()+amount);
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
		//获取当前货物
		ContractProduct contractProduct = baseDao.get(ContractProduct.class, id);
		if(UtilFuns.isNotEmpty(contractProduct)){
			Contract contract = contractProduct.getContract();
			int extNum = contract.getExtCproductNum();
			//获取所有的附件
			Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
			for(ExtCproduct extCproduct:extCproducts){
				//减去附件的总金额
				contract.setTotalAmount(contract.getTotalAmount()-extCproduct.getAmount());
				contract.setExtCproductNum(extNum-1);
			}
			contract.setProductNum(contract.getProductNum()-1);
			//减去货物金额
			contract.setTotalAmount(contract.getTotalAmount()-contractProduct.getAmount());
			
			//更新购销合同的附件数
			
			baseDao.deleteById(ContractProduct.class, id);
			
			//更新购销合同
			baseDao.saveOrUpdate(contract);
		}
	}


	@Override
	public List<ContractProduct> findByCondition(String hql, Class entityClass, Object... params) {
		// TODO Auto-generated method stub
		System.out.println(1);
		return baseDao.find(hql, entityClass, params);
	}

}
