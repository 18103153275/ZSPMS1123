package com.tjzs.zscms.Tchannel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.Tchannel.bean.TchannelBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.util.DButil;

/**
 * 查询栏目表中所有信息
 * */
public class TcannelDaoImpl implements TchannelDao {
	// 连接数据库
	DButil db = new DButil();

	// 查询所有栏目，返回所有栏目信息 (文章)
	@Override
	public List<TchannelBean> queryAllchannle() throws SysException {
		// SQL语句
		String sql = "select * from  tchannel ";
		// 调用db中的查询方法
		List<Map<String, String>> list = db.execQuery(sql, null);
		// 创建集合存储信息
		List<TchannelBean> adverts = new ArrayList<TchannelBean>();
		// 遍历
		for (Map<String, String> map : list) {
			// 创建bean
			TchannelBean channe = new TchannelBean();

			channe.setId(Integer.parseInt(map.get("id")));
			channe.setCname(map.get("cname"));

			// 添加到集合中
			adverts.add(channe);
		}

		return adverts;
	}

	/**
	 * 带条件的查询频道
	 * 
	 * @throws SysException
	 */
	@Override
	public List<TchannelBean> queryChannelByTiaoJian(String sql, Object[] obj)
			throws SysException {
		// 调用db中的查询方法
		List<Map<String, String>> channel = db.execQuery(sql, obj);
		// 创建集合list用于存放数据
		List<TchannelBean> channels = new ArrayList<TchannelBean>();
		// 循环遍历集合
		for (Map<String, String> map : channel) {
			// 创建channel对象
			TchannelBean channel1 = new TchannelBean();
			// 将map中的数据封装进集合中
			channel1.setId(Integer.parseInt(map.get("id")));
			channel1.setCname(map.get("cname"));
			// channel1.setPid(Integer.parseInt(map.get("pid")));
			channel1.setLev(Integer.parseInt(map.get("lev")));
			channel1.setIsleaf(Integer.parseInt(map.get("isleaf")));
			channel1.setSort(Integer.parseInt(map.get("sort")));
			if (Integer.parseInt(map.get("isleaf")) == 1) {
				channel1.setIsleafTxt("是");
			} else {
				channel1.setIsleafTxt("否");
			}
			if (map.get("pname") != null) {
				channel1.setPname(map.get("pname"));
			}
			if (Integer.parseInt(map.get("lev")) == 1) {
				channel1.setLevTxt("一级栏目");
			} else {
				channel1.setLevTxt("二级栏目");
			}
			// 将数据封装放入集合中
			channels.add(channel1);
		}
		return channels;
	}

	/**
	 * 查询全部的栏目
	 * 
	 * @throws SysException
	 */
	@Override
	public List<TchannelBean> queryByPage(int start, int size) throws SysException {
		// 查询所有，不需要条件和参数，那么直接给null
		String sql = "select c.*,a.pname from tchannel c left join (select id,cname pname from tchannel)a on a.id=c.pid order by c.id  desc limit ?,?";
		Object[] obj = { start, size };
		return queryChannelByTiaoJian(sql, obj);
	}

	/**
	 * 增加栏目的方法
	 */
	@Override
	public int addChannel(TchannelBean channel) throws SysException {
		// 创建sql语句
		String sql = "insert into tchannel values(null,?,?,?,?,?)";
		// 给参数赋值
		Object[] obj = { channel.getCname(), channel.getPid(),
				channel.getLev(), channel.getIsleaf(), channel.getSort() };
		// 调用db中的新增方法
		int result = db.execUpdate(sql, obj);
		// 返回结果
		return result;
	}

	/**
	 * 通过id获取栏目
	 * 
	 * @throws SysException
	 */
	@Override
	public List<TchannelBean> queryChannelById(int id) throws SysException {
		// 创建sql语句
		String sql = "select * from tchannel where id=?";
		// 给参数赋值
		Object[] obj = { id };
		// 调用本类中的带条件查询方法
		List<TchannelBean> channel = this.queryChannelByTiaoJian(sql, obj);
		// 返回结果
		return channel;
	}

	/**
	 * 通过频道名获取栏目
	 * 
	 */
	@Override
	public List<TchannelBean> queryChannelByCname(String cname)
			throws SysException {
		// 创建sql语句
		String sql = "select * from tchannel where cname=?";
		// 给参数赋值
		Object[] obj = { cname };
		// 调用本类中的带条件查询方法
		List<TchannelBean> channel = this.queryChannelByTiaoJian(sql, obj);
		// 返回结果
		return channel;
	}

	/**
	 * 修改栏目方法
	 * 
	 * @throws SysException
	 */
	@Override
	public int updateChannel(TchannelBean channel) throws SysException {
		// 创建sql语句
		String sql = "update tchannel set cname=?,pid=?,lev=?,isleaf=?,sort=? where id=?";
		// 给参数赋值
		Object[] obj = { channel.getCname(), channel.getPid(),
				channel.getLev(), channel.getIsleaf(), channel.getSort(),
				channel.getId() };
		// 调用db中的修改方法
		int update = db.execUpdate(sql, obj);
		// 返回结果
		return update;
	}

	/**
	 * 删除栏目方法
	 * 
	 * @throws SysException
	 */
	@Override
	public int deleteChannel(int id) throws SysException {
		// 创建sql语句
		String sql = "delete from tchannel where id=?";
		// 给参数赋值
		Object[] obj = { id };
		// 调用db中的删除方法
		int result = db.execUpdate(sql, obj);
		// 返回结果
		return result;
	}
	/*查询用户的总记录条数*/
	@Override
	public int queryChannelCounts() throws SysException {
		String sql="select count(id) count from tchannel";
		List<Map<String, String>>  list=db.execQuery(sql, null);
		int ChannelCount=Integer.parseInt(list.get(0).get("count"));
		return ChannelCount;
	}

}
