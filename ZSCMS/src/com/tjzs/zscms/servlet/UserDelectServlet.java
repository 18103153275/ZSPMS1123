package com.tjzs.zscms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.service.UserService;

public class UserDelectServlet extends HttpServlet {
	/**
	 * 
	 * 删除用户信息
	 * */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取页面的id
		String id=req.getParameter("id");
		//创建逻辑层对象
		UserService us=new UserService();
		
		try {
			//调用删除的方法
			us.delectUser(Integer.parseInt(id));
			//重定向
			resp.sendRedirect("userlist.do");
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}catch (SysException e) {
			e.getErrMsg();
			//重定向
			resp.sendRedirect("userlist.do");
		}
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}
}
