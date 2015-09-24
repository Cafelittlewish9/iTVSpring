package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.ReportVideoDAO;
import model.vo.ReportVideoVO;
import util.HibernateUtil;

public class ReportVideoDAOjdbc implements ReportVideoDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
	// private DataSource ds;
	// public ReportVideoDAOjdbc(){
	// try {
	// Context ctx = new InitialContext();
	// this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }
	// private static final String SELECT_ALL = "SELECT orderId,
	// reportedVideoId, reportTime, reportReason, "
	// + "v.memberId, videoWebsite, videoClassName, videoTitle, videoUploadTime,
	// videoDescription, "
	// + "videoDescriptionModifyTime, memberAccount FROM ReportVideo JOIN Video
	// v ON reportedVideoId = "
	// + "videoId JOIN Member m ON v.memberId = m.memberId";

	@Override
	public List<ReportVideoVO> selectAll() {
		List<ReportVideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ReportVideoVO order by orderId asc");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String INSERT = "INSERT INTO ReportVideo(reportedVideoId, reportReason) VALUES(?, ?)";

	@Override
	public int insert(ReportVideoVO reportVideo) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(reportVideo);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String DELETE = "DELETE FROM ReportVideo WHERE orderId = ?";

	@Override
	public int delete(int orderId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from ReportVideoVO where orderId = ?").setParameter(0, orderId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		ReportVideoDAO dao = new ReportVideoDAOjdbc();
		// INSERT
		// ReportVideoVO temp1 = new ReportVideoVO();
		// temp1.setOrderId(11);
		// temp1.setReportedVideoId(12);
		// temp1.setReportTime(new java.util.Date());
		// temp1.setReportReason("測試新增");
		// boolean test1 = dao.insert(temp1);
		// System.out.println(test1);
		// // DELETE
		// boolean test3 = dao.delete(14);
		// System.out.println(test3);
		// // SELECT_ALL
		List<ReportVideoVO> list = dao.selectAll();
		for (ReportVideoVO dept : list) {
			System.out.println(dept.getOrderId() + ",");
			System.out.println(dept.getReportedVideoId() + ",");
			System.out.println(dept.getReportTime() + ",");
			System.out.println(dept.getReportReason());
			System.out.println(dept.getVideo().getMember().getMemberEmail());
		}
	}
}