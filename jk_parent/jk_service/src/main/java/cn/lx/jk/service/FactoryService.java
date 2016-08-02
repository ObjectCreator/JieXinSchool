package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.Factory;
import cn.lx.jk.utils.Page;

public interface FactoryService {

	/**
	 * 实现分页查询
	 * @param hql
	 * @param page
	 * @param params
	 */
	public void findFactoryByPage(String hql ,Page page ,Object ...params);
	
	/**
	 * 根据id,得到对象
	 * @param id
	 * @return
	 */
	public Factory findFactoryById(String id);

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<Factory> findAll();

	/**
	 * 保存或更新部门
	 * @param model
	 */
	public void saveOrUpdate(Factory model);

	/**
	 *删除单条记录
	 * @param id
	 */
	public void delete(String id);
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(String[] ids);

	public List<Factory> find(String string, Class<Factory> class1, Object... objects);
}
