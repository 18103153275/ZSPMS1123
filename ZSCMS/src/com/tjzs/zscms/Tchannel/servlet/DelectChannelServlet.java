package com.tjzs.zscms.Tchannel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tchannel.service.TchannelService;
import com.tjzs.zscms.exception.SysException;

public class DelectChannelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 获取地址栏中的id
		int id = Integer.parseInt(req.getParameter("id"));
		// 创建逻辑层对象
		TchannelService cS = new TchannelService();

		try {
			// 调用删除的方法
			cS.deleteChannel(id);
			// 转发
			resp.sendRedirect("channelist.do");
		} catch (SysException e) {
			// 失败
			resp.sendRedirect("error.html");

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
