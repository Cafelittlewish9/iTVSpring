package model.dao;

import java.util.List;

import model.vo.ReplyArticleVO;

public interface ReplyArticleDAO {

	public List<ReplyArticleVO> selectAll();

	public List<ReplyArticleVO> selectByArticleId(int articleId);

	public int insert(ReplyArticleVO bean);

	public int update(ReplyArticleVO bean);

	public int delete(int replyArticleId);

}