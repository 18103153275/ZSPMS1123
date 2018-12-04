package com.tjzs.zscms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.DeptService;
import com.tjzs.zscms.user.service.UserService;
/*
 * 通过id查询用户信息，将用户信息传入修改页面
 * 
 * */
public class UserGetByIdServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取修改的用户id
		int id=Integer.parseInt(req.getParameter("id"));
		//创建逻辑对象
		UserService us= new UserService();
		DeptService ds=new DeptService();
		try {
			//将id传递下去进行查询
			UserBean user=us.queryUserById(id);
			//获取全部部门
			List<DeptBean> depts=ds.queryAll();
			//将对象存储到作用域中
			req.setAttribute("user",user);
			req.setAttribute("depts",depts);
			//转发
			req.getRequestDispatcher("Tuser/Update.jsp").forward(req, resp);
			
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
