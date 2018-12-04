package com.tjzs.zscms.Tadvert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tadvert.bean.TadvertBean;
import com.tjzs.zscms.Tadvert.service.TadvertService;
import com.tjzs.zscms.exception.SysException;

public class TadvertUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取新增栏目页面中表单的数据，将数据封装到bean包
		// 创建Tadvertbean对象
		TadvertBean tad = new TadvertBean();
		int id = Integer.parseInt(req.getParameter("id"));
		tad.setId(id);
		tad.setTitle(req.getParameter("title"));
		tad.setContent(req.getParameter("content"));
		tad.setCrtime(req.getParameter("crtime"));

		tad.setCrman(req.getParameter("crman"));
		
		// 创建service层对象
		TadvertService us = new TadvertService();
		try {
			// 调用修改的方法
			us.updateTadvert(tad);
			// 转发
			req.getRequestDispatcher("Tadvertlist.do").forward(req, resp);

		} catch (SysException e) {
			// 修改失败
			req.setAttribute("id", id);
			req.setAttribute("msg", e.getErrMsg());
			req.getRequestDispatcher("Tadvertget.do").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}
}
