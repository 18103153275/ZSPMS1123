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

public class QueryChannelservlt extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7374707513060430922L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 创建逻辑层对象
		TchannelService cs = new TchannelService();

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
			List<TchannelBean> channels = cs.queryByPage(page, size);
			// 获取总页数
			int pageCount = cs.queryPageCount(size);
			// 将当前页面继续返回到页面中
			req.setAttribute("currentPage", page);
			req.setAttribute("pageCount", pageCount);
			// 调用cs中查询全部频道的方法
			req.setAttribute("channels", channels);
			// 转发到作用域
			req.getRequestDispatcher("Tchannel/list.jsp").forward(req, resp);
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
