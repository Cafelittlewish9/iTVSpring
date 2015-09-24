package controller;
//使用RESTfulservice的servlet，但不曉得與一般的servlet差在哪裡
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ArticleService;
import model.vo.ArticleVO;
@WebServlet("/article")
public class ArticleRServlet extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 2010L;
    private ArticleService service = null;
    
//    public void setArticleRestful(ArticleRestful service){
//        this.service = service;
//    }
    public void init() throws ServletException{
    	service = new ArticleService();
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
        String subclassNo=request.getParameter("subclassNo");
        String articleTitle=request.getParameter("articleTitle");
        String articleContent=request.getParameter("articleContent");
//      String watchTimes=request.getParameter("watchTimes");
        //watchTimes的計數應該是寫在servlet or service裡，但怎麼做還是不太知道
//      轉換型別
        int id=Integer.parseInt(memberId);
        long time=new java.util.Date().getTime();
        java.sql.Timestamp publishTime=new java.sql.Timestamp(time);
//      呼叫model
        ArticleVO bean = new ArticleVO();
        bean.setMemberId(id);
        bean.setSubclassNo(subclassNo);
        bean.setArticleTitle(articleTitle);
        bean.setArticleContent(articleContent);
        bean.setPublishTime(publishTime);
        bean.setModifyTime(publishTime);
        service.addArticle(bean);
//      setAttribute共享資訊並轉交
        request.setAttribute("articleList", service.allArticle());
    }
    //要如何只讓作者本人及管理員看到刪除這顆按鈕?權限角色?!
    public void delete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        String articleId = request.getParameter("articleId");
        int id=Integer.parseInt(articleId);
        ArticleVO bean = new ArticleVO();
        bean.setArticleId(id);
        service.deleteArticle(bean);
        request.setAttribute("articleList", service.allArticle());
    }

    public void list(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String keyword =request.getParameter("keyword");
    	//搜尋可能無法這麼便宜行事
        request.setAttribute("articleList", service.searchByInput("", keyword));        
    }
    
    public void update(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
    	String articleId=request.getParameter("articleId");
    	String memberId = request.getParameter("memberId");
        String subclassNo=request.getParameter("subclassNo");
        String articleTitle=request.getParameter("articleTitle");
        String articleContent=request.getParameter("articleContent");
    	int id=Integer.parseInt(memberId);
        long time=new java.util.Date().getTime();
        java.sql.Timestamp modifyTime=new java.sql.Timestamp(time);
        ArticleVO bean = new ArticleVO();
        bean.setMemberId(id);
        bean.setSubclassNo(subclassNo);
        bean.setArticleTitle(articleTitle);
        bean.setArticleContent(articleContent);
        bean.setModifyTime(modifyTime); 
        service.modifyArticle(bean);
        request.setAttribute("articleList", service.searchByInput(subclassNo, articleTitle));
        //改完應該會想再看一下同一篇文章吧
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