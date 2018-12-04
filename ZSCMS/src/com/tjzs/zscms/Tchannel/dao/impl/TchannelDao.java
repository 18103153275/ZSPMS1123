package com.tjzs.zscms.Tchannel.dao.impl;

import java.util.List;

import com.tjzs.zscms.Tchannel.bean.TchannelBean;
import com.tjzs.zscms.exception.SysException;

/**
 * 栏目的数据====对tchannel表的操作
 * */
public interface TchannelDao {

	/**
	 * 查询所有栏目，返回所有栏目信息 （文章）
	 * 
	 * @return
	 * @throws SysException
	 */
	public List<TchannelBean> queryAllchannle() throws SysException;

	/**
	 * 带条件的查询栏目
	 * 
	 * @throws SysException
	 */
	public List<TchannelBean> queryChannelByTiaoJian(String sql, Object[] obj)
			throws SysException;

	/**
	 * 查询全部的栏目
	 * 
	 * @throws SysException
	 */
	/**
	 * start:表示分页查询的起始值 size：表示每页显示多少条
	 * */
	public List<TchannelBean> queryByPage(int start, int size)
			throws SysException;


	/**
	 * 新增栏目方法
	 * 
	 * @throws SysException
	 */
	public int addChannel(TchannelBean channel) throws SysException;

	/**
	 * 通过id获取栏目
	 * 
	 * @throws SysException
	 */
	public List<TchannelBean> queryChannelById(int id) throws SysException;

	/**
	 * 通过频道名获取栏目
	 * 
	 */
	public List<TchannelBean> queryChannelByCname(String cname)
			throws SysException;

	/**
	 * 修改栏目方法
	 * 
	 * @throws SysException
	 */
	public int updateChannel(TchannelBean channel) throws SysException;

	/**
	 * 删除栏目方法
	 * 
	 * @throws SysException
	 */
	public int deleteChannel(int id) throws SysException;

	// 查询用户的总记录条数
	public int queryChannelCounts() throws SysException;
}
