package controller;
//使用RESTfulservice的servlet，但不曉得與一般的servlet差在哪裡
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.BlackService;
import model.vo.BlackVO;
import model.vo.MemberVO;
@WebServlet("/black")
public class BlackRServlet extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 2010L;
    private BlackService service = null;
    
//    public void setBlackRestful(BlackRestful service){
//        this.service = service;
//    }
    public void init() throws ServletException{
    	service = new BlackService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        this.processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        this.processRequest(request, response);
    }   

    public void insert(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
//    	接收資料
    	String memberId =request.getParameter("memberId");
    	String blackedId=request.getParameter("blackedId");
//      轉換型別
        int id=Integer.parseInt(memberId);
        int bid=Integer.parseInt(blackedId);
        BlackVO bean = new BlackVO();
        MemberVO member = new MemberVO();
        member.setMemberId(id);
        bean.setMember(member);
        bean.setMemberId(id);
        //還是應該用bid去求出被黑的人的memberAccount?!
//      呼叫model
        service.insertBlackList(bean);
//      setAttribute共享資訊並轉交
        request.setAttribute("blackList", service.searchBlackAccount(id));
    }
    //要如何只讓作者本人及管理員看到刪除這顆按鈕?權限角色?!
    public void delete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String memberId =request.getParameter("memberId");
    	String blackedId=request.getParameter("blackedId");
        int id=Integer.parseInt(memberId);
        int bid=Integer.parseInt(blackedId);
        BlackVO bean = new BlackVO();
        MemberVO member = new MemberVO();
        member.setMemberId(id);
        bean.setMember(member);
        bean.setMemberId(id);
        service.removeBlackAccount(bean);
        request.setAttribute("blackList", service.searchBlackAccount(id));
    }
    
    public void removeAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        	String memberId =request.getParameter("memberId");
            int id=Integer.parseInt(memberId);
            service.removeAll(id);
            request.setAttribute("blackList", service.searchBlackAccount(id));
        }

    public void list(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	//沒有人可以看別人的黑名單，應該管理員也一樣…管理員如果閒到去看用戶設定的黑名單，那就增加管理員的工作量！
    	String memberId =request.getParameter("memberId");
    	int id=Integer.parseInt(memberId);
        request.setAttribute("blackList", service.searchBlackAccount(id));        
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String operation = request.getParameter("operation");

        String viewName = null;
        if(operation!=null){
			 if (operation.equals("insert")) {
				insert(request, response);
				viewName = ".html";
			} else if (operation.equals("delete")) {
				delete(request, response);
				viewName = ".html";
			} else if (operation.equals("list")) {
				list(request, response);
				viewName = ".html";
			} else if (operation.equals("removeAll")) {
				removeAll(request, response);
				viewName = ".html";
			}
        }

        RequestDispatcher view = request.getRequestDispatcher(viewName);
        view.forward(request, response);
    }   

}