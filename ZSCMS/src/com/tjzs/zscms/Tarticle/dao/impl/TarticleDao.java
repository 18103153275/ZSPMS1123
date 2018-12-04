package com.tjzs.zscms.Tarticle.dao.impl;

import java.util.List;

import com.tjzs.zscms.Tarticle.bean.TarticleBean;
import com.tjzs.zscms.exception.SysException;

/**
 * 对Tarticle表进行crud操作
 * */
public interface TarticleDao {
	// 根据条件查询，参数为查询条件，返回的是所有用户的集合
	public List<TarticleBean> queryByTiaoJian(String TiaoJian, Object[] obj)
			throws SysException;

	// 查询用户表的全部信息
	// 查询用户表的全部信息
	/**
	 * start:表示分页查询的起始值 size：表示每页显示多少条
	 * */
	public List<TarticleBean> queryByPage(int start, int size)
			throws SysException;

	// 用户新增
	public int insertTarticle(TarticleBean tar) throws SysException;

	// 根据id查询用户信息
	public List<TarticleBean> queryTarticleById(int id) throws SysException;

	// 修改用户信息
	public int updateTarticle(TarticleBean tar) throws SysException;

	// 删除用户信息
	public int delectTarticle(int id) throws SysException;

	public List<TarticleBean> queryByTitle(String title) throws SysException;

	// 查询用户的总记录条数
	public int queryTarticleCounts() throws SysException;
}
