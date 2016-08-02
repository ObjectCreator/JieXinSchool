package cn.lx.jk.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Contract;
import cn.lx.jk.domain.ContractProduct;
import cn.lx.jk.domain.Export;
import cn.lx.jk.domain.ExportProduct;
import cn.lx.jk.domain.ExtCproduct;
import cn.lx.jk.domain.ExtEproduct;
import cn.lx.jk.service.ExportService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.UtilFuns;

public class ExportServiceImpl implements ExportService {
	//注入DAO
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void findExportByPage(String hql, Page page, Object... params) {
		baseDao.findPage(hql, page, Export.class, params);
		
	}


	public Export findExportById(String id) {
		return baseDao.get(Export.class, id);
	}


	public List<Export> findAll() {
		return baseDao.find("from Export where state=1", Export.class, null);
	}

	/**
	 * 报运单的新增或修改操作，需要处理的业务
	 * 将报运的购销合同的状态设置为2  已报运
	 * 将购销合同下的货物转移到报运单对应的报运货物表中
	 * 将货物下的附件转义到报运单对应报运附件中
	 * 同时维护报运单中的货物数和附件数
	 *   
	 * */
	public void saveOrUpdate(Export model) {
		if(UtilFuns.isEmpty(model.getId())){
		
			//设置制单日期
			model.setInputDate(new Date());
			
			//设置状态为草稿
			model.setState(0);
			//设置初始化货物数和附件数
			model.setExtEproductNum(0);
			model.setExProductNum(0);
			//将购销合同的id集合转换为用逗号分割
			String[] ids = model.getContractIds().split(", ");
			//查询所有的购销合同，拼接合同及确认书号，实际就是够销合同的合同号，用空格隔开
			StringBuilder sb = new StringBuilder();
			for(String id :ids){
				
				Contract contract = baseDao.get(Contract.class, id);
				sb.append(contract.getContractNo()).append(" ");
				//购销合同的状态改为已上报
				contract.setState(2);
				//更新购销合同
				baseDao.saveOrUpdate(contract);
			}
			model.setCustomerContract(sb.toString());
			
			//购销合同id的集合
			String contractIds = UtilFuns.joinStr(ids, ",");
			
			//用于存储报运货物 
			Set<ExportProduct> exportProducts = new HashSet<ExportProduct>();
		
			//查询所有的货物 集合，根据购销合同的id
			//UtilFuns.joinInStr(ids)方法的作用是将字符串数组｛x,y,z｝转换为 'x','y','c'的格式
			String hql = "from ContractProduct where contract.id in("+UtilFuns.joinInStr(ids)+")";
			List<ContractProduct> list = baseDao.find(hql, ContractProduct.class,null);
			
			//遍历所有的货物
			for(ContractProduct cp :list){
				ExportProduct ep = new ExportProduct();
				//维护报运单的货物个数
				model.setExProductNum(model.getExProductNum()+1);
				//数据对拷
				BeanUtils.copyProperties(cp, ep);
				//id设为null
				ep.setId(null);
				//设置归属的报运单
				ep.setExport(model);
				
				//创建集合用于存储报运附件
				Set<ExtEproduct> extEproducts =  new HashSet<ExtEproduct>();
				
				//遍历所有的附件
				for(ExtCproduct ecp : cp.getExtCproducts()){
					
					ExtEproduct eep = new ExtEproduct();
					//维护报运单的附件个数
					model.setExtEproductNum(model.getExtEproductNum()+1);
					//数据对拷
					BeanUtils.copyProperties(ecp, eep);
					//设置id值由数据库自动生成
					eep.setId(null);
					//设置归属的货物
					eep.setExportProduct(ep);
					//添加到集合中
					
					extEproducts.add(eep);
				}
				ep.setExtEproducts(extEproducts);
				//添加到set集合中
				exportProducts.add(ep);
				//设置报运单包含的所有货物
				model.setExportProducts(exportProducts);
			}
		}else{
			
		}
		//执行保存或修改工作.级联操作，保存报运单的时候，会同时保存对应的报运货物，保存报运货物的时候会同时保存对应的报运附件
		baseDao.saveOrUpdate(model);
	}


	public void delete(String[] ids) {
		
		if(ids !=null && ids.length>0){
			for(String id :ids){
				this.delete(id);
			}
		}
	}
	public void delete(String id) {	
		Export obj = baseDao.get(Export.class, id);	
		baseDao.deleteById(Export.class, id);		
	}	
	public void saveOrUpdate1(Export export) {
		// TODO Auto-generated method stub
		//根据这个报运单的contractIds得到其包含的合同的id
		String contractIds = export.getContractIds();
		String[] ids = contractIds.split(", ");		
		StringBuilder sb=new StringBuilder();	
		Set<ExtEproduct> extEproducts=new HashSet<ExtEproduct>();
		Set<ExportProduct> exportProducts=new HashSet<ExportProduct>();
		//遍历每个合同.查询出合同下所有的货物
		for(String id:ids){
			Contract contract = baseDao.get(Contract.class, id);
			//将这个合同的状态置为2,表示为已报运
			contract.setState(2);		
			//此时查出了其中一个合同的id,开始拼接报运单的合同或确认证书
			sb.append(contract.getContractNo()).append(" ");		
			//保存状态到数据库
			baseDao.saveOrUpdate(contract);		
			Set<ContractProduct> contractProducts = contract.getContractProducts();
			//遍历每个货物
			for(ContractProduct conPro:contractProducts){
				//每得到一个货物对象,创建一个报运单下面的商品对象,实现属性对拷
				ExportProduct exportProduct=new ExportProduct();
				exportProduct.setBoxNum(conPro.getBoxNum());
				exportProduct.setCnumber(conPro.getCnumber());
				exportProduct.setFactory(conPro.getFactory());
				exportProduct.setOrderNo(conPro.getOrderNo());
				exportProduct.setPrice(conPro.getPrice());
				exportProduct.setProductNo(conPro.getProductNo());
				exportProduct.setPackingUnit(conPro.getPackingUnit());
				//同时设置该商品和报运单的关系
				exportProduct.setExport(export);
				//将这个商品加入到集合中
				exportProducts.add(exportProduct);
				//得到每个货物下面的附件
				Set<ExtCproduct> extCproducts = conPro.getExtCproducts();
				//再遍历每个附件
				for(ExtCproduct extPro:extCproducts){
					//没得到一个货物下面的附件,就新创建一个商品附件,实现属性对拷
					ExtEproduct extEproduct=new ExtEproduct();
					//使用工具类,实现属性对拷
					BeanUtils.copyProperties(extPro, extEproduct);
					//因为此时id也考进去了,要将其置空
					extEproduct.setId(null);
					//再设置商品附件和商品之间的关系
					extEproduct.setExportProduct(exportProduct);	
					//将这个附件对象,加入到集合中
					extEproducts.add(extEproduct);
				}
				//此时一个商品下面所有的商品附件加载完毕,设置其关系
				exportProduct.setExtEproducts(extEproducts);		
			}	
		}
		//此时已经加载完该报运单下面所有的商品了,设置其关系
		export.setExportProducts(exportProducts);
		//为报运单的合同或者报运单号赋值
		export.setCustomerContract(sb.toString());
		baseDao.saveOrUpdate(export);		
	}


	@Override
	public List<Export> findByCondition(String hql,Object... params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, Export.class, params);
	}
}
