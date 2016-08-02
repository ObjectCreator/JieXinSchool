package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.Role;
import cn.lx.jk.utils.Page;

public interface RoleService {

	/**分页查询
	 * @param string
	 * @param page
	 */
	void findByPage(String string, Page page,Object... params);

	/**查询所有角色
	 * @return
	 */
	List<Role> findAll();

	/**保存角色
	 * @param role
	 */
	void save(Role role);

	/**通过id查询角色
	 * @param id
	 * @return
	 */
	Role findById(String id);

	/**删除角色
	 * @param ids
	 */
	void delete(String[] ids);

	/**更新角色
	 * @param myRole
	 */
	void update(Role myRole);

}
