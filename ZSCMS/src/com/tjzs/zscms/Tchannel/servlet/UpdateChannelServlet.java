package com.tjzs.zscms.Tchannel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tchannel.bean.TchannelBean;
import com.tjzs.zscms.Tchannel.service.TchannelService;
import com.tjzs.zscms.exception.SysException;

public class UpdateChannelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 调用bean对象
		TchannelBean tBean = new TchannelBean();
		// 封装数据
		int id = Integer.parseInt(req.getParameter("id"));
		tBean.setId(id);
		tBean.setCname(req.getParameter("cname"));
		tBean.setIsleaf(Integer.parseInt(req.getParameter("isleaf")));
		tBean.setLev(Integer.parseInt(req.getParameter("lev")));
		tBean.setPid(Integer.parseInt(req.getParameter("pid")));
		tBean.setSort(Integer.parseInt(req.getParameter("sort")));
		// 创建逻辑层对象
		TchannelService tS = new TchannelService();
		try {
			// 调用修改方法
			int channel = tS.updateChannel(tBean);
			// 转发
			req.getRequestDispatcher("channelist.do").forward(req, resp);
		} catch (SysException e) {
			// 错误信息封存和id
			req.setAttribute("id", id);
			req.setAttribute("msg", e.getErrMsg());
			// 转发
			req.getRequestDispatcher("channelget.do").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req, resp);
	}
}
