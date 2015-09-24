package model.dao;

import java.util.List;

import model.vo.ReportReplyArticleVO;

public interface ReportReplyArticleDAO {

	public List<ReportReplyArticleVO> selectAll();

	public int insert(ReportReplyArticleVO reportReplyArticle);

	public int delete(int orderId);

}