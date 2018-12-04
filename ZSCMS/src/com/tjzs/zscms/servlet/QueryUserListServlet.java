package com.tjzs.zscms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.UserService;

public class QueryUserListServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7427252622859247391L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建逻辑层对象
		UserService us = new UserService();
		try {
			// 定义每页的查询条数
			int size = 3;
			// 获取当前页数
			String p = req.getParameter("currentPage");
			int page = 1;
			if (p == null) {
				page = 1;
			} else {
				page = Integer.parseInt(p);
			}
			// 获取当前的页数
			List<UserBean> users = us.queryByPage(page, size);
			// 获取总页数
			int pageCount = us.queryPageCount(size);
			// 将当前页面继续返回到页面中
			req.setAttribute("currentPage", page);
			req.setAttribute("pageCount", pageCount);
			// 将用户集合存储到req作用域
			req.setAttribute("users", users);
			// 转发到用户查询页面，在查询页面显示数据
			req.getRequestDispatcher("Tuser/list.jsp").forward(req, resp);
		} catch (SysException e) {
			// 重定向
			resp.sendRedirect("error.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
