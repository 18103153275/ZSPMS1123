package com.tjzs.zscms.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;
import com.tjzs.zscms.user.service.DeptService;
/**
 * 获取部门名称
 * */
public class DeptServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		DeptService ds=new DeptService();
		try {
			List<DeptBean> depts=ds.queryAll();
			//将信息放入req对象中
			req.setAttribute("depts", depts);
			//转发到add.jsp
			req.getRequestDispatcher("Tuser/add.jsp").forward(req, resp);
		} catch (SysException e) {
			resp.sendRedirect("error.html");
		}
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
