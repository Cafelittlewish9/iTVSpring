package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.FollowService;
import model.service.VideoService;
import model.vo.FollowVO;
import model.vo.MemberVO;
import model.vo.VideoVO;

@WebServlet("/PersonalPage")
public class PersonalPage extends HttpServlet {
	private VideoService vService;
	private FollowService fService;

	@Override
	public void init() throws ServletException {
		this.vService = new VideoService();
		this.fService = new FollowService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		int memberId = member.getMemberId();
		System.out.println(memberId);
		Collection<VideoVO> vList = vService.searchMemberId(memberId);
		Collection<FollowVO> fList = fService.followList(memberId);
		request.setAttribute("vList", vList);
		request.setAttribute("fList", fList);
		request.getRequestDispatcher("PersonalPage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
