package com.tjzs.zscms.Tchannel.service;

import java.util.List;

import com.tjzs.zscms.Tchannel.bean.TchannelBean;
import com.tjzs.zscms.Tchannel.dao.impl.TcannelDaoImpl;
import com.tjzs.zscms.Tchannel.dao.impl.TchannelDao;
import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;

/**
 * 栏目信息的业务逻辑层
 * */
public class TchannelService {
	// 多态形式
	TchannelDao tD = new TcannelDaoImpl();

	// 查询所有栏目的方法 （文章）
	public List<TchannelBean> querAll() throws SysException {
		return tD.queryAllchannle();
	}

	/**
	 * 查询全部频道的方法
	 * 
	 * @throws SysException
	 */
	/**
	 * page表示查询的当前页数 size：条数
	 * */
	public List<TchannelBean> queryByPage(int page, int size) throws SysException {
		int start = (page - 1) * size;
		return tD.queryByPage(start, size);

	}
	/**
	 * 新增频道方法
	 * 
	 * @throws SysException
	 * @throws BusinessException
	 */
	public int addChannel(TchannelBean channel) throws SysException,
			BusinessException {
		// 调用通过栏目名查找频道方法
		List<TchannelBean> list = tD.queryChannelByCname(channel.getCname());
		// 判断栏目名是否重复
		if (list != null && list.size() != 0) {
			throw new BusinessException("频道已存在，请重新输入", 202);
		}
		// 调用数据层中的新增频道的方法
		// 返回结果
		return tD.addChannel(channel);
	}

	/**
	 * 通过id查询频道方法
	 * 
	 * @throws SysException
	 */
	public TchannelBean queryChannelById(int id) throws SysException {
		// 调用数据层中的通过id查询频道的方法
		List<TchannelBean> list = tD.queryChannelById(id);
		// 返回结果
		return list.get(0);
	}

	/**
	 * 修改频道方法
	 * 
	 * @throws SysException
	 */
	public int updateChannel(TchannelBean channel) throws SysException {
		// 调用数据层中的方法
		int result = tD.updateChannel(channel);
		// 返回结果
		return result;
	}

	/**
	 * 删除方法
	 * 
	 * @throws SysException
	 */
	public int deleteChannel(int id) throws SysException {
		// 调用数据层中的方法
		int result = tD.deleteChannel(id);
		// 返回值
		return result;
	}
	/* 查询总页数 */
	public int queryPageCount(int size) throws SysException {
		// 将用户的总记录转换为总页数
		int ChannelCount = tD.queryChannelCounts();
		if (ChannelCount % size == 0) {// 表示总页数能够整除每页查询的数据量
			// 总页数
			return ChannelCount / size;
		} else {
			return ChannelCount / size + 1;
		}
	}
}
