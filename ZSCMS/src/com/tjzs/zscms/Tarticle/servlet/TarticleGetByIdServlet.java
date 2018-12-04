package com.tjzs.zscms.Tarticle.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.Tarticle.bean.TarticleBean;
import com.tjzs.zscms.Tarticle.service.TarticleService;
import com.tjzs.zscms.Tchannel.bean.TchannelBean;
import com.tjzs.zscms.Tchannel.service.TchannelService;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;

/*
 * 通过id查询栏目信息，将栏目信息传入修改页面
 * 
 * */
public class TarticleGetByIdServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取修改的用户id
		int id = Integer.parseInt(req.getParameter("id"));
		// 创建逻辑对象
		//创建逻辑层对象
		TarticleService aS=new TarticleService();
		TchannelService tS=new TchannelService();
		try {
			//将id传递下去进行查询
			TarticleBean tar = aS.queryTarticleById(id);
			//获取全部栏目
			List<TchannelBean> tchannes=tS.querAll();
			//将对象存储到作用域中
			req.setAttribute("tar", tar);
			req.setAttribute("tchannes", tchannes);
			//转发
			req.getRequestDispatcher("Tarticle/Update.jsp").forward(req, resp);
		} catch (SysException e) {
			//重定向
			resp.sendRedirect("error.heml");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}
}