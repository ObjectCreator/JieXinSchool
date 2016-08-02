package cn.lx.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.domain.Feedback;
import cn.lx.jk.utils.Page;


/**
 * @Description:	FeedbackService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-16 12:24:47
 */

public interface FeedbackService {

	public List<Feedback> find(String hql, Class<Feedback> entityClass, Object[] params);
	public Feedback get(Class<Feedback> entityClass, Serializable id);
	public Page<Feedback> findPage(String hql, Page<Feedback> page, Class<Feedback> entityClass, Object[] params);
	
	
	public void saveOrUpdate(Feedback entity);
	public void saveOrUpdateAll(Collection<Feedback> entitys);
	
	public void deleteById(Class<Feedback> entityClass, Serializable id);
	public void delete(Class<Feedback> entityClass, Serializable[] ids);
	/**根据id查询反馈意见
	 * @param class1
	 * @param id
	 * @return
	 */
	public Feedback findById(Class<Feedback> entityClass, String id);
}
