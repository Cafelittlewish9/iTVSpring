package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.service.ShowService;
import model.vo.MemberVO;
import model.vo.ShowVO;

@WebServlet("/show")
public class Show extends HttpServlet {
	private ShowService service;

	@Override
	public void init() throws ServletException {
		this.service = new ShowService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		Collection<ShowVO> showList = service.showList(2);
		request.setAttribute("showlist", showList);
		request.getRequestDispatcher("").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
