package cn.lx.jk.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.LoginLog;
import cn.lx.jk.domain.Manager;
import cn.lx.jk.domain.User;
import cn.lx.jk.domain.UserInfo;
import cn.lx.jk.service.UserService;
import cn.lx.jk.utils.Encrypt;
import cn.lx.jk.utils.Page;

public class UserServiceImpl implements UserService {
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString().replace("-", "");
		user.setId(id);
		user.getUserInfo().setId(id);
		user.setCreateTime(new Date());
		//设置默认密码,同时进行加密
		user.setPassword(Encrypt.md5(user.getPassword(), user.getUserName()));
		baseDao.saveOrUpdate(user);
	
		
	}

	@Override
	public void findByPage(String hql, Page page, Object... params) {
		// TODO Auto-generated method stub
		baseDao.findPage(hql, page, User.class, params);
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return baseDao.get(User.class,id);
	}

	@Override
	public void update(User myUser) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(myUser);
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for(String id:ids){
			//查询用户信息是否存在，
			User user = baseDao.get(User.class,id);
			if(user !=null){
				//手动维护管理表
				List<Manager> list = baseDao.find("from Manager where typeId = ? or userId= ?", Manager.class, new Object[]{id,id});
					if(list !=null && list.size()>0){
						for(Manager m:list){
							//删除记录
							baseDao.deleteById(Manager.class, m.getId());
						}
					}
					
				//如果存在就进行删除
				baseDao.deleteById(User.class,id);
			}
		}
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return baseDao.find("from User ", User.class, null);
	}

	@Override
	public User findByName(String userName) {
	
		List<User> list = baseDao.find("from User where userName =?", User.class, new Object[]{userName});
		if(list !=null &&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveLoginLog(LoginLog loginLog) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(loginLog);
	}

	@Override
	public void save(User user, Manager manager) {
		// TODO Auto-generated method stub
		save(user);
		//设置管理者的所管理用户的id
		manager.setTypeId(user.getId());
		//设置管理的类型为员工
		manager.setType("员工");
		//保存管理
		baseDao.saveOrUpdate(manager);
	}

	@Override
	public void update(User myUser, Manager myManager) {
		// TODO Auto-generated method stub
		update(myUser);
		baseDao.saveOrUpdate(myManager);
	}

	@Override
	public User findByCondition(String hql, Object... params) {
		// TODO Auto-generated method stub
		  List<User> list = baseDao.find(hql, User.class, params);
		  if(list != null && list.size()>0){
			  return list.get(0);
		  }
		  return null;
	}

	
	
}
