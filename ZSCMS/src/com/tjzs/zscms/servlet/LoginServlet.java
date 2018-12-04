package com.tjzs.zscms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.UserService;

/**
 * 登录的servlet
 * */
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7186189236942172582L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建逻辑层对象
		UserService us = new UserService();
		// 实现页面登录功能
		// 获取登录页面中的账号和密码
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// 创建userbean对象
		UserBean user = new UserBean();
		user.setLoginname(username);
		user.setPassword(password);

		// 获取是否记住密码的属性值
		String rmbPwd = req.getParameter("rmbPwd");
		// 判断是否记住密码
		if (rmbPwd != null && rmbPwd.equals("1")) {
			// 记住密码，利用cookie
			// 创建cookie，保存用户名和密码
			Cookie cookie1 = new Cookie("username", username);
			Cookie cookie2 = new Cookie("password", password);
			// 设置cookie时长
			cookie1.setMaxAge(60);
			cookie2.setMaxAge(60);
			// 响应给客户端
			resp.addCookie(cookie1);
			resp.addCookie(cookie2);
		}
		try {
			UserBean userBean = us.userLogin(user);
			// 去空
			if (userBean!= null) {
				// 如果不为空表示登录成功
				// 创建session
				HttpSession session = req.getSession();
				// 获取输入的验证码
				String idenfitying = req.getParameter("idenfitying");
				// 获取图片中的验证码
				String code = (String) session
						.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
				// 判断
				if (!idenfitying.equals(code)) {
					req.setAttribute("msg", "验证码输入有误，请重新输入");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
					return;
				}
				// 将用户信息的真是姓名从数据库中取出
				// 使用session将信息存储
				session.setAttribute("userBean", userBean);
				// 转发到主页面
				req.getRequestDispatcher("main.html").forward(req, resp);
				return;
			}
		} catch (BusinessException e) {
			req.setAttribute("msg", e.getErrMsg());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
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
