package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import model.service.ShowService;
import model.vo.ShowVO;
import model.vo.VideoVO;
import util.ConvertType;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowServlet() {
        super();
        
    }
    
    public JSONArray convertToJson(Collection<ShowVO> result){
    	JSONArray list = new JSONArray();
		for (ShowVO row : result) {
			Map map =new HashMap();
			map.put("memberId",row.getMemberId());
			map.put("showTime",row.getShowTime()+"");
			map.put("website",row.getVideo().getVideoWebsite());
			map.put("videoTitle", row.getVideo().getVideoTitle());
			list.add(map);
		}
    	return list;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收資料
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Website = "http://itvvm.cloudapp.net/ITV/PlayVideo.jsp?filename=";
		
		String memberId = request.getParameter("memberId");
		java.sql.Timestamp showTime = new java.sql.Timestamp(date.getTime());
		String videoId = request.getParameter("videoId");
		String prodaction = request.getParameter("prodaction");
		
		System.out.println(memberId);
		System.out.println(videoId);
		System.out.println(prodaction);
		//驗證資料
				Map<String, String> errors = new HashMap<String, String>();
				request.setAttribute("error", errors);
				
				if(prodaction!=null) {
					if(prodaction.equals("Insert")) {
						if(memberId==null || memberId.length()==0) {
							errors.put("memberId", "Please ADD Your Id for"+ prodaction );
						}
					}else if (prodaction.equals("Update")||prodaction.equals("delete")){
						//還沒寫完
					}
				}
		//轉換資料
		int id = 0;
		if(memberId!=null && memberId.length()!=0) {
			id = ConvertType.convertToInt(memberId);
			if(id==-1000) {
				errors.put("id", "Id must be an integer");
			}
		}
		
		//呼叫Model
		ShowVO bean = new ShowVO();
		bean.setMemberId(id);
		bean.setShowTime(showTime);
		VideoVO video = new VideoVO();
		video.setVideoId(ConvertType.convertToInt(videoId));
		bean.setVideo(video);
		
		
		//根據Model執行結果導向View
		ShowService ss = new ShowService();
		
		
		if(prodaction!=null && prodaction.equals("Select")) {
			Collection<ShowVO> result = ss.showList(bean.getMemberId());
			request.setAttribute("select", result);
			request.getRequestDispatcher(
					"/pages/Success.jsp").forward(request, response);
		}else if(prodaction!=null && prodaction.equals("Check")) {
			ShowVO result = ss.checkShow(bean.getMemberId(),bean.getVideo().getVideoId());
			PrintWriter out = response.getWriter();
			if(result!=null){
				JSONArray one = new JSONArray();
				
					Map map =new HashMap();
					map.put("memberId",result.getMemberId());
					map.put("showTime",result.getShowTime()+"");
					map.put("website",result.getVideo().getVideoTitle());
					
				//轉換contentType必要
				response.setContentType("text/html; charset=utf-8");
				String json = JSONValue.toJSONString(one);
				
				out.println(json);
			}else	out.println(result);
					
		}else if(prodaction!=null && prodaction.equals("Insert")) {
			Collection<ShowVO> result = ss.addShow(bean);
			if(result==null) {
				errors.put("action", "ADD fail");
			} else {
				
				JSONArray one = new JSONArray();
				for (ShowVO row : result) {
					Map map =new HashMap();
					map.put("memberId",row.getMemberId());
					map.put("showTime",row.getShowTime()+"");
					map.put("website",row.getVideo().getVideoTitle());
					one.add(map);
				}
				//轉換contentType必要
				response.setContentType("text/html; charset=utf-8");
				String insert = JSONValue.toJSONString(one);
				PrintWriter out = response.getWriter();
				out.println(insert);
				
//				request.setAttribute("insert", insert);
			}
//			request.getRequestDispatcher(
//					"/pages/Success.jsp").forward(request, response);
		}else if(prodaction!=null && prodaction.equals("Update")) {
			Collection<ShowVO> result = ss.changeShow(bean);
			if(result==null) {
				errors.put("action", "Update fail");
			} else {
				request.setAttribute("update", result);
			}
			request.getRequestDispatcher(
					"/pages/Success.jsp").forward(request, response);
		} else if(prodaction!=null && prodaction.equals("Delete")) {
			Collection<ShowVO> result = ss.removeShow(bean);
			
			//轉換contentType必要
			response.setContentType("text/html; charset=utf-8");
			String json = JSONValue.toJSONString(this.convertToJson(result));
			PrintWriter out = response.getWriter();
			out.println(json);
			
		} else{
			//沒有按時直接送這段
			Collection<ShowVO> result = ss.showList(bean.getMemberId());
			//轉換contentType必要
			response.setContentType("text/html; charset=utf-8");
			String json = JSONValue.toJSONString(this.convertToJson(result));
			PrintWriter out = response.getWriter();
			out.println(json);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 this.doGet(request, response);
	}

}
