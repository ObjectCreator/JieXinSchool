package cn.lx.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import cn.lx.jk.dao.BaseDao;
import cn.lx.jk.domain.Feedback;
import cn.lx.jk.service.FeedbackService;
import cn.lx.jk.utils.Page;


/**
 * @Description:	FeedbackService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 12:24:47
 */

public class FeedbackServiceImpl implements FeedbackService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Feedback> find(String hql, Class<Feedback> entityClass, Object[] params) {
		return baseDao.find(hql, Feedback.class, params);
	}

	public Feedback get(Class<Feedback> entityClass, Serializable id) {
		return baseDao.get(Feedback.class, id);
	}

	public Page<Feedback> findPage(String hql, Page<Feedback> page, Class<Feedback> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Feedback.class, params);
	}

	public void saveOrUpdate(Feedback entity) {
		if(entity.getId()==null){		
			//代表新增
			entity.setInputTime(new Date());//设置创建时间
			entity.setState(0);	//设置状态 0表示未解决								//状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Feedback> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Feedback> entityClass, Serializable id) {
		baseDao.deleteById(Feedback.class, id);
	}

	public void delete(Class<Feedback> entityClass, Serializable[] ids) {
		baseDao.delete(Feedback.class, ids);
	}

	@Override
	public Feedback findById(Class<Feedback> entityClass, String id) {
		// TODO Auto-generated method stub
		return baseDao.get(entityClass, id);
	}

}

