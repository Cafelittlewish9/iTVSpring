package model.service;

import java.util.Collection;

import model.dao.ArticleDAO;
import model.dao.ReportArticleDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.dao.jdbc.ReportArticleDAOjdbc;
import model.vo.ReportArticleVO;

public class ReportArticleService {
	private ReportArticleDAO dao;
	private ArticleDAO dao2;

	public ReportArticleService() {
		this.dao = new ReportArticleDAOjdbc();
		this.dao2 = new ArticleDAOjdbc();
	}

	public Collection<ReportArticleVO> reportArticleList() {
		return dao.selectAll();
	}

	public boolean addReportArticle(ReportArticleVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public boolean deleteArticle(ReportArticleVO bean) {
		int result1 = dao2.delete(bean.getArticle());
		int result2 = dao.delete(bean.getOrderId());
		if (result1 == 1 && result2 == 1) {
			return true;
		} else {
			return false;
		}
	}
}
