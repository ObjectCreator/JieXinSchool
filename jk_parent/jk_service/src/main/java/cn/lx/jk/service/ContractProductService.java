package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.ContractProduct;
import cn.lx.jk.utils.Page;


public interface ContractProductService {

	/**
	 * 实现分页查询
	 * @param hql                 
	 * @param page
	 * @param params
	 */
	public void findContractProductByPage(String hql ,Page page ,Object ...params);
	
	/**
	 * 根据id,得到对象
	 * @param id
	 * @return
	 */
	public ContractProduct findContractProductById(String id);

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<ContractProduct> findAll();

	/**
	 * 保存或更新部门
	 * @param model
	 */
	public void saveOrUpdate(ContractProduct model);

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

	/**通过条件查询
	 * @param hql
	 * @param class1
	 * @return 
	 */
	public List<ContractProduct> findByCondition(String hql, Class entityClass,Object... params);


}
