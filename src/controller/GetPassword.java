package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.LoginService;
import model.service.MemberService;
import model.vo.MemberVO;

@WebServlet("/getPWd")
public class GetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	public void init() throws ServletException {
		memberService = new MemberService();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("memberAccount");
		String password = request.getParameter("memberPassword");
		
		String operation = request.getParameter("operation");

		MemberVO bean = memberService.login1(username, password);
		if (operation != null && operation.equals("登入") && bean != null) {
			System.out.println("XXX");
			request.getSession().setAttribute("LoginOK", bean);
			response.sendRedirect("HomePageVersion3.jsp");
			return;
		} else if (operation != null && operation.equals("提取密碼")) {
			request.getRequestDispatcher("HomePageVersion3.jsp").forward(request, response);// 送去客服頁面
			return;
		} else {
			response.sendRedirect("HomePageVersion3.jsp");
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
