package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import model.service.VideoService;
import model.vo.VideoVO;
import util.ConvertType;

/**
 * Servlet implementation class VideoServlet
 */
@WebServlet("/VideoServlet")
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoServlet() {
        super();
    }
    
    public JSONArray convertToJson(Collection<VideoVO> result){
    	JSONArray list = new JSONArray();
		for (VideoVO row : result) {
			Map map =new HashMap();
			map.put("videoId",row.getVideoId());
			map.put("memberId",row.getMemberId());
			map.put("videoClassName",row.getVideoClassName());
			map.put("videoName",row.getVideoName());
			map.put("videoTitle",row.getVideoTitle());
			map.put("videoWebsite",row.getVideoWebsite());
			map.put("videoPath",row.getVideoPath());
			map.put("videoUploadTime",row.getVideoUploadTime()+"");
			map.put("videoWatchTimes",row.getVideoWatchTimes());
			map.put("videoDescription",row.getVideoDescription());
			map.put("videoDescriptionModifyTime", row.getVideoDescriptionModifyTime()+"");
			map.put("memberAccount", row.getMember().getMemberAccount());
			list.add(map);
		}
    	return list;
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//接收資料
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(date));
//		java.sql.Timestamp videoUploadTime = new java.sql.Timestamp(date.getTime());
		
		String Website = "http://itvvm.cloudapp.net/ITV/PlayVideo.jsp?filename=";
		String Path = "../mp4/";
		String extension = ".mp4";
				
				String memberId = request.getParameter("memberId");
				String videoId = request.getParameter("videoId");
				String videoClassName = request.getParameter("videoClassName");
				String videoTitle = request.getParameter("videoTitle");
				String videoName = request.getParameter("videoName");
				String videoWebsite = Website + videoName;
				String videoPath = Path + videoName +extension;
				java.sql.Timestamp videoUploadTime = new java.sql.Timestamp(date.getTime());
				String videoDescription = request.getParameter("videoDescription");
				java.sql.Timestamp videoDescriptionModifyTime = new java.sql.Timestamp(date.getTime());
				String prodaction = request.getParameter("prodaction");
		
		//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		
		if(prodaction!=null) {
			if(prodaction.equals("Insert")) {
				if(videoName==null || videoName.length()==0) {
					errors.put("videoName", "Please ADD a file for"+ prodaction );
				}
			}else if (prodaction.equals("Update")||prodaction.equals("delete")){
				//還沒寫完
			}
		}	
		
		//轉換資料
		int id = 0;
		int vid = 0;
		
		if(memberId!=null && memberId.length()!=0) {
			id = ConvertType.convertToInt(memberId);
			if(id==-1000) {
				errors.put("id", "Id must be an integer");
			}
		}
		
		if(videoId!=null && videoId.length()!=0) {
			vid = ConvertType.convertToInt(videoId);
			if(vid==-1000) {
				errors.put("id", "Id must be an integer");
			}
		}
		
		//呼叫Model
		VideoVO bean = new VideoVO();
		bean.setMemberId(id);
		bean.setVideoId(vid);
		bean.setVideoClassName(videoClassName);
		bean.setVideoTitle(videoTitle);
		bean.setVideoName(videoName);
		bean.setVideoWebsite(videoWebsite);
		bean.setVideoPath(videoPath);
		bean.setVideoUploadTime(videoUploadTime);
		bean.setVideoDescription(videoDescription);
		bean.setVideoUploadTime(videoDescriptionModifyTime);
		
		//根據Model執行結果導向View
		VideoService vs = new VideoService();

		
		
		if(prodaction!=null && prodaction.equals("Select")) {
			Collection<VideoVO> result = vs.hotVideo();
			request.setAttribute("select", result);
			request.getRequestDispatcher(
					"/pages/Success.jsp").forward(request, response);
			
		}else if(prodaction!=null && prodaction.equals("Insert")) {
			boolean result = vs.uploadVideo(bean);
			if(result==false) {
				errors.put("action", "ADD fail");
			} else {
				
			}
		}else if(prodaction!=null && prodaction.equals("Update")) {
			System.out.println("Update");
			System.out.println(bean.getVideoId());
			boolean result = vs.updateVideo(bean);
			PrintWriter out = response.getWriter();
			if(result==false) {
				out.println(result);
			} else {
				out.println(result);
			}
			
		} else if(prodaction!=null && prodaction.equals("Delete")) {
			boolean result = vs.removeVideo(bean.getVideoId());
			PrintWriter out = response.getWriter();
			if(result==false) {
				out.println(result);
			} else {
				out.println(result);
			}
		} else if(prodaction!=null && prodaction.equals("searchVideoName")) {
			Collection<VideoVO> res = vs.searchVideoName(bean.getVideoName());
			if(res==null) {
				request.setAttribute("searchVideoName", "can't Search");
			} else {
				
				//轉換contentType必要
				response.setContentType("text/html; charset=utf-8");
				String videoname = JSONValue.toJSONString(this.convertToJson(res));
				PrintWriter out = response.getWriter();
				out.println(videoname);
			}
			
		}else if(prodaction!=null && prodaction.equals("searchMemberId")) {
			Collection<VideoVO> res = vs.searchMemberId(bean.getMemberId());
			if(res==null) {
				request.setAttribute("searchMemberId", "can't Find");
			} else {
				
				//轉換contentType必要
				response.setContentType("text/html; charset=utf-8");
				String videoname = JSONValue.toJSONString(this.convertToJson(res));
				PrintWriter out = response.getWriter();
				out.println(videoname);
			}
			
		}else  {
			//沒有按時直接送這段
			
			Collection<VideoVO> res = vs.searchVideo(bean.getVideoTitle());
			
			//轉換contentType必要
			response.setContentType("text/html; charset=utf-8");
			String json = JSONValue.toJSONString(this.convertToJson(res));
			PrintWriter out = response.getWriter();
			out.println(json);
		}
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    this.doGet(request, response);
	}

}
