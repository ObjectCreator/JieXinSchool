package cn.lx.jk.service.impl;

import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Role;
import cn.lx.jk.service.RoleService;
import cn.lx.jk.utils.Page;

public class RoleServiceImpl implements RoleService {
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void findByPage(String string, Page page,Object... params) {
		// TODO Auto-generated method stub
		baseDao.findPage(string, page, Role.class, params);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return baseDao.find("from Role ", Role.class, null);
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(role);
	}

	@Override
	public Role findById(String id) {
		// TODO Auto-generated method stub
		return baseDao.get(Role.class, id);
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for(String id :ids){
			Role role = baseDao.get(Role.class, id);
			if(role!= null){
				baseDao.deleteById(Role.class, id);
			}
		}
	}

	@Override
	public void update(Role myRole) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(myRole);
	}
	
}
