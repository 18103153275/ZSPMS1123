package com.tjzs.zscms.user.dao.impl;

import java.util.List;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;

/**
 * 对tuser表进行crud操作
 * */
public interface UserDao {
	// 一个map集合对应一个userbean
	public List<UserBean> login(UserBean user) throws SysException;

	// 根据条件查询，参数为查询条件，返回的是所有用户的集合
	public List<UserBean> queryByTiaoJian(String TiaoJian, Object[] obj)
			throws SysException;

	// 查询用户表的全部信息
	/**
	 * start:表示分页查询的起始值 size：表示每页显示多少条
	 * */
	public List<UserBean> queryByPage(int start, int size) throws SysException;

	// 用户新增
	public int insertUser(UserBean user) throws SysException;

	// 根据用户名查询用户信息
	public List<UserBean> queryByLoginname(String loginname)
			throws SysException;

	// 根据邮箱查询用户信息
	public List<UserBean> queryByEmail(String email) throws SysException;

	// 根据id查询用户信息
	public List<UserBean> queryUserById(int id) throws SysException;

	// 修改用户信息
	public int updateUser(UserBean user) throws SysException;

	// 删除用户信息
	public int delectUser(int id) throws SysException;

	// 查询用户的总记录条数
	public int queryUserCounts() throws SysException;
}
