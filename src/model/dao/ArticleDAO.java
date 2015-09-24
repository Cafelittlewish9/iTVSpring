package model.dao;

import java.util.List;

import model.vo.ArticleVO;

public interface ArticleDAO {

	public List<ArticleVO> selectAll();

	public List<ArticleVO> selectByInput(String subclassNo, String articleTitle);

	public int insert(ArticleVO article);

	public int update(ArticleVO article);

	public int delete(ArticleVO bean);

}