package model.service;

import model.dao.ArticleClassDAO;
import model.dao.jdbc.ArticleClassDAOjdbc;
import model.vo.ArticleClassVO;

/**
 * @author iTV小組成員
 *
 */
public class ArticleClassService {
	private ArticleClassDAO dao;

	/**
	 * 初始化ArticleClassDAO
	 */
	public ArticleClassService() {
		this.dao = new ArticleClassDAOjdbc();
	}

	/**
	 * 增加文章類別
	 * 
	 * @param bean
	 *            必須包含 <b>subclassNo</b>、 <b>subclassName</b> 與 <b>className</b>
	 * @return true 增加成功; false 增加失敗
	 */
	public boolean addArticleClass(ArticleClassVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 修改文章類別
	 * 
	 * @param bean
	 *            必須包含 <b>SubclassNo</b>、<b>SubclassNo</b>、<b>SubclassNo</b>
	 * @return true 修改成功; false 修改失敗
	 */
	public boolean updateArticleClass(ArticleClassVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.update(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 刪除文章類別
	 * 
	 * @param subclassNo
	 *            文章子類別編號
	 * @return true 刪除成功; false 刪除失敗
	 */
	public boolean deleteArticleClass(String subclassNo) {
		boolean result = false;
		if (subclassNo != null && subclassNo.trim().length() != 0) {
			int temp = dao.delete(subclassNo);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}
}
