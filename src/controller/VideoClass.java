package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.service.VideoService;
import model.vo.VideoVO;

@WebServlet("/videos/class")
public class VideoClass extends HttpServlet {
	private VideoService service;

	@Override
	public void init() throws ServletException {
		service = new VideoService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String videoClassName = req.getParameter("videoClassName");
		System.out.println(videoClassName);
		Collection<VideoVO> list = null;
		if (videoClassName != null && videoClassName.trim().length() != 0) {
			System.out.println("videoClassName!=null");
			list = service.videoClassList(videoClassName);
		} else {
			System.out.println("videoClassName==null");
			list = service.hotVideo();
		}
		resp.setCharacterEncoding("UTF-8");
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		System.out.println(obj.toString());
		resp.getWriter().write(obj.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
