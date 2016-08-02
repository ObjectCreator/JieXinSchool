package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.Contract;
import cn.lx.jk.utils.Page;


public interface ContractService {

	/**
	 * 实现分页查询
	 * @param hql
	 * @param page
	 */
	public void findContractByPage(String hql ,Page page ,Object ...params);
	
	/**
	 * 根据id,得到对象
	 * @param id
	 * @return
	 */
	public Contract findContractById(String id);

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<Contract> findAll();

	/**
	 * 保存或更新部门
	 * @param model
	 */
	public void saveOrUpdate(Contract model);

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

	/**通过条件进行查询
	 * @param hql
	 * @param dataTime
	 * @return
	 */
	public List<Contract> findByCondition(String hql, Object... params);
}
