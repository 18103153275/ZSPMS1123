package com.tjzs.zscms.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;
import com.tjzs.zscms.util.DButil;
/**
 * 查询部门表中所有的部门信息
 * */
public class DeptDaoImpl implements DeptDao {
	//连接数据库
	DButil db=new DButil();
	public List<DeptBean> queryAlldepts() throws SysException {
		String sql="select * from dept";
		List<Map<String, String>> list=db.execQuery(sql, null);
		//创建集合，存储部门信息
		List<DeptBean> depts=new ArrayList<DeptBean>();
		//遍历
		for(Map<String, String> map:list){
			//创建deptbean对象
			DeptBean dept=new DeptBean();
			dept.setId(Integer.parseInt(map.get("id")));
			dept.setDeptname(map.get("deptname"));
			//添加到集合中
			depts.add(dept);
		}
		return depts;
	}

}

