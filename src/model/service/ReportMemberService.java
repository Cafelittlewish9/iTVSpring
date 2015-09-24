package model.service;

import java.util.List;
import model.dao.MemberDAO;
import model.dao.ReportMemberDAO;
import model.dao.jdbc.MemberDAOjdbc;
import model.dao.jdbc.ReportMemberDAOjdbc;
import model.vo.ReportMemberVO;

public class ReportMemberService {
	private ReportMemberDAO dao;
	private MemberDAO dao2;

	public ReportMemberService() {
		this.dao = new ReportMemberDAOjdbc();
		this.dao2 = new MemberDAOjdbc();
	}

	public boolean addReportMember(ReportMemberVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public List<ReportMemberVO> selectAll() {
		return dao.selectAll();
	}

	//刪除成功為false
	public boolean deleteReportMember(ReportMemberVO bean) {
		int result1 = dao2.switchSuspend(bean.getMember().getMemberId(), true);
		int result2 = dao.delete(bean.getOrderId());
		if (result1 == 1 && result2 == 1) {
			return true;
		} else {
			return false;
		}
	}

}
