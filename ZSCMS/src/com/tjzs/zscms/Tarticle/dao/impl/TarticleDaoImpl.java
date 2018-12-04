package com.tjzs.zscms.Tarticle.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.Tarticle.bean.TarticleBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.util.DButil;

/**
 * 对tadvert表进行crud操作
 * */
public class TarticleDaoImpl implements TarticleDao {
	// 创建DBUtil对象来连接数据库
	DButil db = new DButil();

	/* 根据条件查询用户信息 */
	@Override
	public List<TarticleBean> queryByTiaoJian(String sql, Object[] obj)
			throws SysException {
		List<Map<String, String>> list = db.execQuery(sql, obj);
		// 创建list集合来存储tad对象
		List<TarticleBean> tads = new ArrayList<TarticleBean>();
		// 遍历集合，将map集合和中的数据取出，然后直接封装到UserBean中
		for (Map<String, String> map : list) {
			// 创建TadvertBean对象
			TarticleBean Tarticlebean = new TarticleBean();
			// map集合是根据key来获取value
			Tarticlebean.setId(Integer.parseInt(map.get("id")));
			Tarticlebean.setTitle(map.get("title"));
			Tarticlebean.setContent(map.get("content"));
			Tarticlebean.setAuthor(map.get("author"));
			Tarticlebean.setCrtime(map.get("crtime"));
			//判断标题名不为空
			if (map.get("cname")!=null) {
				Tarticlebean.setCname(map.get("cname"));
			}
			Tarticlebean.setIsremod(Integer.parseInt(map.get("isremod")));
			Tarticlebean.setIshot(Integer.parseInt(map.get("ishot")));
			//判断推荐和热点
			if (Integer.parseInt(map.get("isremod"))==1) {
				Tarticlebean.setIsremodTex("可以推荐");
			}else {
				Tarticlebean.setIsremodTex("不可推荐");
			}
			if (Tarticlebean.getIshot()==1) {
				Tarticlebean.setIshotTex("是热点");
			}else {
				Tarticlebean.setIshotTex("不是热点");
			}
			
			// 将Tadvertbean对象加入集合中
			tads.add(Tarticlebean);
		}
		return tads;
	}

	/* 查询文章的所有信息 */
	@Override
	public List<TarticleBean> queryByPage(int start, int size) throws SysException {
		// 查询所有，不需要条件和参数，那么直接给null
		String sql = "select t.* ,tc.cname from tarticle t left join tchannel tc on t.channel=tc.id order by t.id desc limit ?,?";
		Object[] obj = { start, size };
		return queryByTiaoJian(sql, obj);
	}
	/* 新增文章 */
	@Override
	public int insertTarticle(TarticleBean tar) throws SysException {
		String sql = "insert into tarticle values(null,?,?,?,?,?,?,?)";
		Object[] obj = {	tar.getTitle(),tar.getContent(),
				tar.getAuthor(),tar.getCrtime(),
				tar.getChannel(),tar.getIsremod(),
				tar.getIshot()
				 };
		int result = db.execUpdate(sql, obj);
		return result;
	}
	/* 根据id查询文章信息 */
	@Override
	public List<TarticleBean> queryTarticleById(int id) throws SysException {
		String sql = "select * from tarticle  where id=?";
		Object[] obj = { id };
		return this.queryByTiaoJian(sql, obj);
	}

	/* 修改文章信息 */
	@Override
	public int updateTarticle(TarticleBean tar) throws SysException {
		String sql = "update tarticle set title=?,content=?,author=?,crtime=?,channel=?,isremod=?,ishot=? where id=? ";
		Object[] obj = { tar.getTitle(),tar.getContent(),
				tar.getAuthor(),tar.getCrtime(),
				tar.getChannel(),tar.getIsremod(),
				tar.getIshot(),tar.getId()};
		int result = db.execUpdate(sql, obj);
		return result;
	}

	/* 删除文章信息 */
	@Override
	public int delectTarticle(int id) throws SysException {
		String sql = "delete from tarticle where id=?";
		Object[] obj = { id };
		int result = db.execUpdate(sql, obj);
		return result;
	}
	/* 根据用标题查询文章信息 */
	@Override
	public List<TarticleBean> queryByTitle(String title)
			throws SysException {
		String sql = "select * from tarticle where title=?";
		Object[] obj = {  title };
		return this.queryByTiaoJian(sql, obj);
	}
	/*查询用户的总记录条数*/
	@Override
	public int queryTarticleCounts() throws SysException {
		String sql="select count(id) count from tarticle";
		List<Map<String, String>>  list=db.execQuery(sql, null);
		int TarticleCount=Integer.parseInt(list.get(0).get("count"));
		return TarticleCount;
	}

}
