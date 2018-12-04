package com.tjzs.zscms.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.util.DButil;

/**
 * 对tuser表进行crud操作
 * */
public class UserDaoImpl implements UserDao {
	// 创建DBUtil对象来连接数据库
	DButil db = new DButil();

	@Override
	public List<UserBean> login(UserBean user) throws SysException {
		// 登录查询的sql语句
		String sql = "select * from tuser where loginname=? and password=?";
		// 给sql语句的参数赋值
		Object[] obj = { user.getLoginname(), user.getPassword() };
		// 执行查询，返回结果
		List<Map<String, String>> list = db.execQuery(sql, obj);
		// 创建list集合来存储user对象
		List<UserBean> users = new ArrayList<UserBean>();
		// 遍历集合，将map集合和中的数据取出，然后直接封装到UserBean中
		for (Map<String, String> map : list) {
			// 创建UserBean对象
			UserBean userbean = new UserBean();
			// map集合是根据key来获取value
			userbean.setId(Integer.parseInt(map.get("id")));
			userbean.setLoginname(map.get("loginname"));
			userbean.setPassword(map.get("password"));
			userbean.setRealname(map.get("realname"));
			userbean.setSex(map.get("sex"));
			userbean.setBirthday(map.get("birthday"));
			userbean.setDept(Integer.parseInt(map.get("dept")));
			userbean.setEmail(map.get("email"));
			userbean.setEnabled(Integer.parseInt(map.get("enabled")));
			userbean.setCreatman(Integer.parseInt(map.get("createman")));
			// 将userbean对象加入集合中
			users.add(userbean);
		}
		return users;
	}

	/* 根据条件查询用户信息 */
	@Override
	public List<UserBean> queryByTiaoJian(String sql, Object[] obj)
			throws SysException {
		// select * from tuser where 1=1 and id=?
		// String sql = "select * from tuser where 1=1"
		// + (TiaoJian == null ? "" : TiaoJian);// TiaoJian==and id=?
		List<Map<String, String>> list = db.execQuery(sql, obj);
		// 创建list集合来存储user对象
		List<UserBean> users = new ArrayList<UserBean>();
		// 遍历集合，将map集合和中的数据取出，然后直接封装到UserBean中
		for (Map<String, String> map : list) {
			// 创建UserBean对象
			UserBean userbean = new UserBean();
			// map集合是根据key来获取value
			userbean.setId(Integer.parseInt(map.get("id")));
			userbean.setLoginname(map.get("loginname"));
			userbean.setPassword(map.get("password"));
			userbean.setRealname(map.get("realname"));
			userbean.setSex(map.get("sex"));
			userbean.setBirthday(map.get("birthday"));
			userbean.setDept(Integer.parseInt(map.get("dept")));
			userbean.setEmail(map.get("email"));
			userbean.setEnabled(Integer.parseInt(map.get("enabled")));
			// 如果等于1可用，等于2不可用
			if (userbean.getEnabled() == 1) {
				userbean.setEnabledTxt("可用");
			} else {
				userbean.setEnabledTxt("不可用");
			}
			userbean.setCreatman(Integer.parseInt(map.get("createman")));
			// 判断deptname不为空
			if (map.get("deptname") != null) {
				userbean.setDeptname(map.get("deptname"));
			}
			// 将userbean对象加入集合中
			users.add(userbean);
		}
		return users;
	}

	/* 查询用户的所有信息 */
	@Override
	public List<UserBean> queryByPage(int start, int size) throws SysException {
		// 查询所有，不需要条件和参数，那么直接给null
		// 左外连接
		String sql = "select u.*,d.deptname from tuser u left join dept d on u.dept=d.id order by u.id desc limit ?,?";
		Object[] obj = { start, size };
		return queryByTiaoJian(sql, obj);
	}
	/* 新增用户 */
	@Override
	public int insertUser(UserBean user) throws SysException {
		String sql = "insert into tuser values(null,?,?,?,?,?,?,?,?,?)";
		Object[] obj = { user.getLoginname(), user.getPassword(),
				user.getRealname(), user.getSex(), user.getBirthday(),
				user.getEmail(), user.getDept(), user.getEnabled(),
				user.getCreatman() };
		int result = db.execUpdate(sql, obj);
		return result;
	}

	/* 根据用户名查询用户信息 */
	@Override
	public List<UserBean> queryByLoginname(String loginname)
			throws SysException {
		String sql = "select * from tuser where loginname=?";
		Object[] obj = { loginname };
		return this.queryByTiaoJian(sql, obj);
	}

	/* 根据邮箱查询用户信息 */
	@Override
	public List<UserBean> queryByEmail(String email) throws SysException {
		String sql = "select * from tuser where email=?";
		Object[] obj = { email };
		return this.queryByTiaoJian(sql, obj);
	}

	/* 根据id查询用户信息 */
	@Override
	public List<UserBean> queryUserById(int id) throws SysException {
		String sql = "select * from tuser where id=?";
		Object[] obj = { id };
		return this.queryByTiaoJian(sql, obj);
	}

	/* 根据id修改用户信息 */
	@Override
	public int updateUser(UserBean user) throws SysException {
		String sql = "update tuser set loginname=?,realname=?,sex=?,dept=?,email=?,birthday=?,enabled=? where id=?";
		Object[] obj = { user.getLoginname(), user.getRealname(),
				user.getSex(), user.getDept(), user.getEmail(),
				user.getBirthday(), user.getEnabled(), user.getId() };
		int result = db.execUpdate(sql, obj);
		return result;
	}
	/*查询用户的总记录条数*/
	@Override
	public int queryUserCounts() throws SysException {
		String sql="select count(id) count from tuser";
		List<Map<String, String>>  list=db.execQuery(sql, null);
		int userCount=Integer.parseInt(list.get(0).get("count"));
		return userCount;
	}
	/*批量删除用户*/
	@Override
	public int delectUser(int id) throws SysException {
		//sql语句
		String sql="delete from tuser where id=?";
		//给sql语句参数赋值
		Object[] objs={id};
		int result=db.execUpdate(sql, objs);
		return result;
	}
}
