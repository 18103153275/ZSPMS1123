package com.tjzs.zscms.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.UserService;
/**
 * 新增用户
 * */
public class UserAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取新增用户页面中表单的数据，将数据封装到bean包
		//创建userbean对象
		UserBean user=new UserBean();
		user.setLoginname(req.getParameter("loginname"));
		user.setPassword(req.getParameter("password"));
		user.setRealname(req.getParameter("realname"));
		user.setSex(req.getParameter("sex"));
		user.setBirthday(req.getParameter("birthday"));
		user.setEmail(req.getParameter("email"));
		user.setDept(Integer.parseInt(req.getParameter("dept")));
		user.setEnabled(1);//新增用户默认可用
		//创建session
		HttpSession session=req.getSession();
		//从session中获取登录的用户信息
		UserBean users=(UserBean) session.getAttribute("userBean");
		//将登录用户的id，赋值进来
		user.setCreatman(users.getId());
		//确认密码的数据
		String repwd=req.getParameter("repwd");
		//判断两次密码是否一致
		if(!user.getPassword().equals(repwd)){
			req.setAttribute("pwdMsg", "两次密码不一致，请重新输入");
			req.getRequestDispatcher("dept.do").forward(req, resp);
			return;
		}
		//创建service层对象
		UserService us=new UserService();
		try {
			if(us.insertUser(user)>0){
				//转发到用户查询页面
				req.getRequestDispatcher("userlist.do").forward(req, resp);
			}
		} catch (BusinessException e) {
			//设置错误提示信息
			req.setAttribute("msg", e.getErrMsg());
			//转发到新增页面
			req.getRequestDispatcher("dept.do").forward(req, resp);
		}catch (SysException e) {
			resp.sendRedirect("error.html");
		}
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
