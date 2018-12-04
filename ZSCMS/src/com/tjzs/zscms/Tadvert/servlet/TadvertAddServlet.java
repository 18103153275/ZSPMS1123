package com.tjzs.zscms.Tadvert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjzs.zscms.Tadvert.bean.TadvertBean;
import com.tjzs.zscms.Tadvert.service.TadvertService;
import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.UserService;

/**
 * 新增栏目
 * */
public class TadvertAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取新增用户页面中表单的数据，将数据封装到bean包
		// 创建Tadvertbean对象
		TadvertBean tad = new TadvertBean();
		tad.setTitle(req.getParameter("title"));
		tad.setContent(req.getParameter("content"));
		tad.setCrtime(req.getParameter("crtime"));
		tad.setCrman(req.getParameter("crman"));
		// 创建service层对象
		TadvertService us = new TadvertService();
		try {
			if (us.insertTadvert(tad) > 0) {
				// 转发到用户查询页面
				req.getRequestDispatcher("Tadvertlist.do").forward(req, resp);
			}
		} catch (BusinessException e) {
			// 设置错误提示信息
			req.setAttribute("msg", e.getErrMsg());
			// 转发到新增页面
			req.getRequestDispatcher("Tadvert/add.jsp").forward(req, resp);
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
