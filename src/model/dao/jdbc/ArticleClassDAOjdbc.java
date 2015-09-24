package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.ArticleClassDAO;
import model.vo.ArticleClassVO;
import model.vo.ReplyArticleVO;
import util.GC;
import util.HibernateUtil;

/**
 * @author iTV小組成員
 *
 */
public class ArticleClassDAOjdbc implements ArticleClassDAO {
//	private DataSource ds;
//
//	public ArticleClassDAOjdbc() {
//		try {
//			Context ctx = new InitialContext();
//			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private static final String SELECT_ALL = "SELECT subclassNo,subclassName,className FROM articleclass";

	/**
	 * 查詢資料庫內所有文章
	 * 
	 * @return List<ArticleClassVO>
	 */
	@Override
	public List<ArticleClassVO> selectAll() {
		List<ArticleClassVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ArticleClassVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 以文章子分類代碼查詢該分類下資料庫內的所有文章
	 * 
	 * @param subclassNo
	 *            文章子分類代碼
	 * @return List<ArticleClassVO>
	 */
//	private static final String SELECT_BY_SUBCLASSNO = "SELECT subclassNo,subclassName,className FROM articleclass WHERE subclassNo=?";

	@Override

	public List<ArticleClassVO> select(String subclassNo) {
		List<ArticleClassVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ArticleClassVO where subclassNo = ?").setParameter(0, subclassNo);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String SELECT_BY_SUBCLASSNAME = "SELECT subclassNo,subclassName,className FROM articleclass WHERE subclassName=?";

	@Override
	public ArticleClassVO selectBySubclassName(String subclassName) {
		ArticleClassVO bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ArticleClassVO where subclassName = ?").setParameter(0, subclassName);
			bean = (ArticleClassVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	private static final String INSERT = "INSERT INTO ArticleClass VALUES (?,?,?)";

	/**
	 * 新增文章分類、子分類代碼與子分類名稱
	 * 
	 * @param bean
	 *            必須包含<b>subclassNo</b>、<b>subclassName</b>與<b>className</b>
	 * @return true 新增成功；false 新增失敗
	 */
	@Override
	public int insert(ArticleClassVO bean) {
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

	private static final String UPDATE = "UPDATE ArticleClass SET SubclassName=?,ClassName=? WHERE subclassNo=?";

	/**
	 * 修改文章分類、子分類代碼或子分類名稱
	 * 
	 * @param bean
	 *            必須包含<b>subclassNo</b>
	 * @return true 修改成功；false 修改失敗
	 */
	@Override
	public int update(ArticleClassVO bean) {
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

	private static final String DELETE = "DELETE FROM ArticleClass WHERE subclassNo=?";

	/**
	 * 刪除文章分類、子分類代碼與子分類名稱
	 * 
	 * @param subclassNo
	 *            文章子分類代碼
	 * @return true 刪除成功；false 刪除失敗
	 */
	@Override
	public int delete(String subclassNo) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from ArticleClassVO where subclassNo = ?").setParameter(0, subclassNo);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args) throws SQLException {
		ArticleClassDAO temp = new ArticleClassDAOjdbc();
		List<ArticleClassVO> list = temp.select("C");
		for(ArticleClassVO bean : list ) {
			System.out.println(bean.getSubclassNo());
			System.out.println(bean.getClassName());
			System.out.println(bean.getSubclassName());
		}
//		ArticleClassVO acvo = new ArticleClassVO();
//		acvo.setSubclassNo("o");
//		acvo.setSubclassName("聯航");
//		acvo.setClassName("交通");
		// System.out.println(temp.selectAll());
		// System.out.println(temp.select("o"));
		// System.out.println(temp.insert(acvo));
//		System.out.println(temp.update(acvo));
		// System.out.println(temp.delete("o"));

	}

}
