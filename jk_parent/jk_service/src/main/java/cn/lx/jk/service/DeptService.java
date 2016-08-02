package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.Dept;
import cn.lx.jk.domain.Manager;
import cn.lx.jk.utils.Page;

/**
 * @author liuxuan
 *
 */
public interface DeptService {

	/**保存部门信息
	 * @param dept
	 */
	void save(Dept dept);

	/**查询所有部门信息
	 * @return
	 */
	List<Dept> findAll();

	/**根据id查询部门信息
	 * @param id
	 * @return
	 */
	Dept findById(String id);

	/**更新部门信息
	 * @param myDept
	 */
	void update(Dept myDept);

	

	/**根据用户选中的Ids进行删除部门信息
	 * @param ids
	 */
	void deleteByIds(String[] ids);

	/**分页进行查询
	 * @param string
	 * @param page
	 * @param params
	 */
	void fingByPage(String string, Page page,Object... params );

	/**父类删除，子类的父部门设为null
	 * @param ids
	 */
	void deleteByIds1(String[] ids);

	/**父类停用，子类停用
	 * @param ids
	 */
	void deleteByIds2(String[] ids);

	/**父类停用，子类父部门设为null
	 * @param ids
	 */
	void deleteByIds4(String[] ids);

	/**保存部门同时保存管理表
	 * @param dept
	 * @param manager
	 */
	void save(Dept dept, Manager manager);

	/**更新部门同时更新manager
	 * @param myDept
	 * @param manager
	 */
	void update(Dept myDept, Manager manager);

}
