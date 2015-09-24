package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.VideoService;
import model.vo.VideoVO;
@WebServlet("/videos")
public class Video extends HttpServlet {
	private VideoService service;

	@Override
	public void init() throws ServletException {
		this.service = new VideoService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Collection<VideoVO> list = service.hotVideo();
		request.setAttribute("videolist", list);
		request.getRequestDispatcher("Videos.jsp").forward(request, response);
	}
	
}
