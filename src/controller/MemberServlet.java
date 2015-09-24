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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	//尚未測試
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberAccount = request.getParameter("memberAccount");
		String memberPassword = request.getParameter("memberPassword");
		String memberEmail = request.getParameter("memberEmail");
		String memberFB = request.getParameter("memberFB");
		String memberGoogle = request.getParameter("memberGoogle");
		String memberTwitter = request.getParameter("memberTwitter");
		String memberName = request.getParameter("memberName");
		String memberNickname = request.getParameter("memberNickname");
		String memberBirthday = request.getParameter("memberBirthday");
		String memberPhoto = request.getParameter("memberPhoto");
		String memberSelfIntroduction = request.getParameter("memberSelfIntroduction");
		String broadcastWebsite = request.getParameter("broadcastWebsite");
		String broadcastTitle = request.getParameter("broadcastTitle");
		String broadcastClassName = request.getParameter("broadcastClassName");
		String broadcastTime = request.getParameter("broadcastTime");
		String broadcastDescription = request.getParameter("broadcastDescription");
		
		String UpdateMemberInfo = request.getParameter("UpdateMemberInfo");
		
		MemberVO checkMemberInfo = service.showMemberInfo(memberAccount, memberPassword);
		
		Map<String , String> errors = new HashMap<String , String>();
		request.setAttribute("errors", errors);
		
		//判斷是否是會員才可修改會員資料
		if(UpdateMemberInfo!=null && memberAccount.equals(checkMemberInfo)){
			
			if(UpdateMemberInfo.equals("upadte")){
				if(memberAccount == null || memberAccount.length() == 0){
					errors.put("memberAccount", "會員帳號請勿空白");
				}else if(memberPassword == null || memberPassword.length() == 0){
					errors.put("memberPassword", "會員密碼請勿空白");
				}else if(memberEmail == null || memberEmail.length() == 0){
					errors.put("memberEmail", "會員Email請勿空白");
				}else if(memberNickname == null || memberNickname.length() == 0){
					errors.put("memberNickname", "會員暱稱請勿空白");
				}else if(memberBirthday == null || memberBirthday.length() == 0){
					errors.put("memberBirthday", "會員生日請勿空白");
				}else if(memberSelfIntroduction == null || memberSelfIntroduction.length() == 0){
					errors.put("memberBirthday", "會員生日請勿空白");
				}else if(broadcastTitle == null || broadcastTitle.length() == 0){
					errors.put("broadcastTitle", "實況台標題請勿空白");
				}else if(broadcastClassName == null || broadcastClassName.length() == 0){
					errors.put("broadcastClassName", "實況台分類請勿空白");
				}else if(broadcastDescription == null || broadcastDescription.length() == 0){
					errors.put("broadcastDescription", "實況台說明請勿空白");
				}
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
			if(convertMemberBirthday == null){
				errors.put("memberBirthday", "MemberBirthday CAN NOT Null.");
			}
		}
		
		
		//導向View
		//先將個人資料Select到畫面上，再讓會員修改
//		if(service.showMemberInfo(memberAccount, memberPassword)!=null){
//			MemberVO showMemberInfo = service.showMemberInfo(memberAccount, memberPassword);
//			if(showMemberInfo == null){
//				request.setAttribute("showMemberInfo", showMemberInfo);
//			}else{
//				request.setAttribute("showMemberInfo", showMemberInfo);
//			}
//			request.getRequestDispatcher("").forward(request, response); //目前沒有路徑
//		}
//		
		//呼叫Model
		MemberVO bean = new MemberVO();
		bean.setMemberEmail(memberEmail);
		bean.setMemberNickname(memberNickname);
		bean.setMemberBirthday(convertMemberBirthday);
		bean.setBroadcastTitle(broadcastTitle);
		bean.setBroadcastClassName(broadcastClassName);
		bean.setBroadcastDescription(broadcastDescription);
		
		//會員修改資料
//		if(UpdateMemberInfo!=null && UpdateMemberInfo.equals("update")){
//			int result = service.update(bean);
//			if(result == -1){
//				request.setAttribute("update", -1);
//			}else{
//				request.setAttribute("update", 1);
//			}
//			request.getRequestDispatcher("").forward(request, response); //目前無路徑
//		}else {
//			errors.put("action", "Unknown Action:"+UpdateMemberInfo);
//			request.getRequestDispatcher("").forward(request, response); //目前無路徑
//		}
//		
		
		
	}

}
