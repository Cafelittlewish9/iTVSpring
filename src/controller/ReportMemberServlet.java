package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.MemberService;
import model.service.ReportMemberService;
import model.vo.MemberVO;
import model.vo.ReportMemberVO;
import util.ConvertType;

@WebServlet("/ReportMemberServlet")
public class ReportMemberServlet extends HttpServlet {
	
	ReportMemberService service;
       
	@Override
	public void init() throws ServletException {
		service = new ReportMemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	//尚未測試
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String orderId = request.getParameter("orderId");
		String reportedMemberId = request.getParameter("reportedMemberId");
		String reportTime = request.getParameter("reportTime");
		String reportReason = request.getParameter("reportReason");
		String sendReportMember = request.getParameter("sendReportMember");
		
		String memberAccount = request.getParameter("memberAccount");
		String memberPassword = request.getParameter("memberPassword");
		MemberService memberService = new MemberService();
		MemberVO checkMemberInfo = memberService.login1(memberAccount, memberPassword);
		
		Map<String , String> errors = new HashMap<String , String>();
		request.setAttribute("errors", errors);
		
		//判斷是否是會員才可檢舉
		if(sendReportMember!=null && memberAccount.equals(checkMemberInfo)){
			if(sendReportMember.equals("insert")){
				if(reportReason == null || reportReason.length() == 0){
					errors.put("reportReason" , "請輸入檢舉事由");
				}
			}else if(sendReportMember.equals("delete")){
				if(!memberAccount.equals(checkMemberInfo)){
					errors.put("memberAccount", "請先輸入管理人員帳號");
				}
			}
		}
		
		//轉換資料
		int convertReportedMemberId =0;
		if(reportedMemberId!=null && reportedMemberId.length()!=0){
			convertReportedMemberId = ConvertType.convertToInt(reportedMemberId);
			if(convertReportedMemberId==-1000){
				errors.put("reportedMemberId", "ReportedMemberId MUST Be a Integer.");
			}
		}
		
		int convertOrderId =0;
		if(orderId!=null && orderId.length()!=0){
			convertOrderId = ConvertType.convertToInt(orderId);
			if(convertOrderId==-1000){
				errors.put("orderId", "OrderId MUST Be a Integer.");
			}
		}
		
		//導向View
		
		//管理人員後台顯示被檢舉影片
		ReportMemberService reportMemberService = new ReportMemberService();
		if(reportMemberService.selectAll()!=null){
			Collection<ReportMemberVO> showReportMember = reportMemberService.selectAll();
			if(showReportMember!=null){
				request.setAttribute("showReportMember", showReportMember);
			}else{
				request.setAttribute("showReportMember", showReportMember);
			}
			request.getRequestDispatcher("//管理人員後台頁面路徑").forward(request, response);
		}
		
		//顯示會員暱稱
//		if(memberService.getMemberNickname(memberAccount)!=null && memberAccount.equals(checkMemberInfo)){
//			String showMemberNickname = memberService.getMemberNickname(memberAccount);
//			if(showMemberNickname!=null){
//				request.setAttribute("showMemberNickname", showMemberNickname);
//			}
//			request.getRequestDispatcher("").forward(request, response); //目前沒有頁面
//		}
		
		
		//呼叫Model
		ReportMemberVO bean = new ReportMemberVO();
		bean.setOrderId(convertOrderId);
		bean.setReportedMemberId(convertReportedMemberId);
		
		//檢舉影片、管理員後台刪除檢舉
		if(sendReportMember!=null && sendReportMember.equals("insert")){
			boolean result = reportMemberService.addReportMember(bean);
			if(!result){
				request.setAttribute("insert", 0);
			}else{
				request.setAttribute("insert", 1);
			}
			request.getRequestDispatcher("").forward(request, response); //目前沒有頁面
		}else if(sendReportMember!=null && sendReportMember.equals("delete")){
			boolean result = reportMemberService.deleteReportMember(bean);
			if(!result){
				request.setAttribute("delete", 0);
			}else{
				request.setAttribute("delete", 1);
			}
			request.getRequestDispatcher("//管理人員頁面路徑").forward(request, response);
		}else{
			errors.put("action", "Unknown Action:"+sendReportMember);
			request.getRequestDispatcher("").forward(request, response); //目前沒有頁面
		}
		
	}	
}
