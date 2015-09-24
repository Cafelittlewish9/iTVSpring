package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ArticleClassService;
import model.vo.ArticleClassVO;
@WebServlet("/articleClass")
public class ArticleClassRServlet extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 2010L;
    private ArticleClassService service = null;
    
//    public void setArticleClassRestful(setArticleClassRestful service){
//        this.service = service;
//    }
    public void init() throws ServletException{
    	service = new ArticleClassService();
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
    	String subclassNo =request.getParameter("subclassNo");
        String subclassName=request.getParameter("subclassName");
        String className=request.getParameter("className");
//      轉換型別…結果三個型態剛好都是String沒必要轉
//      呼叫model
        ArticleClassVO bean = new ArticleClassVO();
        bean.setSubclassNo(subclassNo);
        bean.setSubclassName(subclassName);
        bean.setClassName(className);
        service.addArticleClass(bean);
//      setAttribute共享資訊並轉交，驚覺這個類別根本就沒有select指令，不太知道接下來要轉啥給畫面
        //request.setAttribute("articleClassList", );
    }
    
    public void delete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String subclassNo =request.getParameter("subclassNo");
        service.deleteArticleClass(subclassNo);
//      request.setAttribute("articleClassList", );
    }

    /*public void list(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String memberId =request.getParameter("memberId");
    	int id=Integer.parseInt(memberId);
    	request.setAttribute("showList", service.showList(id));        
    }*/
    
    public void update(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String subclassNo =request.getParameter("subclassNo");
        String subclassName=request.getParameter("subclassName");
        String className=request.getParameter("className");
        ArticleClassVO bean = new ArticleClassVO();
        bean.setSubclassNo(subclassNo);
        bean.setSubclassName(subclassName);
        bean.setClassName(className);
        service.updateArticleClass(bean);
//      request.setAttribute("articleClassList", );
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
			} 
			/*else if (operation.equals("list")) {
				list(request, response);
				viewName = ".html";
			}
			*/ else if (operation.equals("update")) {
				update(request, response);
				viewName = ".html";
			}
        }

        RequestDispatcher view = request.getRequestDispatcher(viewName);
        view.forward(request, response);
    }   

}