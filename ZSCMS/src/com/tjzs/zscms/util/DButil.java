package com.tjzs.zscms.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tjzs.zscms.exception.SysException;


/**
 * @author Administrator
 * 
 */
public class DButil {
	// 驱动
	private String driver = "com.mysql.jdbc.Driver";
	// 连接路径，地址和连接方式
	private String url = "jdbc:mysql://localhost:3306/zscms?useUnicode=true&characterEncoding=utf-8";
	// 连接用户名
	private String user = "root";
	// 密码
	private String password = "admin";

	/*
	 * 获得数据库连接的方法 ，不允许外界调用
	 */
	private Connection getConn() {
		// 加载并注册驱动
		Connection conn = null;
		// 获得数据库连接
		try {
			// 加载并注册驱动
			Class.forName(driver);
			// 获得数据库连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * 关闭资源的方法 rs结果集 ps处理集 conn连接
	 */
	private void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public int execUpdate(String sql, Object[] objs) {
		// 获得数据库连接
		Connection conn = this.getConn();
		// 声明预编译对象
		PreparedStatement ps = null;
		// 如果连接为空时不能执行以下内容
		if (conn == null) {
			return 0;
		}

		try {
			// 从连接中获得预编译对象
			ps =conn.prepareStatement(sql);
			// 依次将参数set到ps中参数注入
			for (int i = 0; i < objs.length; i++) {
				// 索引从1开始
				ps.setObject(i + 1, objs[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();

			try {
				throw new SysException(100, "系统异常，请联系管理员");
			} catch (SysException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}

		} finally {
			this.close(null, ps, conn);

		}
		return 0;

	}

	/*
	 * 查询的方法
	 */
	public List<Map<String, String>> execQuery(String sql, Object[] objs)
			throws SysException {
		// 获得连接
		Connection conn = this.getConn();
		// 定义处理对象的变量初始化为null
		PreparedStatement ps = null;
		// 定义结果集初始化为null
		ResultSet rs = null;
		// 定义一个用于存放封装数据的Map集合的list集合
		List<Map<String, String>> maps = new ArrayList<>();

		try {
			// 从数据库连接中获得处理对象
			ps = conn.prepareStatement(sql);
			if (objs!=null) {
				// 把参数注入到SQL中
				for (int i = 0; i < objs.length; i++) {
					// 索引从1开始
					ps.setObject(i + 1, objs[i]);
				}
			}
			
			// 获得结果集
			rs = ps.executeQuery();
			// 获得结果集的结构
			ResultSetMetaData rm = rs.getMetaData();
			// 依次从结果中取值
			while (rs.next()) {
				// Map集合用于存放一条数据
				Map<String, String> map = new HashMap<>();
				// 把数据封装到Map中
				for (int i = 1; i <= rm.getColumnCount(); i++) {
					// 结果集结构中获得字段名，rm.getColumnCount()，i从1开始
					// 结果集获得字段集rm.getColumnCount()，i从1开始
					map.put(rm.getColumnName(i), rs.getString(i));
				}
				// 把一条信息放的list集合中
				maps.add(map);

			}

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new SysException(100, "系统异常，请联系管理员");
		} finally {
			// 调用本类的关闭资源
			this.close(rs, ps, conn);

		}
		return maps;

	}
}
