package model.dao.jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
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

import model.dao.ReportArticleDAO;
import model.vo.ArticleClassVO;
import model.vo.ArticleVO;
import model.vo.MemberVO;
import model.vo.ReportArticleVO;
import model.vo.ReportVideoVO;
import util.ConvertType;
import util.GC;
import util.HibernateUtil;

public class ReportArticleDAOjdbc implements ReportArticleDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
	// private DataSource ds;
	//
	// public ReportArticleDAOjdbc() {
	// try {
	// Context ctx = new InitialContext();
	// this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private static final String SELECT_ALL = "SELECT orderId,
	// reportedArticleId, reportTime, reportReason, articleId,"
	// + "a.memberId, memberAccount, memberPhoto, articleTitle, articleContent,
	// modifyTime,a.subclassNo, className, subclassName "
	// + "FROM ReportArticle r JOIN Article a ON r.reportedArticleId =
	// a.articleId JOIN Member m "
	// + "ON a.memberId = m.memberId JOIN ArticleClass ac ON a.subclassNo =
	// ac.subclassNo ORDER BY reportTime DESC";

	@Override
	public List<ReportArticleVO> selectAll() {
		List<ReportArticleVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ReportArticleVO order by orderId asc");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String INSERT = " INSERT INTO
	// ReportArticle(reportedArticleId, reportReason) VALUES(?, ?) ";

	@Override
	public int insert(ReportArticleVO reportArticle) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(reportArticle);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String DELETE = " DELETE FROM ReportArticle WHERE
	// orderId = ?";

	@Override
	public int delete(int orderId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from ReportArticleVO where orderId = ?").setParameter(0, orderId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		ReportArticleDAO dao = new ReportArticleDAOjdbc();
		// INSERT
		// ReportArticleVO temp1 = new ReportArticleVO();
		// temp1.setOrderId(11);
		// temp1.setReportedArticleId(12);
		// temp1.setReportTime(new java.util.Date());
		// temp1.setReportReason("測試新增");
		// boolean test1 = dao.insert(temp1);
		// System.out.println(test1);
		// DELETE
		// boolean test3 = dao.delete(13);
		// System.out.println(test3);
		// SELECT_ALL
		List<ReportArticleVO> list = dao.selectAll();
		for (ReportArticleVO dept : list) {
			System.out.println(dept.getOrderId() + ",");
			System.out.println(dept.getArticle().getArticleId() + ",");
			System.out.println(dept.getReportTime() + ",");
			System.out.println(dept.getReportReason());
		}
	}
}