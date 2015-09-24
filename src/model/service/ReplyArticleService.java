package model.service;

import java.util.Collection;

import model.dao.ReplyArticleDAO;
import model.dao.jdbc.ReplyArticleDAOjdbc;
import model.vo.ReplyArticleVO;

public class ReplyArticleService {
	private ReplyArticleDAO dao;

	public ReplyArticleService() {
		this.dao = new ReplyArticleDAOjdbc();
	}

	public Collection<ReplyArticleVO> listReplyArticle(int articleId) {
		return dao.selectByArticleId(articleId);
	}

	public boolean addReplyArticle(ReplyArticleVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public boolean modifyReplyArticle(ReplyArticleVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.update(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public boolean deleteReplyArticle(int replyArticleId) {
		boolean result = false;
		int temp = dao.delete(replyArticleId);
		if (temp == 1) {
			result = true;
		}
		return result;
	}
}
