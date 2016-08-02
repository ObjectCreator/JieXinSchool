package cn.lx.jk.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Factory;
import cn.lx.jk.domain.User;
import cn.lx.jk.service.FactoryService;
import cn.lx.jk.utils.DateConverter;
import cn.lx.jk.utils.Page;
import cn.lx.jk.utils.SysConstant;
import cn.lx.jk.utils.UtilFuns;



public class FactoryServiceImpl implements FactoryService {
	//注入DAO
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void findFactoryByPage(String hql, Page page, Object... params) {
		baseDao.findPage(hql, page, Factory.class, params);
		
	}


	public Factory findFactoryById(String id) {
		return baseDao.get(Factory.class, id);
	}


	public List<Factory> findAll() {
		return baseDao.find("from Factory where state=1", Factory.class, null);
	}


	public void saveOrUpdate(Factory model) {
		User user=(User) ActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO);
		if(UtilFuns.isEmpty(model.getId())){
			model.setCreateBy(user.getUserName());
			model.setCreateDept(user.getDept().getDeptName());
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
			model.setCreateTime(new Date());
			model.setState(0);			
		}		
		model.setUpdateBy(user.getUserName());
		model.setUpdateTime(new Date());
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
		
		
		
		//第二种方法
		Factory obj = baseDao.get(Factory.class, id);
		obj.setState(3);
		baseDao.saveOrUpdate(obj);
		
	}


	@Override
	public List<Factory> find(String hql, Class<Factory> class1, Object... params) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, class1, params);
	}

}
