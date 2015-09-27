package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.service.MemberService;
import model.vo.MemberVO;
import util.ConvertType;

@WebServlet(name = "Photo", urlPatterns = { "/upload" })
public class Photo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MemberService ms;

	public void init() throws ServletException {
		ms = new MemberService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		Part filePart = request.getPart("memberPhoto");
		String updateMemberInfo = request.getParameter("operation");
		if (updateMemberInfo != null && updateMemberInfo.equals("update")&&filePart!=null ){
//			String username=request.getParameter("memberAccount");//用tester嘗試時需要用這個參數去資料庫拿到VO，見53行
			String header = filePart.getHeader("Content-Disposition");
			String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));

			InputStream in = filePart.getInputStream();

//			System.out.println(in.available());
//			String path = "Y:/" + filename;//如果是想寫到硬碟去的話需要有路徑，寫去DB就不用，只是jdbc方法要寫好就是了
//			OutputStream out = new FileOutputStream(path);
			byte[] buffer = new byte[in.available()];
			int length = -1;
//			MemberVO member=ms.searchByMemberAccount(username);//見42行
			while ((length = in.read(buffer)) != -1) {
//				out.write(buffer, 0, length);
//				int i = 1;
//				System.out.println(buffer);
				String temp = ConvertType.convertToBase64(buffer, "png");
				member.setMemberName(temp);
//				System.out.println(temp);
//				i += 1;
			}
			System.out.println("he");

			member.setMemberPhoto(buffer);
			ms.update(member);
//			out.close();//有寫到硬碟去的話就需要關資源
			in.close();
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath())+"/PersonalPage.jsp");
		}else{
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath())+"/HomePageVersion3.jsp");
		}
		
	}
}
