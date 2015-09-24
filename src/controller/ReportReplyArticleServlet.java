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
import model.service.ReportReplyArticleService;
import model.vo.MemberVO;
import model.vo.ReportReplyArticleVO;
import util.ConvertType;

@WebServlet("/ReportReplyArticleServlet")
public class ReportReplyArticleServlet extends HttpServlet {
	ReportReplyArticleService service ;
	
	@Override
	public void init() throws ServletException {
		service = new ReportReplyArticleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	//尚未測試
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String orderId = request.getParameter("orderId");
		String reportedReplyArticleId = request.getParameter("reportedReplyArticleId");
		String reportedReplyArticle = request.getParameter("reportedReplyArticle");
		String reportTime = request.getParameter("reportTime");
		String reportReason = request.getParameter("reportReason");
		String sendReportArticle = request.getParameter("sendReportArticle");
		
		String memberAccount = request.getParameter("memberAccount");
		String memberPassword = request.getParameter("memberPassword");
		MemberService memberService = new MemberService();
		MemberVO checkMemberInfo = memberService.showMemberInfo(memberAccount, memberPassword);
		
		Map<String, String> errors = new HashMap<String , String>();
		request.setAttribute("errors", errors);
		
		//判斷是否是會員才可檢舉
		if(sendReportArticle != null && memberAccount.equals(checkMemberInfo)){
			
			if(sendReportArticle.equals("insert")){
				if(reportReason == null || reportReason.length() ==0){
					errors.put("reportReason", "請輸入檢舉事由");
				}
			}else if(sendReportArticle.equals("delete")){
				if(!memberAccount.equals(checkMemberInfo)){
					errors.put("memberAccount", "請輸入管理人員帳號");
				}
			}
		}
		
		//轉換資料
		int convertOrderId = 0;
		if(orderId!=null && orderId.length()!=0){
			convertOrderId = ConvertType.convertToInt(orderId);
			if(convertOrderId == -1000){
				errors.put("orderId", "OrderId MUST Be a Integer.");
			}
		}
		
		int convertReportedReplyArticleId =0;
		if(reportedReplyArticleId!=null && reportedReplyArticleId.length()!=0){
			convertReportedReplyArticleId = ConvertType.convertToInt(reportedReplyArticleId);
			if(convertReportedReplyArticleId==-1000){
				errors.put("reportedReplyArticleId", "ReportedReplyArticleId MUST Be a Integer.");
			}
		}
		
		//呼叫Model
		ReportReplyArticleVO bean = new ReportReplyArticleVO();
		bean.setOrderId(convertOrderId);
		bean.setReportedReplyArticleId(convertReportedReplyArticleId);
		
		//導向View
		//管理人員後台顯示被檢舉回文
		ReportReplyArticleService reportReplyArticleService = new ReportReplyArticleService();
		if(reportReplyArticleService.selectAllList()!=null){
			Collection<ReportReplyArticleVO> showReportReplyArticle = reportReplyArticleService.selectAllList();
			if(showReportReplyArticle!=null){
				request.setAttribute("showReportReplyArticle", showReportReplyArticle);
			}else{
				request.setAttribute("showReportReplyArticle", showReportReplyArticle);
			}
			request.getRequestDispatcher("").forward(request, response);
		}
		
		//顯示會員暱稱(或改會員帳號?先不猶豫先擺著)
//		if(memberService.getMemberNickname(memberAccount)!=null && memberAccount.equals(checkMemberInfo)){
//			String showMemberNickname = memberService.getMemberNickname(memberAccount);
//			if(showMemberNickname!=null){
//				request.setAttribute("showMemberNickname", showMemberNickname);
//			}
//			request.getRequestDispatcher("").forward(request, response); //目前沒有頁面
//		}
		//檢舉文章、管理員後台刪除檢舉s
		if(sendReportArticle != null & sendReportArticle.equals("insert")){
			boolean result = reportReplyArticleService.addReportReplyArticle(bean);
			if(!result){
				request.setAttribute("insert", 0);
			}else{
				request.setAttribute("insert", 1);
			}
			request.getRequestDispatcher("").forward(request, response);
		}else if(sendReportArticle!=null && sendReportArticle.equals("delet")){
			boolean result = reportReplyArticleService.deleteReplyArticle(bean);
			if(!result){
				request.setAttribute("delete", 0);
			}else{
				request.setAttribute("delete", 1);
			}
			request.getRequestDispatcher("").forward(request, response);
		}else{
			errors.put("action", "Unknown Action:"+sendReportArticle);
			request.getRequestDispatcher("").forward(request, response); //目前沒有頁面
		}
		
		
	}

}
