package com.tjzs.zscms.Tadvert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tadvert.service.TadvertService;
import com.tjzs.zscms.exception.SysException;

public class TadvertDelectServlet extends HttpServlet {
	/**
	 * 
	 * 删除栏目信息
	 * */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取页面的id
		String id = req.getParameter("id");
		// 创建逻辑对象
		TadvertService us = new TadvertService();
		try {
			// 调用删除的方法
			us.delectTadvert(Integer.parseInt(id));
			// 重定向
			resp.sendRedirect("Tadvertlist.do");
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SysException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			// 重定向
			resp.sendRedirect("Tadvertlist.do");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}
}
