package model.dao.jdbc;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.ReportMemberDAO;
import model.vo.ReportMemberVO;
import util.HibernateUtil;

public class ReportMemberDAOjdbc implements ReportMemberDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
	// private DataSource ds;
	// public ReportMemberDAOjdbc(){
	// try {
	// Context ctx = new InitialContext();
	// this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private static final String SELECT_ALL = "SELECT orderId,
	// reportedMemberId, reportTime, reportReason, "
	// + "memberAccount, memberName, memberNickname, memberPhoto,
	// broadcastTitle, broadcastClassName, "
	// + "broadcastDescription FROM ReportMember JOIN Member ON reportedMemberId
	// = memberId "
	// + "ORDER BY reportTime DESC";

	@Override
	public List<ReportMemberVO> selectAll() {
		List<ReportMemberVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ReportMemberVO order by orderId asc");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String INSERT = "insert into ReportMember(reportedMemberId, reportReason) values(?, ? )";

	@Override
	public int insert(ReportMemberVO reportMember) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(reportMember);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String DELETE = "delete from ReportMember where orderId = ?";

	@Override
	public int delete(int orderId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from ReportMemberVO where orderId = ?").setParameter(0, orderId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		ReportMemberDAO temp = new ReportMemberDAOjdbc();
		List<ReportMemberVO> list = temp.selectAll();
		for (ReportMemberVO bean : list) {
			System.out.println(bean.getOrderId());
			System.out.println(bean.getMember().getMemberAccount());
			System.out.println(bean.getMember().getMemberPassword());
		}
	}
}
