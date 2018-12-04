package com.tjzs.zscms.Tarticle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tarticle.bean.TarticleBean;
import com.tjzs.zscms.Tarticle.dao.impl.TarticleDao;
import com.tjzs.zscms.Tarticle.dao.impl.TarticleDaoImpl;
import com.tjzs.zscms.exception.SysException;

public class TarticleAddServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//获取新增页面表单中的数据封到bean中
		//创建ArticleBean对象
		TarticleBean tar= new TarticleBean();
		tar.setTitle(req.getParameter("title"));
		tar.setContent(req.getParameter("content"));
		tar.setAuthor(req.getParameter("author"));
		tar.setCrtime(req.getParameter("crtime"));
		tar.setChannel(Integer.parseInt(req.getParameter("channel")));
		tar.setIsremod(Integer.parseInt(req.getParameter("isremod")));
		tar.setIshot(Integer.parseInt(req.getParameter("ishot")));
		//多态形式
		TarticleDao tars=new TarticleDaoImpl();
		
		try {
			//调用daoimpl中新增的方法进行判断
			if (tars.insertTarticle(tar)>0) {
				//大于0转发
				req.getRequestDispatcher("Tarticlelist.do").forward(req, resp);
			}
			
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
