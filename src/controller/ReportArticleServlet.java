package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.service.MemberService;
import model.service.ReportArticleService;
import model.vo.ArticleVO;
import model.vo.MemberVO;
import model.vo.ReportArticleVO;
import util.ConvertType;

@WebServlet("/ReportArticleServlet")
public class ReportArticleServlet extends HttpServlet {
	ReportArticleService service;
	
	@Override
	public void init() throws ServletException {
		service = new ReportArticleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	//尚未測試
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String orderId = request.getParameter("orderId");
		String reportedArticleId = request.getParameter("reportedArticleId");
		String reportTime = request.getParameter("reportTime");
		String reportReason = request.getParameter("reportReason");
		String sendReportArticle = request.getParameter("sendReportArticle");
		
		String memberAccount = request.getParameter("memberAccount");
		String memberPassword = request.getParameter("memberPassword");
		MemberService memberService = new MemberService();
		MemberVO checkMemberInfo = memberService.login1(memberAccount, memberPassword);
		
		Map<String , String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		//判斷是否是會員才可檢舉
		if(sendReportArticle!=null && memberAccount.equals(checkMemberInfo)){
			if(sendReportArticle.equals("insert")){
				if(reportReason == null || reportReason.length() == 0){
					errors.put("reportReason", "請輸入檢舉事由");
				}
			}else if(sendReportArticle.equals("delet")){
				if(!memberAccount.equals(checkMemberInfo)){
					errors.put("memberAccount" , "請輸入管理人員帳號");
				}
			}
		}
		
		//轉換資料
		int convertOrderId =0;
		if(orderId!=null && orderId.length()!=0){
			convertOrderId = ConvertType.convertToInt(orderId);
			if(convertOrderId==-1000){
				errors.put("orderId", "OrderId MUST Be a Integer.");
			}
		}
		
		int convertReportedArticleId =0;
		if(reportedArticleId!=null && orderId.length()!=0){
			convertReportedArticleId = ConvertType.convertToInt(reportedArticleId);
			if(convertReportedArticleId==-1000){
				errors.put("orderId", "OrderId MUST Be a Integer.");
			}
		}
		
		Date convertReportTime = null;
		if(reportTime!=null && reportTime.length()!=0){
			SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
			try {
				convertReportTime = (Date) dsf.parse(reportTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(convertReportTime == null){
				errors.put("reportTime", "ReportTime CAN NOT Null.");
			}
		}
		
		//呼叫Model
		ReportArticleVO bean = new ReportArticleVO();
		bean.setOrderId(convertOrderId);
		ArticleVO article = new ArticleVO();
		article.setArticleId(convertReportedArticleId);
		bean.setArticle(article);
		bean.setReportTime(convertReportTime);
		bean.setReportReason(reportReason);
		
		//導向View
		//管理人員後台顯示被檢舉文章
		ReportArticleService reportArticleService = new ReportArticleService();
		if(reportArticleService.reportArticleList()!=null){
			Collection<ReportArticleVO> showReportArticle = reportArticleService.reportArticleList();
			JSONObject obj = new JSONObject();
			obj.put("list", showReportArticle);
			response.getWriter().write(obj.toString());
			if(showReportArticle!=null){
				request.setAttribute("showReportArticle", showReportArticle);
			}else{
				request.setAttribute("showReportArticle", showReportArticle);
			}
			request.getRequestDispatcher("//管理人員後台路徑頁面").forward(request, response);
		}
		
		//顯示會員暱稱
//		if(memberService.getMemberNickname(memberAccount)!=null && memberAccount.equals(checkMemberInfo)){
//			String showMemberNickname = memberService.acgetMemberNickname(memberAccount);
//			if(showMemberNickname!=null){
//				request.setAttribute("showMemberNickname", showMemberNickname);
//			}
//			request.getRequestDispatcher("").forward(request, response); //目前沒有頁面
//		}
//		
		//檢舉文章、管理員後台刪除檢舉
		if(sendReportArticle != null && sendReportArticle.equals("insert")){
			boolean result = reportArticleService.addReportArticle(bean);
			if(!result){
				request.setAttribute("insert", 0);
			}else{
				request.setAttribute("insert", 1);
			}
			request.getRequestDispatcher("").forward(request, response); //目前沒有頁面
		}else if(sendReportArticle!=null && sendReportArticle.equals("delet")){
			boolean result = reportArticleService.deleteArticle(bean);
			if(!result){
				request.setAttribute("delete", 0);
			}else{
				request.setAttribute("delete", 1);
			}
			request.getRequestDispatcher("//管理人員頁面路徑").forward(request, response); 
		}else {
			errors.put("action", "Unknown Action:"+sendReportArticle);
			request.getRequestDispatcher("").forward(request, response); //目前沒有頁面
		}
		
	}

}
