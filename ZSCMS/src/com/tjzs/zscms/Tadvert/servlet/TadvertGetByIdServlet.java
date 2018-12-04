package com.tjzs.zscms.Tadvert.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tadvert.bean.TadvertBean;
import com.tjzs.zscms.Tadvert.service.TadvertService;
import com.tjzs.zscms.exception.SysException;
/*
 * 通过id查询栏目信息，将栏目信息传入修改页面
 * 
 * */
public class TadvertGetByIdServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取修改的用户id
		int id=Integer.parseInt(req.getParameter("id"));
		//创建逻辑对象
		TadvertService us= new TadvertService();
		try {
			//将id传递下去进行查询
			TadvertBean tad=us.queryTadvertById(id);
			//将对象存储到作用域中
			req.setAttribute("tad",tad);
			//转发
			req.getRequestDispatcher("Tadvert/Update.jsp").forward(req, resp);
			
		} catch (SysException e) {
			resp.sendRedirect("error.html");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}
}