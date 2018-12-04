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

public class ChannPidServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4568168125042737013L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 调用逻辑层对象
		TchannelService tc = new TchannelService();
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
			List<TchannelBean> channel = tc.queryByPage(page, size);
			// 获取总页数
			int pageCount = tc.queryPageCount(size);
			// 将当前页面继续返回到页面中
			req.setAttribute("currentPage", page);
			req.setAttribute("pageCount", pageCount);
			// 将栏目放入请求作用域
			for (TchannelBean tchannelBean : channel) {
				System.err.println(tchannelBean);
			}
			req.setAttribute("channel", channel);
			// 转发至增加页面
			req.getRequestDispatcher("Tchannel/add.jsp").forward(req, resp);

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
