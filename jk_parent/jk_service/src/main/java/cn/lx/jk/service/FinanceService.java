package cn.lx.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.lx.jk.domain.Finance;
import cn.lx.jk.utils.Page;

/**
 * @Description:	FinanceService接口
 * @Author:			麦芽的香气
 * @Company:		truest me
 * @CreateDate:		2016-7-16 11:30:02
 */

public interface FinanceService {
	/**
	 * 实现分页查询
	 * @param hql                 
	 * @param page
	 * @param params
	 */
	public List<Finance> find(String hql, Class<Finance> entityClass, Object[] params);
	/**
	 * 根据id,得到对象
	 * @param id
	 * @return
	 */
	public Finance get(Class<Finance> entityClass, Serializable id);
	/**
	 * 查询所有数据
	 * @return
	 */
	public Page<Finance> findPage(String hql, Page<Finance> page, Class<Finance> entityClass, Object[] params);
	
	/**
	 * 保存或更新数据
	 * @param model
	 */
	public void saveOrUpdate(Finance entity);
	
	/**
	 *批量保存或更新数据
	 * @param id
	 */
	public void saveOrUpdateAll(Collection<Finance> entitys);
	/**
	 *删除单条记录
	 * @param id
	 */
	public void deleteById(Class<Finance> entityClass, Serializable id);
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Class<Finance> entityClass, Serializable[] ids);
	/**
	 * 保存
	 * @param model
	 */
	public void save(Finance model);
}
