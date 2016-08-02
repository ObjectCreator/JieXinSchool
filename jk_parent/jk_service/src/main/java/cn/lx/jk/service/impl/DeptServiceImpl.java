package cn.lx.jk.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.lx.jk.dao.BaseDao;

import cn.lx.jk.domain.Dept;
import cn.lx.jk.domain.Manager;
import cn.lx.jk.service.DeptService;
import cn.lx.jk.utils.Page;

public class DeptServiceImpl implements DeptService {
		
		private BaseDao baseDao;
		
		public void setDeptDao(BaseDao baseDao) {
			this.baseDao = baseDao;
		}

		/**保存部门信息
		 * @param dept
		 */
		@Override
		public void save(Dept dept) {
			// TODO Auto-generated method stub
			//如果没有父部门，就将父部门设为null
			if(dept.getParent() ==null || "".equals(dept.getParent().getId())){
				dept.setParent(null);
			}
			baseDao.saveOrUpdate(dept);
		}

		/**查询所有部门信息
		 * @return
		 */
		@Override
		public List<Dept> findAll() {
			// TODO Auto-generated method stub
			return baseDao.find("from Dept where state =1 ",Dept.class,null);
		}

		/**通过id查询部门信息
		 * @param id
		 * @return
		 */
		@Override
		public Dept findById(String id) {
			// TODO Auto-generated method stub
			return baseDao.get(Dept.class, id);
		}

		/**更新部门信息
		 * @param myDept
		 */
		@Override
		public void update(Dept myDept) {
			// TODO Auto-generated method stub
			//如果没有父部门，就将父部门设为null，同时如果更新的时候选择父部门和原部门名称
			if(myDept.getParent() ==null || "".equals(myDept.getParent().getId())|| myDept.getId().equals(myDept.getParent().getId())){
				myDept.setParent(null);
			}
			baseDao.saveOrUpdate(myDept);
		}

		
		//删除父部门同时删除子部门
		@Override
		public void deleteByIds(String[] ids) {
			//删除父部门同时删除子部门，需要进行递归删除
			for(String id:ids){
				//通过id查询部门
				Dept dept = baseDao.get(Dept.class, id);
				if(dept !=null){
					Object[] obj = {dept};
					//通过id查询子部门
					List<Dept> list = baseDao.find("from Dept where parent = ?", Dept.class, obj);
					//如果存在子部门，进行递归
					if(list != null && list.size()>0){
						StringBuffer stringBuffer  = new StringBuffer();
						//获取所有id
						for(Dept dept1:list){
							stringBuffer.append(dept1.getId()+",");
						}
						//将StringBuffer转换为数组
						deleteByIds(stringBuffer.toString().split(","));
					}	
					//查出有没有对应的管理表，如果有就删除
					List<Manager> managerList = baseDao.find("from Manager where typeId=?",Manager.class,new Object[]{id});
					if(managerList != null && managerList.size()>0){
						baseDao.deleteById(Manager.class, managerList.get(0).getId());
					}
					//通过id进行删除，递归要有跳出语句
					baseDao.deleteById(Dept.class, id);
	
				}
				
			}
		}
	
		
		/**删除父部门，同时将子部门的父部门设为空
		 * @param ids
		 */
		public void deleteByIds1(String[] ids) {
			
			for(String id:ids){
				//通过id查询部门
				Dept dept = baseDao.get(Dept.class, id);
				if(dept != null){
					Object[] obj = {dept};
					//通过id查询子部门
					List<Dept> list = baseDao.find("from Dept where parent = ?", Dept.class, obj);
					//如果存在子部门，将子部门的父部门设为null，从数据库返回的List不会为null,只可能是size=0
					if(list !=null && list.size()>0){
						for(Dept dept1 :list){
							dept1.setParent(null);
							baseDao.saveOrUpdate(dept1);
						}
					}
					//删除父部门
					baseDao.deleteById(Dept.class, id);
				}
			}
		}
		/**分页查询
		 * @param hql
		 * @param page
		 * @param params
		 */
		@Override
		public void fingByPage(String hql, Page page, Object... params) {
			// TODO Auto-generated method stub
			baseDao.findPage(hql, page, Dept.class, params);
		}

		/**
		 * 父部门停用，子部门也停用
		 */
		@Override
		public void deleteByIds2(String[] ids) {
			// TODO Auto-generated method stub
			//删除父部门同时删除子部门，需要进行递归删除
			for(String id:ids){
				//通过id查询部门
				Dept dept = baseDao.get(Dept.class, id);
				if(dept !=null){
					Object[] obj = {dept};
					//通过id查询子部门
					List<Dept> list = baseDao.find("from Dept where parent = ?", Dept.class, obj);
					//如果存在子部门，进行递归
					if(list != null && list.size()>0){
						StringBuffer stringBuffer  = new StringBuffer();
						//获取所有id
						for(Dept dept1:list){
							stringBuffer.append(dept1.getId()+",");
						}
						//将StringBuffer转换为数组
						deleteByIds(stringBuffer.toString().split(","));
					}	
					//通过id进行删除，递归要有跳出语句
					dept.setState(0);
					baseDao.saveOrUpdate(dept);
						
				}
				
			}
		}

		/**父部门的状态，停用，子部门的父部门设为null
		 * @param ids
		 */
		@Override
		public void deleteByIds4(String[] ids) {
			for(String id:ids){
				//通过id查询部门
				Dept dept = baseDao.get(Dept.class, id);
				if(dept != null){
					Object[] obj = {dept};
					//通过id查询子部门
					List<Dept> list = baseDao.find("from Dept where parent = ?", Dept.class, obj);
					//如果存在子部门，将子部门的父部门设为null，从数据库返回的List不会为null,只可能是size=0
					if(list !=null && list.size()>0){
						for(Dept dept1 :list){
							dept1.setParent(null);
							baseDao.saveOrUpdate(dept1);
						}
					}
					//删除父部门
					dept.setState(0);
					baseDao.saveOrUpdate(dept);
				}
			}
			
		}

		@Override
		public void save(Dept dept, Manager manager) {
			// TODO Auto-generated method stub
			save(dept);
			manager.setTypeId(dept.getId());
			manager.setType("部门");
			baseDao.saveOrUpdate(manager);
		}

		@Override
		public void update(Dept myDept, Manager manager) {
			// TODO Auto-generated method stub
			update(myDept);
			baseDao.saveOrUpdate(manager);
			
		}
		
}
