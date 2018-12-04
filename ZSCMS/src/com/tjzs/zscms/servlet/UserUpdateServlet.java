package com.tjzs.zscms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.UserService;

public class UserUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取新增用户页面中表单的数据，将数据封装到bean包
		// 创建userbean对象
		UserBean user = new UserBean();
		int id=Integer.parseInt(req.getParameter("id"));
		user.setId(id);
		user.setLoginname(req.getParameter("loginname"));
		user.setRealname(req.getParameter("realname"));
		
		user.setSex(req.getParameter("sex"));
		user.setDept(Integer.parseInt(req.getParameter("dept")));
		user.setBirthday(req.getParameter("birthday"));
		
		user.setEmail(req.getParameter("email"));
		user.setEnabled(Integer.parseInt(req.getParameter("enabled")));
		
		
		// 创建service层对象
		UserService us = new UserService();
		try {
			//调用修改的方法
			us.updateUser(user);
			// 转发
			req.getRequestDispatcher("userlist.do").forward(req, resp);
			
		} catch (SysException e) {
			// 修改失败
			req.setAttribute("id",id);
			req.setAttribute("msg", e.getErrMsg());
			req.getRequestDispatcher("userget.do").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doGet(req, resp);
	}
}
