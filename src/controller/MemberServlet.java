package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.service.MemberService;
import model.vo.MemberVO;
import util.ConvertType;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	MemberService service;

	@Override
	public void init() throws ServletException {
		service = new MemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");	
		
		String memberNewPwd = request.getParameter("memberNewPwd");
		String memberCheckPwd = request.getParameter("memberCheckPwd");
		String memberEmail = request.getParameter("memberEmail");
		String memberNickname = request.getParameter("memberNickname");
		String memberBirthday = request.getParameter("memberBirthday");

		String updateMemberInfo = request.getParameter("operation");
		Map<String , String> errors = new HashMap<String , String>();
		request.setAttribute("errorMsg", errors);
		//判斷是否為會員本人才可修改會員資料←因為會員的其他資料從session來可判斷為確實已登入且為會員
		//驗證用戶真的有輸入資料		
		if (memberNewPwd != "" && memberCheckPwd != "") {
			if (memberNewPwd != memberCheckPwd) {
				errors.put("wrongNewPwd", "請再確認一次新密碼");
			}
		}
		
		//資料轉換
		Date convertMemberBirthday = null;
		if(memberBirthday!=null && memberBirthday.length()!=0){
			SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				convertMemberBirthday = (Date) dsf.parse(memberBirthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
		//呼叫Model

		if (updateMemberInfo != null && updateMemberInfo.equals("update")) {
			if(memberNewPwd!=""&& memberCheckPwd != ""){
				member.setMemberPassword(memberNewPwd.getBytes());
			}
			if (memberEmail != "") {
				member.setMemberEmail(memberEmail);
			}
			if (memberNickname != "") {
				member.setMemberNickname(memberNickname);
			}
			if (memberBirthday != "") {
				member.setMemberBirthday(convertMemberBirthday);
			}
			System.out.println("really");
			service.update(member);
		}

		if (errors.isEmpty()) {
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath())+"/PersonalPage.jsp");
			return;
		}
		else{
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath())+"/HomePageVersion3.jsp");
			return;
		}
	}



}
