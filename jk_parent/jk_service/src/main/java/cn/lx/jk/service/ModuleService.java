package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.Module;
import cn.lx.jk.domain.Role;
import cn.lx.jk.utils.Page;

public interface ModuleService {

	/**分页查询
	 * @param string
	 * @param page
	 */
	void findByPage(String hql, Page page,Object... params);

	/**保存模块
	 * @param module
	 */
	void save(Module module);

	/**通过id进行查询
	 * @param id
	 * @return
	 */
	Module findById(String id);

	/**
	 * @param myRole
	 */
	void update(Module module);

	/**根据ids进行删除
	 * @param ids
	 */
	void delete(String[] ids);

	/**查询所有权限
	 * @return
	 */
	List<Module> findAll();

	/**通过条件查询模块
	 * @param hql
	 * @param id
	 * @return
	 */
	List<Module> findByCondition(String hql, String id);

	

}
