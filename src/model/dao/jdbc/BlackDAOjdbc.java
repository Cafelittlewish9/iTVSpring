package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.BlackDAO;
import model.vo.BlackVO;
import model.vo.MemberVO;
import model.vo.ReplyArticleVO;
import util.HibernateUtil;

/**
 * @author iTV小組成員
 *
 */
public class BlackDAOjdbc implements BlackDAO {
//	private DataSource ds;
//
//	public BlackDAOjdbc() {
//		try {
//			Context ctx = new InitialContext();
//			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

//	private static final String MARK_BLACK = "INSERT INTO black VALUES (?,?)";

	/**
	 * 設定黑名單
	 * 
	 * @param memberId
	 *            設定黑名單之會員編號
	 * @param blackedId
	 *            被設定為黑名單之會員編號
	 * @return true 設定成功；false 設定失敗
	 */
	@Override
	public int markBlack(BlackVO bean) {
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

	// SELECT更改過了
//	private static final String GET_LIST = "SELECT b.memberId, b.blackedId,m.memberAccount FROM black b JOIN member m"
//			+ " ON b.blackedid = m.memberid WHERE b.memberId=?";

	/**
	 * 查詢某會員編號所設定的全部黑名單
	 * 
	 * @param memberId
	 *            設定黑名單之會員編號
	 * @return List<BlackVO>
	 */
	@Override
	public List<BlackVO> getList(int memberId) {
		List<BlackVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from BlackVO where memberId = ?").setParameter(0, memberId);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	private static final String REMOVE_BLACK = "DELETE FROM black WHERE memberId=? AND blackedId=?";

	/**
	 * 解除某會員編號所設定的單筆黑名單
	 * 
	 * @param memberId
	 *            設定黑名單之會員編號
	 * @param blackedId
	 *            被設定為黑名單之會員編號
	 * @return List<BlackVO>
	 */
	@Override
	public int removeBlack(BlackVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from BlackVO where memberId = ? and blackedId = ?").setParameter(0, bean.getMemberId()).setParameter(1, bean.getBlackedId());
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * （突然覺得世間充滿大愛）解除某會員編號所設定的全部黑名單
	 * 
	 * @param memberId
	 *            設定黑名單之會員編號
	 * @return List<BlackVO>
	 */
//	private static final String REMOVE_ALL = "DELETE FROM black WHERE memberId=?";

	@Override
	public int removeAll(int memberId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from BlackVO where memberId = ?").setParameter(0, memberId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args){
		BlackDAO blackDao = new BlackDAOjdbc();
//		System.out.println(blackDao.removeAll(4));
		// System.out.println(blackDao.markBlack(2,4));
		// System.out.println(blackDao.markBlack(5,5));
//		for (BlackVO bean : blackDao.getList(4)) {
//			System.out.println(bean + bean.getMember().getMemberAccount());
//		}

		// System.out.println(blackDao.removeBlack(4, 2));
		// System.out.println(blackDao.removeAll(4));

	}

}
