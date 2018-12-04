package com.tjzs.zscms.user.service;

import java.util.List;

import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.dao.impl.UserDao;
import com.tjzs.zscms.user.dao.impl.UserDaoImpl;

public class UserService {
	/**
	 * 用户的业务逻辑处理层
	 * */
	// 采用多态的形式
	UserDao ud = new UserDaoImpl();

	// 判断是否有相应用户信息，成功返回信息，失败返回null
	public UserBean userLogin(UserBean user) throws SysException,
			BusinessException {
		List<UserBean> users = ud.login(user);
		if (users == null || users.size() == 0) {
			throw new BusinessException("此用户不存在", 400);
		}
		return users.get(0);
	}

	// 新增用户
	/*
	 * 判断吗用户名和有邮箱是否重复 如果重复，抛出异常，然后将异常信息放到页面上面 1成功 0失败
	 */
	public int insertUser(UserBean user) throws SysException, BusinessException {
		List<UserBean> users1 = ud.queryByLoginname(user.getLoginname());
		if (users1 != null && users1.size() != 0) {// 用户名重复
			throw new BusinessException("用户名重复，请重新输入", 101);
		}
		List<UserBean> users2 = ud.queryByEmail(user.getEmail());
		if (users2 != null && users2.size() != 0) {// 用户名重复
			throw new BusinessException("邮箱重复，请重新输入", 102);
		}
		// 如果执行到此表示用户名和邮箱都不重复
		return ud.insertUser(user);
	}

	// 查询所有用户信息
	/**
	 * page表示查询的当前页数 size：条数
	 * */
	public List<UserBean> queryByPage(int page, int size) throws SysException {
		int start = (page - 1) * size;
		return ud.queryByPage(start, size);

	}

	// 根据id查询用户信息
	public UserBean queryUserById(int id) throws SysException {
		List<UserBean> users = ud.queryUserById(id);
		return users.get(0);

	}

	// 修改用户信息
	public int updateUser(UserBean user) throws SysException {
		int user1 = ud.updateUser(user);
		return user1;

	}

	// 删除用户信息
	public int delectUser(int id) throws SysException {
		int user = ud.delectUser(id);
		return user;
	}

	/* 查询总页数 */
	public int queryPageCount(int size) throws SysException {
		// 将用户的总记录转换为总页数
		int userCount = ud.queryUserCounts();
		if (userCount % size == 0) {// 表示总页数能够整除每页查询的数据量
			// 总页数
			return userCount / size;
		} else {
			return userCount / size + 1;
		}
	}

}