package com.tjzs.zscms.Tarticle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tarticle.bean.TarticleBean;
import com.tjzs.zscms.Tarticle.service.TarticleService;
import com.tjzs.zscms.exception.SysException;

public class TarticleUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取新增栏目页面中表单的数据，将数据封装到bean包
		// 创建Tarticlebean对象
		TarticleBean tar=new TarticleBean();
		int id = Integer.parseInt(req.getParameter("id"));
		tar.setId(id);
		tar.setTitle(req.getParameter("title"));
		tar.setContent(req.getParameter("content"));
		tar.setAuthor(req.getParameter("author"));
		tar.setCrtime(req.getParameter("crtime"));
		tar.setChannel(Integer.parseInt(req.getParameter("channel")));
		tar.setIsremod(Integer.parseInt(req.getParameter("isremod")));
		tar.setIshot(Integer.parseInt(req.getParameter("ishot")));
		//创建逻辑层对象
		// 创建service层对象
		TarticleService us = new TarticleService();
		try {
			// 调用修改的方法
			us.updateTarticle(tar);
			// 转发
			req.getRequestDispatcher("Tarticlelist.do").forward(req, resp);

		} catch (SysException e) {
			// 修改失败
			req.setAttribute("id", id);
			req.setAttribute("msg", e.getErrMsg());
			req.getRequestDispatcher("Tarticleget.do").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}
}
