//package filter;
//import java.io.*;
//import java.util.*;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.http.*;
////這個filter可能有問題
//import model.vo.MemberVO;
//
//@WebFilter(
//		urlPatterns = { "/*" }, 
//		initParams = { 
//				@WebInitParam(name = "mustLogin1", value = "/雲端硬碟cloud"), 
//				@WebInitParam(name = "mustLogin2", value = "/PlayVideo.jsp追蹤、讚、檢舉、影片追求跟留言"), 
//				@WebInitParam(name = "mustLogin3", value = "/PersonalPage.jsp的一切"),
//				//但其實PersionalPage的連結藏在登入後才會出現已經有<c:if判斷掉了
//				@WebInitParam(name = "mustLogin4", value = "/_20_productMaintain/*")
//				//留一個憲春老師的範例，但value似乎沒有太大意義
//		})
//public class LoginFilter implements Filter {
//	Collection<String> url = new ArrayList<String>();
//	String servletPath;
//	String contextPath;
//	String requestURI;
//	public void init(FilterConfig fConfig) throws ServletException {
//		Enumeration<String> e = fConfig.getInitParameterNames();
//		while (e.hasMoreElements()) {
//			String path = e.nextElement();
//			url.add(fConfig.getInitParameter(path));
//		}
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		boolean isRequestedSessionIdValid = false;
//		if (request instanceof HttpServletRequest
//				&& response instanceof HttpServletResponse) {
//			HttpServletRequest req = (HttpServletRequest) request;
//			HttpServletResponse resp = (HttpServletResponse) response;
//			servletPath = req.getServletPath();  
//			contextPath = req.getContextPath();
//			requestURI  = req.getRequestURI();
//			isRequestedSessionIdValid = req.isRequestedSessionIdValid();
//			
//			if (mustLogin()) {
//				if (checkLogin(req)) {   //  需要登入，已經登入
//					chain.doFilter(request, response);
//				} else {				//  需要登入，尚未登入
//					HttpSession session = req.getSession();
//					session.setAttribute("requestURI", requestURI);
//					if ( ! isRequestedSessionIdValid ) {
//						session.setAttribute("timeOut", "使用逾時，請重新登入");
//					}
//					resp.sendRedirect(contextPath + "/Login.jsp");
//					return;
//				}
//			} else {   //不需要登入
//				chain.doFilter(request, response);
//			}
//		} else {
//			throw new ServletException("Request / Response 型態錯誤");
//		}
//	}
//	private boolean checkLogin(HttpServletRequest req) {
//		HttpSession session = req.getSession();
//		MemberVO loginToken = (MemberVO) session.getAttribute("LoginOK");
//		if (loginToken == null) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	private boolean mustLogin() {
//		boolean login = false;
//		for (String sURL : url) {
//			if (sURL.endsWith("*")) {
//				sURL = sURL.substring(0, sURL.length() - 1);
//				if (servletPath.startsWith(sURL)) {
//					login = true;
//					break;
//				}
//			} else {
//				if (servletPath.equals(sURL)) {
//					login = true;
//					break;
//				}
//			}
//		}
//		return login;
//	}
//	@Override
//	public void destroy() {
//	}
//}