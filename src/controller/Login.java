package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import model.service.LoginService;
import model.service.MemberService;
import model.vo.MemberVO;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;
	private LoginService ls;

	public void init() throws ServletException {
		ms = new MemberService();
		ls = new LoginService();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("memberAccount");
		String password = request.getParameter("memberPassword");
		String operation = request.getParameter("operation");
		String path = request.getRequestURI();
		System.out.println(path);
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		if (username == null || username.trim().length() == 0) {
			errors.put("username", "請輸入帳號");
		}

		if (password == null || password.trim().length() == 0) {
			errors.put("password", "請輸入密碼");
		}
		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}

		MemberVO bean = ms.login1(username, password);
		if (operation != null && operation.equals("登入") && bean != null) {
			HttpSession session = request.getSession();
			String ip = request.getRemoteAddr();// 有個ip and then?
			System.out.println(ip);
			session.setAttribute("user", bean);
			response.sendRedirect("HomePageVersion3.jsp");
			return;
		} else if (operation != null && operation.equals("提取密碼")) {
			request.getRequestDispatcher("GetPassword.jsp").forward(request, response);// 送去客服頁面
			return;
		} else {
			errors.put("password", "登入失敗，請再試一遍");// 放在session裡
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

}
