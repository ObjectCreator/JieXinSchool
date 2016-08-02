package cn.lx.jk.service.impl;

import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Module;
import cn.lx.jk.service.ModuleService;
import cn.lx.jk.utils.Page;

public class ModuleServiceImpl implements ModuleService{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void findByPage(String hql, Page page, Object... params) {
		// TODO Auto-generated method stub
		baseDao.findPage(hql, page, Module.class, params);
	}
	@Override
	public void save(Module module) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(module);
	}
	@Override
	public Module findById(String id) {
		// TODO Auto-generated method stub
		return baseDao.get(Module.class, id);
	}
	@Override
	public void update(Module module) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(module);
	}
	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for(String id :ids){
			Module module = baseDao.get(Module.class, id);
			if(module != null){
				baseDao.deleteById(Module.class, id);
			}
		}
	}
	@Override
	public List<Module> findAll() {
		// TODO Auto-generated method stub
		return baseDao.find("from Module order by orderNo ", Module.class, null);
	}
	@Override
	public List<Module> findByCondition(String hql, String id) {
		if(id != null){
			return baseDao.find(hql, Module.class, new Object[]{id});
		}else{
			return baseDao.find(hql, Module.class,null);
		}
		
	}
	
}
