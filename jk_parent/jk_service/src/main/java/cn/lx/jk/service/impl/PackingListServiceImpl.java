package cn.lx.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Export;
import cn.lx.jk.domain.PackingList;

import cn.lx.jk.service.PackingListService;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.UtilFuns;


/**
 * @Description:	PackingListService接口
 * @Author:			黑马程序员	    无德皇叔
 * @Company:		http://java.lx.cn
 * @CreateDate:		2016-7-15 15:44:05
 */

public class PackingListServiceImpl implements PackingListService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params) {
		return baseDao.find(hql, PackingList.class, params);
	}

	public PackingList get(Class<PackingList> entityClass, Serializable id) {
		return baseDao.get(PackingList.class, id);
	}

	

	public void saveOrUpdate(PackingList entity) {
		
		//执行新增方法
		if(entity.getId()==null){								//代表新增
			//需要拼接exportIds
			String[] ids = entity.getExportIds().split(", ");
			//用于拼接合同号，同时将报运单的状态设置为装箱
			StringBuilder stringBuilder = new StringBuilder();
			for(String id :ids){
				Export export = baseDao.get(Export.class, id);
				stringBuilder.append(export.getCustomerContract()+"|"); 
				export.setState(3);//3表示已装箱
				//更新报运单
				baseDao.saveOrUpdate(export);
			}
			//去掉最后一个分隔符
			stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
			//设置装箱单的合同号
			entity.setExportNos(stringBuilder.toString());
			String exportIds = UtilFuns.joinStr(ids, ",");
			//设置装箱单的报运单id集合
			entity.setExportIds(exportIds);	
			
			entity.setState(0);									//状态：0草稿1已装箱 
			
		}else{
			//执行保存方法
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<PackingList> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<PackingList> entityClass, Serializable id) {
		//baseDao.deleteById(PackingList.class, id);
		//不进行删除，将状态设为-1
		PackingList pl = baseDao.get(entityClass, id);
		if( pl != null){
			pl.setState(-1);
			Export export = baseDao.get(Export.class, id);
			if(export !=null){
				export.setState(2);
				baseDao.saveOrUpdate(export);
			}
			
			baseDao.saveOrUpdate(pl);
			
		}
	
	}

	public void delete(Class<PackingList> entityClass, Serializable[] ids) {
		for(Serializable id:ids){
			deleteById(entityClass,id);
		}
	
	}

	@Override
	public Page<PackingList> findPage(String hql, cn.lx.jk.utils.Page<PackingList> page,
			Class<PackingList> entityClass, Object[] params) {
		// TODO Auto-generated method stub
		return baseDao.findPage(hql, page, entityClass, params);
	}

}

