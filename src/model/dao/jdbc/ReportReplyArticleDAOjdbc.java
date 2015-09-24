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

import model.dao.ReportReplyArticleDAO;
import model.vo.MemberVO;
import model.vo.ReplyArticleVO;
import model.vo.ReportReplyArticleVO;
import model.vo.ReportVideoVO;
import util.ConvertType;
import util.GC;
import util.HibernateUtil;

public class ReportReplyArticleDAOjdbc implements ReportReplyArticleDAO {
//	private static final String URL = GC.URL;
//	private static final String USERNAME = GC.USERNAME;
//	private static final String PASSWORD = GC.PASSWORD;
//	private DataSource ds;
//	public ReportReplyArticleDAOjdbc(){
//		try {
//			Context ctx = new InitialContext();
//			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	private static final String SELECT_ALL = "SELECT orderId, reportedReplyArticleId, reportTime, reportReason, "
//			+ "r.memberId, replyContent, modifyTime, memberAccount, memberPhoto FROM ReportReplyArticle "
//			+ "JOIN ReplyArticle r ON reportedReplyArticleId = replyArticleId JOIN Member m ON r.memberId = "
//			+ "m.memberId ORDER BY reportTime DESC";

	@Override
	public List<ReportReplyArticleVO> selectAll() {
		List<ReportReplyArticleVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ReportReplyArticleVO order by orderId asc");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String INSERT = "INSERT INTO ReportReplyArticle(reportedReplyArticleId, reportReason) VALUES(?, ?)";

	@Override
	public int insert(ReportReplyArticleVO reportReplyArticle) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(reportReplyArticle);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM ReportReplyArticle WHERE orderId = ?";

	@Override
	public int delete(int orderId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from ReportReplyArticleVO where orderId = ?").setParameter(0, orderId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		ReportReplyArticleDAO dao = new ReportReplyArticleDAOjdbc();
		// INSERT
//		ReportReplyArticleVO temp1 = new ReportReplyArticleVO();
//		temp1.setOrderId(11);
//		temp1.setReportedReplyArticleId(12);
//		temp1.setReportTime(new java.util.Date());
//		temp1.setReportReason("測試新增");
//		boolean test1 = dao.insert(temp1);
//		System.out.println(test1);
//		// DELETE
//		boolean test3 = dao.delete(50);
//		System.out.println(test3);
		// SELECT_ALL
		List<ReportReplyArticleVO> list = dao.selectAll();
		for (ReportReplyArticleVO dept : list) {
			System.out.println(dept.getOrderId() + ",");
			System.out.println(dept.getReplyArticle().getReplyArticleId() + ",");
			System.out.println(dept.getReportTime() + ",");
			System.out.println(dept.getReportReason());
			System.out.println(dept.getReplyArticle().getMember().getMemberEmail());
		}
	}
}