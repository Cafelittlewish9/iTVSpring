package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.service.MemberService;
import util.ConvertType;

@WebServlet(name = "CloudServlet", urlPatterns = { "/cloud" })
public class CloudServlet extends HttpServlet {

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
		Part filePart1 = request.getPart("memberPhoto");
		String header = filePart1.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));

		InputStream in = filePart1.getInputStream();

		System.out.println(in.available());
		String path = "d:/png/" + filename;
		OutputStream out = new FileOutputStream(path);
		byte[] buffer = new byte[in.available()];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
//			out.write(buffer, 0, length);
//			int i = 1;
			System.out.println(buffer);
			String temp = ConvertType.convertToBase64(buffer, "png");
			System.out.println(temp);
//			i += 1;
		}
		out.close();
		in.close();

	}

}
