package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.Export;

import cn.lx.jk.utils.Page;


public interface ExportService {

	/**
	 * 实现分页查询
	 * @param hql
	 * @param page
	 */
	public void findExportByPage(String hql ,Page page ,Object ...params);
	
	/**
	 * 根据id,得到对象
	 * @param id
	 * @return
	 */
	public Export findExportById(String id);

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<Export> findAll();

	/**
	 * 保存或更新部门
	 * @param model
	 */
	public void saveOrUpdate(Export model);

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

	public List<Export> findByCondition(String hql,Object... params);
}
