package model.dao;

import java.util.List;

import model.vo.ArticleClassVO;

public interface ArticleClassDAO {

	public List<ArticleClassVO> selectAll();

	public List<ArticleClassVO> select(String subclassNo);
	
	public ArticleClassVO selectBySubclassName(String subclassName);

	public int insert(ArticleClassVO bean);

	public int update(ArticleClassVO bean);

	public int delete(String subclassNo);

}