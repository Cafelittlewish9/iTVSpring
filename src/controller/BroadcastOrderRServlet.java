package controller;
//使用RESTfulservice的servlet，但不曉得與一般的servlet差在哪裡
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.BroadcastOrderService;
import model.vo.BroadcastOrderVO;
@WebServlet("/broadcastOrder")
public class BroadcastOrderRServlet extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 2010L;
    private BroadcastOrderService service = null;
    
//    public void setArticleRestful(ArticleRestful service){
//        this.service = service;
//    }
    public void init() throws ServletException{
    	service = new BroadcastOrderService();
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
    	String memberAccount =request.getParameter("memberAccount");
        String broadcastTitle=request.getParameter("broadcastTitle");
//      String broadcastTime=request.getParameter("broadcastTime");
//      String broadcastWatchTimes=request.getParameter("broadcastWatchTimes");
        //一樣，計數應該是寫在servlet or service裡，但怎麼做還是不太知道
//      轉換型別
        long time=new java.util.Date().getTime();
        java.sql.Timestamp broadcastTime=new java.sql.Timestamp(time);
//      呼叫model
        BroadcastOrderVO bean = new BroadcastOrderVO();
        bean.setMemberAccount(memberAccount);
        bean.setBroadcastSite("http://itvvm.cloudapp.net/ITV/LiveShow?memberAccount="+memberAccount);
        bean.setBroadcastTitle(broadcastTitle);
        bean.setBroadcastTime(broadcastTime);
        //開台時間其實實況主應該要能選擇，不過想限定成實況主按下開始實況按鈕時才送入DB
        //DB表有限定memberAccount不能重複，代表實況主不能在頁面上指定開台時間
        service.createBroadcast(bean);
//      setAttribute共享資訊並轉交
        request.setAttribute("broadcastOrderList", service.broadcastOrder());
    }

    //其實這個應該做在結束實況的按鈕上
    public void delete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String memberAccount =request.getParameter("memberAccount");
        service.removeBroadcast(memberAccount);//為什麼要用上一個bean去刪除，其實用memberAccount就好…
        request.setAttribute("broadcastOrderList", service.broadcastOrder());
    }

    public void list(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String keyword =request.getParameter("keyword");
    	//搜尋可能無法這麼便宜行事
        request.setAttribute("articleList", service.searchBroadcast(keyword));        
    }
    
    public void update(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	//開台後其實唯一能改的就只有broadcastTitle，帳號原本就不能改，網址是站方配的
    	//實況主要改時間別無他法只能先刪掉舊有的實況再開一次
    	String memberAccount =request.getParameter("memberAccount");
    	String broadcastTitle=request.getParameter("broadcastTitle");
    	BroadcastOrderVO bean = new BroadcastOrderVO();
        bean.setBroadcastTitle(broadcastTitle);
        service.changeTitle(bean);
        request.setAttribute("articleList", service.searchBroadcast(memberAccount));
        //改完應該會想再看一下吧
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
			} else if (operation.equals("update")) {
				update(request, response);
				viewName = ".html";
			}
        }

        RequestDispatcher view = request.getRequestDispatcher(viewName);
        view.forward(request, response);
    }   

}