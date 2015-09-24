package model.dao;

import java.util.List;

import model.vo.ReportVideoVO;

public interface ReportVideoDAO {

	public List<ReportVideoVO> selectAll();

	public int insert(ReportVideoVO reportVideo);

	public int delete(int orderId);

}