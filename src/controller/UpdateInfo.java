package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.MemberService;
@WebServlet("/updateInfo")
public class UpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms; 
	
	@Override
	public void init() throws ServletException {
		ms=new MemberService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//我應該需要session幫忙了，session裡有membervo，反正我要確定用戶是在登入狀態
		
		String password=request.getParameter("password");
		String newpwd=request.getParameter("newpassword");
		String email=request.getParameter("email");
		String fb=request.getParameter("fb");
		String google=request.getParameter("google");
		String twitter=request.getParameter("twitter");
		String nickname=request.getParameter("nickname");
		String birthday=request.getParameter("birthday");
		String photo=request.getParameter("photo");//拿圖檔要轉base64
		String selfintro=request.getParameter("selfintro");
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		
		
	
		
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	

	
	
	
}
