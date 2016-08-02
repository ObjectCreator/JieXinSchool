package cn.lx.jk.service;

import java.util.List;

import cn.lx.jk.domain.LoginLog;
import cn.lx.jk.domain.Manager;
import cn.lx.jk.domain.User;
import cn.lx.jk.domain.UserInfo;
import cn.lx.jk.utils.Page;

public interface UserService {

	/**保存用户详情，以及对应的用户扩展
	 * @param user
	 * @param userInfo
	 */
	void save(User user);

	/**分页进行查询
	 * @param string
	 * @param page
	 */
	void findByPage(String string, Page page,Object... params);

	/**根据Id查询用户
	 * @param id
	 * @return
	 */
	User findById(String id);

	/**更新用户信息
	 * @param myUser
	 */
	void update(User myUser);

	/**根据id进行删除
	 * @param ids
	 */
	void delete(String[] ids);

	/**查询所有用户
	 * @return
	 */
	List<User> findAll();

	/**通过用户名查询用户
	 * @param userName
	 * @return
	 */
	User findByName(String userName);

	/**保存登陆日志
	 * @param loginLog
	 */
	void saveLoginLog(LoginLog loginLog);

	/**保存用户的同时保存用户的管理者
	 * @param user
	 * @param manager
	 */
	void save(User user, Manager manager);

	/**更新用户信息的同时，更新用户管理者
	 * @param myUser
	 * @param myManager
	 */
	void update(User myUser, Manager myManager);

	/**通过条件查询用户信息
	 * @param string
	 * @param userName
	 * @return
	 */
	User findByCondition(String hql, Object... params);

}
