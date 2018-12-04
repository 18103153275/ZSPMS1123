package com.tjzs.zscms.Tadvert.dao.impl;

import java.util.List;

import com.tjzs.zscms.Tadvert.bean.TadvertBean;
import com.tjzs.zscms.exception.SysException;

	/**tadvert
 	* 对tadvert表进行crud操作
 	* */
	public interface TadvertDao {
	// 根据条件查询，参数为查询条件，返回的是所有用户的集合
	public List<TadvertBean> queryByTiaoJian(String TiaoJian, Object[] obj)
			throws SysException;

	// 查询用户表的全部信息
	/**
	 * start:表示分页查询的起始值 size：表示每页显示多少条
	 * */
	public List<TadvertBean> queryByPage(int start, int size) throws SysException;
	// 用户新增
	public int insertTadvert(TadvertBean tad) throws SysException;

	// 根据广告标题查询用户信息
	public List<TadvertBean> queryByTitle(String title)
			throws SysException;
	// 根据id查询用户信息
	public List<TadvertBean> queryTadvertById(int id) throws SysException;

	// 修改用户信息
	public int updateTadvert(TadvertBean tad) throws SysException;

	// 删除用户信息
	public int delectTadvert(int id) throws SysException;
	// 查询用户的总记录条数
	public int queryTadvertCounts() throws SysException;
}
