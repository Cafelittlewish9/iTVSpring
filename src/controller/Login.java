package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
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
import model.vo.LoginVO;
import model.vo.MemberVO;

@WebServlet("/login.do")
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

		String path = request.getRequestURI();
		HttpSession session = request.getSession();
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		String username = request.getParameter("memberAccount");
		String password = request.getParameter("memberPassword");
		String operation = request.getParameter("operation");
		String requestURI = (String) session.getAttribute("requestURI");
		if (username == null || username.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
				
		MemberVO bean = ms.login1(username, password);
		LoginVO log=new LoginVO();
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		if (operation != null && operation.equals("登入") && bean != null){
			
			String ip = request.getRemoteAddr();
			log.setIp(ip);
			log.setMemberAccount(username);
			log.setLoginTime(new java.util.Date());
			ls.login(log);
			cookieUser = new Cookie("user", username);
			cookieUser.setMaxAge(30*60*60);
			cookieUser.setPath(request.getContextPath());
			String encodePassword = DatatypeConverter.printBase64Binary(password.getBytes());
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(30*60*60);
			cookiePassword.setPath(request.getContextPath());
			session.setAttribute("user",bean );			
		}else {
			errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
			cookieUser = new Cookie("user", username);
			cookieUser.setMaxAge(0);   
			cookieUser.setPath(request.getContextPath());
			String encodePassword = DatatypeConverter.printBase64Binary(password.getBytes());
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		
		if (!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("Login2.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (errorMsgMap.isEmpty()) {
			if (requestURI != null) {
//				requestURI = (requestURI.length() == 0 ? request
//						.getContextPath() : requestURI);
				requestURI = (requestURI.length() == 0 ? request
						.getContextPath() : path+"/HomePageVersion3.jsp");
				response.sendRedirect(response.encodeRedirectURL(requestURI));
				//少見的XX判斷式
				return;
			} else {
				response.sendRedirect(response.encodeRedirectURL(request
						.getContextPath()));
				return;
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("Login2.jsp");
			rd.forward(request, response);
			return;
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
