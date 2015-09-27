package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import model.service.BroadcastOrderService;
import model.service.MemberService;
import model.vo.BroadcastOrderVO;
import model.vo.MemberVO;

/**
 * Servlet implementation class BroadcastOrderServlet
 */
@WebServlet("/BroadcastOrderServlet")
public class BroadcastOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService mService;
	
	@Override
	public void init() throws ServletException {
		mService = new MemberService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收資料
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String Website = "http://itvvm.cloudapp.net/ITV/LiveShow?memberAccount=";
		
		String memberId = request.getParameter("memberId");
//		其實應該從session裡的MemberVO抓memberId
		String broadcastSite = request.getParameter("broadcastSite");
		String broadcastTitle = request.getParameter("broadcastTitle");
		String Time = request.getParameter("broadcastTime");
		
		//日期轉換
		java.sql.Timestamp broadcastTime=null;
		if(Time!=null){
			String[] datetime = Time.split(" ");
			String[] datemonth = datetime[0].split("/");
			String newtime = datemonth[2]+"-"+datemonth[1]+"-"+datemonth[0]+" "+datetime[1]+":00";
			
			
			Date BroadcastTime = null;
			try {
				BroadcastTime = sdf.parse(newtime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			broadcastTime = new java.sql.Timestamp(BroadcastTime.getTime());
		}
		String prodaction = request.getParameter("prodaction");
		
		//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		
		if(prodaction!=null) {
			if(prodaction.equals("Insert")) {
				if(memberId==null ) {
					errors.put("memberAccount", "請輸入您的帳號以開啟實況");
				}
			}else if (prodaction.equals("Update")){
				errors.put("memberAccount", "請輸入您的帳號以開啟實況");
			}else if(prodaction.equals("delete")){
				errors.put("memberAccount", "請輸入您的帳號以開啟實況");
			}
		}	
		
		//呼叫Model
				MemberVO mvo=new MemberVO();
				String memberAccount=mvo.getMemberAccount();
				BroadcastOrderVO bean = new BroadcastOrderVO();
				bean.setMember(mService.searchByMemberAccount(memberAccount));
				bean.setBroadcastSite(broadcastSite);
				bean.setBroadcastTitle(broadcastTitle);
				bean.setBroadcastTime(broadcastTime);
				
		//根據Model執行結果導向View
				BroadcastOrderService bs = new BroadcastOrderService();
				
				
				if(prodaction!=null && prodaction.equals("Select")) {
					Collection<BroadcastOrderVO> result = bs.broadcastOrder();
					request.setAttribute("select", result);
					request.getRequestDispatcher(
							"/pages/Success.jsp").forward(request, response);
					
				}else if(prodaction!=null && prodaction.equals("Insert")) {
					BroadcastOrderVO result = bs.createBroadcast(bean);
					if(result==null) {
						errors.put("action", "ADD fail");
					} else {
						
						JSONArray list = new JSONArray();
							Map map =new HashMap();
							map.put("memberId",result.getMember().getMemberId());
							map.put("broadcastSite",result.getBroadcastSite());
							map.put("broadcastTitle",result.getBroadcastTitle());
							map.put("broadcastTime",result.getBroadcastTime()+"");
							map.put("broadcastWatchTimes",result.getBroadcastWatchTimes());
							list.add(map);
						
						//轉換contentType必要
						response.setContentType("text/html; charset=utf-8");
						String insert = JSONValue.toJSONString(list);
						PrintWriter out = response.getWriter();
						out.println(insert);
						
					}

				}else if(prodaction!=null && prodaction.equals("Update")) {
					boolean result = bs.changeTitle(bean);
					if(result==false) {
						errors.put("action", "修改失敗");
					} else {
						request.setAttribute("update", result);
					}
					request.getRequestDispatcher(
							"/pages/Success.jsp").forward(request, response);
				} else if(prodaction!=null && prodaction.equals("Delete")) {
					boolean result = bs.removeBroadcast(bean.getMember().getMemberAccount());
					PrintWriter out = response.getWriter();
					out.println(result);
				
				} else if(prodaction!=null && prodaction.equals("searchAccount")) {
					System.out.println(prodaction);
					BroadcastOrderVO res = bs.searchAccount(bean.getMember().getMemberAccount());
					if(res==null){
						PrintWriter out = response.getWriter();
						out.println("Not Login");
					}
					JSONArray list = new JSONArray();
					
						Map map =new HashMap();
						map.put("memberId",res.getMember().getMemberId());
						map.put("broadcastSite",res.getBroadcastSite());
						map.put("broadcastTitle",res.getBroadcastTitle());
						map.put("broadcastTime",res.getBroadcastTime()+"");
						map.put("broadcastWatchTimes",res.getBroadcastWatchTimes());
						list.add(map);
						
//					//轉換contentType必要
					response.setContentType("text/html; charset=utf-8");
					String json = JSONValue.toJSONString(list);
					PrintWriter out = response.getWriter();
					out.println(json);
					
					
				}else  {
					System.out.println(prodaction);
					//沒有按時直接送這段
					Collection<BroadcastOrderVO> res = bs.broadcastOrder();
					JSONArray list = new JSONArray();
					for (BroadcastOrderVO row : res) {
						Map map =new HashMap();
						map.put("memberId",row.getMember().getMemberId());
						map.put("broadcastSite",row.getBroadcastSite());
						map.put("broadcastTitle",row.getBroadcastTitle());
						map.put("broadcastTime",row.getBroadcastTime()+"");
						map.put("broadcastWatchTimes",row.getBroadcastWatchTimes());
						map.put("server", row.getMember().getBroadcastWebsite());
						list.add(map);
					}
//					//轉換contentType必要
					response.setContentType("text/html; charset=utf-8");
					String json = JSONValue.toJSONString(list);
					PrintWriter out = response.getWriter();
					out.println(json);
				}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
