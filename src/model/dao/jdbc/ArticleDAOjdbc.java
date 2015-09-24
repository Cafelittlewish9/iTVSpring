package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.ArticleDAO;
import model.vo.ArticleVO;
import model.vo.MemberVO;
import model.vo.ReplyArticleVO;
import util.ConvertType;
import util.HibernateUtil;

/**
 * @author iTV小組成員
 *
 */
public class ArticleDAOjdbc implements ArticleDAO {
	// private DataSource ds;
	//
	// public ArticleDAOjdbc() {
	// try {
	// Context ctx = new InitialContext();
	// this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	// private static final String SELECT_ALL = "SELECT
	// articleId,memberId,subclassNo,articleTitle,articleContent,publishTime,modifyTime,watchTimes
	// FROM article ORDER BY modifytime";

	/**
	 * 查詢所有文章
	 * 
	 * @return Collection<ArticleVO>
	 */
	@Override
	public List<ArticleVO> selectAll() {
		List<ArticleVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ArticleVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_INPUT = "SELECT
	// a.articleId,a.memberId,a.subclassNo,a.articleTitle,a.articleContent,a.publishTime,a.modifyTime,a.watchTimes,m.memberAccount,m.memberNickname"
	// + " FROM article a JOIN member m ON a.memberId=m.memberId WHERE
	// a.subclassNo =? OR a.articleTitle like ? OR m.memberAccount like ? OR
	// m.memberNickName like ? ";

	/**
	 * 依照各種條件來查詢文章
	 * 
	 * @param memberId
	 *            會員編號
	 * @return List<ArticleVO>
	 */
	@Override
	public List<ArticleVO> selectByInput(String subclassNo, String articleTitle) {
		List<ArticleVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ArticleVO where subclassNo = ? and articleTitle like ?");
			query.setParameter(0, subclassNo).setParameter(1, "%" + articleTitle + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;

	}

	// private static final String INSERT = "INSERT INTO Article (memberId,
	// subclassNo,articleTitle,articleContent) VALUES (?,?,?,?)";

	/**
	 * 新增文章
	 * 
	 * @param bean
	 *            必須包含<b>memberId</b>、<b>subclassNo</b>、<b>articleTitle</b> 與
	 *            <b>articleContent</b>
	 * @return true 新增成功；false 新增失敗
	 */
	@Override
	public int insert(ArticleVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String UPDATE = "UPDATE Article SET
	// subclassNo=?,articleTitle=?,articleContent=?,modifyTime = GETUTCDATE()
	// WHERE articleId=? AND memberId=?";

	/**
	 * 修改文章
	 * 
	 * @param bean
	 *            必須包含<b>articleId</b> 與 <b>memberId</b>
	 * @return true 新增成功；false 新增失敗
	 */
	@Override
	public int update(ArticleVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String DELETE = "UPDATE Article SET articleContent = N'文章已被刪除', modifyTime = GETUTCDATE() WHERE articleId = ?";

	/**
	 * 刪除文章，僅有發文者本人能於登入狀態看到刪除按鈕，當文章確定刪除後，刪除按鈕即消失
	 * 
	 * @param articleId
	 *            文章編號
	 * @return true 刪除成功；false 刪除失敗
	 */
	@Override
	public int delete(ArticleVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean.setArticleContent("文章已被刪除");
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args) throws SQLException, ParseException {
		ArticleDAO temp = new ArticleDAOjdbc();
		// ArticleVO avo = new ArticleVO();
		// avo.setSubclassNo("J");
		// avo.setArticleId(13);
		// avo.setMemberId(4);
		// avo.setArticleTitle("freaking");
		// // System.out.println(temp.selectByInput("E","","",""));
		// avo.setArticleContent("Yes, I am normal");
		// temp.update(avo);
		// System.out.println(temp.selectAll());
		// System.out.println(temp.delete(13, 2));
		List<ArticleVO> list = temp.selectByInput("C", "皮卡丘");
		for (ArticleVO bean : list) {
		}
		// avo.setMemberId(1);
		// avo.setArticleId(14);
		// avo.setSubclassNo("A");
		// avo.setArticleTitle("Dear");
		// avo.setArticleContent("I hate the world");
		// System.out.println(temp.insert(avo));
		// System.out.println(temp.update(avo));
		// getUTCdate

	}

}
