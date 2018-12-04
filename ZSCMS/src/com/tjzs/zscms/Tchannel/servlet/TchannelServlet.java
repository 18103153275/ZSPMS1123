package com.tjzs.zscms.Tchannel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tchannel.bean.TchannelBean;
import com.tjzs.zscms.Tchannel.service.TchannelService;
import com.tjzs.zscms.exception.SysException;

public class TchannelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 创建业务逻辑层
		TchannelService tls = new TchannelService();
		try {
			List<TchannelBean> channes = tls.querAll();
			// 将信息放入req对象中
			req.setAttribute("channes", channes);
			// 转发到add.jsp
			req.getRequestDispatcher("Tarticle/add.jsp").forward(req, resp);
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
