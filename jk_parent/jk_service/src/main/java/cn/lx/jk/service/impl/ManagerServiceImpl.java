package cn.lx.jk.service.impl;

import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Manager;
import cn.lx.jk.service.ManagerService;

public class ManagerServiceImpl implements ManagerService{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public List<Manager> findByCondition(String hql, Object... params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, Manager.class, params);
	}
	@Override
	public Manager get(String id) {
		// TODO Auto-generated method stub
		return baseDao.get(Manager.class, id);
	}
	@Override
	public void save(Manager myManager) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(myManager);
	}
}
