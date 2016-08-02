package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.ExportProduct;

import cn.lx.jk.utils.Page;


public interface ExportProductService {

	/**
	 * 实现分页查询
	 * @param hql
	 * @param page
	 */
	public void findExportProductByPage(String hql ,Page page ,Object ...params);
	
	/**
	 * 根据id,得到对象
	 * @param id
	 * @return
	 */
	public ExportProduct findExportProductById(String id);

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<ExportProduct> findAll();

	/**
	 * 保存或更新部门
	 * @param model
	 */
	public void saveOrUpdate(ExportProduct model);

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

	/**根据条件进行查询
	 * @param string
	 * @param id
	 * @return
	 */
	public List<ExportProduct> findByCondition(String hql, Object... params);
}
