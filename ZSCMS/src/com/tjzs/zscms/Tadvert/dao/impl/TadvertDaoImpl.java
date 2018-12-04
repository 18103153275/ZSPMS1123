package com.tjzs.zscms.Tadvert.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.Tadvert.bean.TadvertBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.util.DButil;

/**
 * 对tadvert表进行crud操作
 * */
public class TadvertDaoImpl implements TadvertDao {
	// 创建DBUtil对象来连接数据库
	DButil db = new DButil();
	/* 根据条件查询用户信息 */
	@Override
	public List<TadvertBean> queryByTiaoJian(String sql, Object[] obj)
			throws SysException {
		List<Map<String, String>> list = db.execQuery(sql, obj);
		// 创建list集合来存储tad对象
		List<TadvertBean> tads = new ArrayList<TadvertBean>();
		// 遍历集合，将map集合和中的数据取出，然后直接封装到UserBean中
		for (Map<String, String> map : list) {
			// 创建TadvertBean对象
			TadvertBean Tadvertbean = new TadvertBean();
			// map集合是根据key来获取value
			Tadvertbean.setId(Integer.parseInt(map.get("id")));
			Tadvertbean.setTitle(map.get("title"));
			Tadvertbean.setContent(map.get("content"));
			Tadvertbean.setCrtime(map.get("crtime"));
			Tadvertbean.setCrman(map.get("crman"));
			// 将Tadvertbean对象加入集合中
			tads.add(Tadvertbean);
		}
		return tads;
	}

	/* 查询广告的所有信息 */
	@Override
	public List<TadvertBean> queryByPage(int start, int size) throws SysException {
		// 查询所有，不需要条件和参数，那么直接给null
		String sql = "select * from tadvert order by id desc limit ?,?";
		Object[] obj = { start, size };
		return queryByTiaoJian(sql, obj);
	}
	/* 新增广告 */
	@Override
	public int insertTadvert(TadvertBean tad) throws SysException {
		String sql = "insert into tadvert values(null,?,?,?,?)";
		Object[] obj = { tad.getTitle(), tad.getContent(),
				tad.getCrtime(),tad.getCrman()
				 };
		int result = db.execUpdate(sql, obj);
		return result;
	}

	/* 根据标题查询用户信息 */
	@Override
	public List<TadvertBean> queryByTitle(String title)
			throws SysException {
		String sql = "select * from tadvert where title=?";
		Object[] obj = {  title };
		return this.queryByTiaoJian(sql, obj);
	}
	/* 根据id查询用户信息 */
	@Override
	public List<TadvertBean> queryTadvertById(int id) throws SysException {
		String sql = "select * from tadvert where id=?";
		Object[] obj = { id };
		return this.queryByTiaoJian(sql, obj);
	}

	/* 修改广告信息 */
	@Override
	public int updateTadvert(TadvertBean tad) throws SysException {
		String sql = "update tadvert set title=?,content=?,crtime=?,crman=? where id=?";
		Object[] obj = { tad.getTitle(), tad.getContent(),
				tad.getCrtime(),tad.getCrman(),tad.getId()
				 };
		int result = db.execUpdate(sql, obj);
		return result;
	}

	/* 删除广告信息 */
	@Override
	public int delectTadvert(int id) throws SysException {
		String sql = "delete from tadvert where id=?";
		Object[] obj = { id };
		int result = db.execUpdate(sql, obj);
		return result;
	}
	/*查询用户的总记录条数*/
	@Override
	public int queryTadvertCounts() throws SysException {
		String sql="select count(id) count from tadvert";
		List<Map<String, String>>  list=db.execQuery(sql, null);
		int userCount=Integer.parseInt(list.get(0).get("count"));
		return userCount;
	}
}
