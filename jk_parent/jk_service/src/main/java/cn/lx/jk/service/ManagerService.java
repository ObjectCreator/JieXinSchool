package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.Manager;

public interface ManagerService {

	/**条件进行查询
	 * @param string
	 * @param id
	 * @return
	 */
	List<Manager> findByCondition(String hql, Object... params);

	/**根据id查询管理对象
	 * @param id
	 * @return
	 */
	Manager get(String id);

	/**保存管理对象
	 * @param myManager
	 */
	void save(Manager myManager);

}
