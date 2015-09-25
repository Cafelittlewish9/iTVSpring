package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.service.MemberService;
import model.service.VideoCommentsService;
import model.vo.MemberVO;
import model.vo.VideoCommentsVO;
import util.ConvertType;

@WebServlet("/VideoCommentServlet")
public class VideoCommentServlet extends HttpServlet {
	private VideoCommentsService service;

	@Override
	public void init() throws ServletException {
		service = new VideoCommentsService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int videoId = ConvertType.convertToInt(request.getParameter("videoId"));
		List<VideoCommentsVO> list = service.videoCommentsList(videoId);

		JSONObject jsonObj = new JSONObject();
		for (VideoCommentsVO bean : list) {
			if (bean.getMember().getMemberPhoto() != null) {
				String b64 = java.util.Base64.getEncoder().encodeToString(bean.getMember().getMemberPhoto());
				bean.getMember().setMemberNickname(b64);
			}
			System.out.println(bean.getCommentContent());
			System.out.println(bean.getVideoId());
		}
		
		jsonObj.put("list", list);
		System.out.println(jsonObj.toString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonObj.toString());
	}
	/*public JSONArray convertToJson(Collection<VideoCommentsVO> result){
		JSONArray list = new JSONArray(); //this is Json simple's JSONArray
		for(VideoCommentsVO row : result){
			Map showAllVideoComments = new HashMap();
			showAllVideoComments.put("commentId", row.getCommentId());
			showAllVideoComments.put("memberId", row.getMemberId());
			showAllVideoComments.put("videoId", row.getVideoId());
			showAllVideoComments.put("commentContent", row.getCommentContent());
			showAllVideoComments.put("commentTime", row.getCommentTime()+"");
			showAllVideoComments.put("memberAccount", row.getMember().getMemberAccount());
			list.add(showAllVideoComments);
		}
    	return list;
    }*/
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String memberNickname = request.getParameter("memberNickname");
		// String memberPhoto = request.getParameter("memberPhoto");

		String videoId = request.getParameter("videoId");

		String commentId = request.getParameter("commentId");
		String commentContent = request.getParameter("commentContent");
		String commentTime = request.getParameter("commentTime");
		String sendComment = request.getParameter("sendComment");

		String memberId = request.getParameter("memberId");
		String memberAccount = request.getParameter("memberAccount");
		String memberPassword = request.getParameter("memberPassword");
		MemberService memberService = new MemberService();
		MemberVO checkMemberInfo = memberService.login1(memberAccount, memberPassword);

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		// 判斷是會員才可新刪修
		if (sendComment != null && memberAccount.equals(checkMemberInfo)) {

			if (sendComment.equals("insert") || sendComment.equals("update")) {
				if (commentContent == null || commentContent.length() == 0) {
					errors.put("commentContent", "請輸入留言後再" + sendComment);
				}
			} else if (sendComment.equals("delete")) {
				if (!memberAccount.equals(checkMemberInfo)) {
					errors.put("memberAccount", "請勿刪除其他會員留言");
				}
			}
		}

		// 轉換資料
		int convertVideoId = 0;
		if (videoId != null && videoId.length() != 0) {
			convertVideoId = ConvertType.convertToInt(videoId);
			if (convertVideoId == -1000) {
				errors.put("videoId", "VideoID MUST Be a Integer.");
			}
		}

		int convertMemberId = 0;
		if (memberId != null && memberId.length() != 0) {
			convertMemberId = ConvertType.convertToInt(memberId);
			if (convertMemberId == -1000) {
				errors.put("memberId", "MemberID MUST Be a Integer.");
			}
		}

		Timestamp convertCommentTime = null;
		if (commentTime != null && commentTime.length() != 0) {
			SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
			try {
				convertCommentTime = (Timestamp) dsf.parse(commentTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (convertMemberId == -1000) {
				errors.put("commentTime", "CommentTime MUST Be a Timestamp.");
			}
		}

		int convertCommentId = 0;
		if (commentId != null && commentId.length() != 0) {
			convertCommentId = ConvertType.convertToInt(commentId);
			if (convertCommentId == -1000) {
				errors.put("commentId", "CommentID MUST Be a Integer.");
			}
		}

		// 導向View

		// //傳送資料庫內的留言
		// VideoCommentsService videoCommentsService = new
		// VideoCommentsService();
		// if(videoCommentsService.selectAllComments()!=null){
		// Collection<VideoCommentsVO> showComments =
		// service.selectAllComments();
		// if(showComments!=null){
		// request.setAttribute("showComments", showComments);
		// }else {
		// request.setAttribute("showComments", showComments);
		// }
		// request.getRequestDispatcher("PlayVideo.jsp").forward(request,
		// response);
		// }

		// 傳送會員暱稱
//		if (memberService.getMemberNickname(memberAccount) != null && memberAccount.equals(checkMemberInfo)) {
//			String showMemberNickname = memberService.getMemberNickname(memberAccount);
//			if (showMemberNickname != null) {
//				request.setAttribute("showMemberNickname", showMemberNickname);
//			}
//			request.getRequestDispatcher("PlayVideo.jsp").forward(request, response);
//		}

		// select, Insert, Update, Delete
		// if(sendComment != null && sendComment.equals("select")){
		// Collection<VideoCommentsVO> showComments =
		// service.selectAllComments();
		// request.setAttribute("showComments", showComments);
		// request.getRequestDispatcher("PlayVideo.jsp").forward(request,
		// response);
		// }else if(sendComment != null && sendComment.equals("insert")){
		// boolean result = service.insertVideoComments(convertMemberId,
		// convertVideoId, commentContent);//commentContent不知道有沒有抓到東西:(
		// if(!result){
		// request.setAttribute("insert", 0);
		// }else{
		// request.setAttribute("insert", 1);
		// }
		// request.getRequestDispatcher("PlayVideo.jsp").forward(request,
		// response);
		// }else if(sendComment != null && sendComment.equals("update")){
		// boolean result = service.updateVideoComments(commentContent,
		// convertCommentTime, convertCommentId);
		// if(!result){
		// request.setAttribute("update", 0);
		// }else{
		// request.setAttribute("update", 1);
		// }
		// request.getRequestDispatcher("PlayVideo.jsp").forward(request,
		// response);
		// }else if(sendComment != null && sendComment.equals("delete")){
		// boolean result = service.deleteVideoComments(convertCommentId);
		// if(!result){
		// request.setAttribute("delete", 0);
		// }else{
		// request.setAttribute("delete", 1);
		// }
		// request.getRequestDispatcher("PlayVideo.jsp").forward(request,
		// response);
		// }else{
		// errors.put("action", "Unknown Action:"+sendComment);
		// request.getRequestDispatcher("PlayVideo.jsp").forward(request,
		// response);
		// }

	}

}
