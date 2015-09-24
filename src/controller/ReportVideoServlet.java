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

import model.service.MemberService;
import model.service.ReportVideoService;
import model.vo.MemberVO;
import model.vo.ReportVideoVO;
import util.ConvertType;

@WebServlet("/ReportVideoServlet")
public class ReportVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ReportVideoService service;

	@Override
	public void init() throws ServletException {
		service = new ReportVideoService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	//尚未測試
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String orderId = request.getParameter("orderId");
		String reportVideoId = request.getParameter("reportVideoId");
		String reportedVideoId = request.getParameter("reportedVideoId");
		String reportTime = request.getParameter("reportTime");
		String reportReason = request.getParameter("reportReason");
		String sendReportVideoMessage = request.getParameter("sendReportVideoMessage");
		
		String memberAccount = request.getParameter("memberAccount");
		String memberPassword = request.getParameter("memberPassword");
		String memberNickname = request.getParameter("memberNickname");
		MemberService memberService = new MemberService();
		MemberVO checkMemberInfo = memberService.login1(memberAccount, memberPassword);
		
		Map<String , String> errors = new HashMap<String , String>();
		request.setAttribute("errors", errors);
		
		//判斷是否是會員才可檢舉
		if(sendReportVideoMessage!=null && memberAccount.equals(checkMemberInfo)){
			if(sendReportVideoMessage.equals("insert")){
				if(reportReason == null || reportReason.length() == 0){
					errors.put("reportReason", "請輸入檢舉事由");
				}
			}else if(sendReportVideoMessage.equals("delet")){
				if(!memberAccount.equals(checkMemberInfo)){
					errors.put("memberAccount", "請先登入管理人員帳號");
				}
			}
		}
		
		//轉換資料
		int convertReportedVideoId =0;
		if(reportedVideoId!=null && reportedVideoId.length()!=0){
			convertReportedVideoId = ConvertType.convertToInt(reportedVideoId);
			if(convertReportedVideoId==-1000){
				errors.put("commentId", "CommentID MUST Be a Integer.");
			}
		}
		
		Date convertreportTime = null;
		if(reportTime!=null && reportTime.length()!=0){
			SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
			try {
				convertreportTime = (Date) dsf.parse(reportTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(convertreportTime == null){
				errors.put("reportTime", "ReportTime CAN NOT Null.");
			}
		}
		
		int convertOrderId =0;
		if(orderId!=null && orderId.length()!=0){
			convertOrderId = ConvertType.convertToInt(orderId);
			if(convertOrderId==-1000){
				errors.put("OrderId", "OrderId MUST Be a Integer.");
			}
		}
		
		//導向View
		
		//管理員後台顯示被檢舉影片
		ReportVideoService reportVideoService = new ReportVideoService();
		if(reportVideoService.selectAllList()!=null){
			Collection<ReportVideoVO> showReportedVideos = reportVideoService.selectAllList();
			if(showReportedVideos!=null){
				request.setAttribute("showReportedVideos", showReportedVideos);
			}else{
				request.setAttribute("showReportedVideos", showReportedVideos);
			}
			request.getRequestDispatcher("//管理員後台的頁面路徑").forward(request, response);
		}
		
		//呼叫Model
		ReportVideoVO bean = new ReportVideoVO();
		bean.setOrderId(convertOrderId);
		bean.setReportedVideoId(convertReportedVideoId);
		
		//檢舉影片、管理員後台刪除檢舉
		if(sendReportVideoMessage != null && sendReportVideoMessage.equals("insert")){
			boolean result = reportVideoService.addReportVideo(bean);
			if(!result){
				request.setAttribute("insert", 0);
			}else{
				request.setAttribute("insert", 1);
			}
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response); //路徑不確定，SORRY
		}else if(sendReportVideoMessage != null && sendReportVideoMessage.equals("delete")){
			boolean result = reportVideoService.deleteVideo(bean);
			if(!result){
				request.setAttribute("delete", 0);
			}else{
				request.setAttribute("delete", 1);
			}
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response); //路徑不確定，SORRY
		}else {
			errors.put("action", "Unknown Action:"+sendReportVideoMessage);
			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response); //路徑不確定，SORRY
		}
		
	}

}
