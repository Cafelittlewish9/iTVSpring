package model.dao;

import java.util.List;

import model.vo.ReportMemberVO;

public interface ReportMemberDAO {

	public List<ReportMemberVO> selectAll();

	public int insert(ReportMemberVO reportMember);

	public int delete(int orderId);

}