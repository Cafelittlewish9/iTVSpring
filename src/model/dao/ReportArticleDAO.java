package model.dao;

import java.util.List;

import model.vo.ReportArticleVO;

public interface ReportArticleDAO {

	public List<ReportArticleVO> selectAll();

	public int insert(ReportArticleVO reportArticle);

	public int delete(int orderId);

}