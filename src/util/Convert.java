package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Convert
 */
@WebServlet("/Convert")
public class Convert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ConvertBegin cb = new ConvertBegin(); 
//		cb.start();
		String[] ff; 
		
		String videoName = request.getParameter("videoName");
		
		while(true) { 
			String folderpath = "C:\\Spring\\tomcat8\\webapps\\video"; 
			// String path = null; 
			File f = new File (folderpath); 

				if (f.isDirectory()) { 
				ff = f.list(); 
				int i = 0; 
				List<String> filename = new ArrayList<>();
				
					System.out.println("第一筆資料: "+ff[0]);

					
					while (i <ff.length) {
						if(ff[i].substring(0, ff[i].lastIndexOf(".")).equals(videoName)){
							filename.add(ff[i]);
							try {
								
								new ConvertVideo(ff[i]).beginConvert();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							i++;
						}else{
							System.out.println(i);
							i++; 
						}
					}
					request.setAttribute("FileName", filename);
					
					ff = null; 
				} 
				
				f = null;
		
				try { 
				Thread.sleep(10000); 
				} catch (InterruptedException e) { 
				// If that fails restart the thread 
//				this.start(); 
				} 
				
//				request.getRequestDispatcher("/result/success.jsp").forward(request, response);
		        return;
			} 
		
			
	}

}
