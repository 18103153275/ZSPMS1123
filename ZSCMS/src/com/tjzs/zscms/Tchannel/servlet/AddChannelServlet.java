package com.tjzs.zscms.Tchannel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tchannel.bean.TchannelBean;
import com.tjzs.zscms.Tchannel.service.TchannelService;
import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;

/**
 * 新增栏目信息表
 * @author Administrator
 *
 */
public class AddChannelServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 创建channelbean对象
				TchannelBean c = new TchannelBean();
				// 获取表单里的值
				c.setCname(req.getParameter("cname"));
				c.setPid(Integer.parseInt(req.getParameter("pid")));
				c.setLev(Integer.parseInt(req.getParameter("lev")));
				c.setIsleaf(Integer.parseInt(req.getParameter("isleaf")));
				c.setSort(Integer.parseInt(req.getParameter("sort")));
				// 创建逻辑层对象
				TchannelService cs = new TchannelService();
				
				
				try {
					// 调用cs中的新增频道的方法
				int result =cs.addChannel(c);
					//新增成功
					if (result > 0) {
						// 新增成功转发至频道展示页面
						req.getRequestDispatcher("channelist.do").forward(req, resp);
					}
				} catch (SysException e) {
					//新增失败 系统异常
					resp.sendRedirect("error.html");
				} catch (BusinessException e) {
					//新增失败
					//将错误信息存入作用域
					req.setAttribute("msg", e.getErrMsg());
					//转发至新增页面继续新增
					req.getRequestDispatcher("Tchannel/add.jsp").forward(req, resp);
				}
				
				
					
				
				
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	doGet(req, resp);
	}
}
