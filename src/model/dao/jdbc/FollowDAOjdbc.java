package model.dao.jdbc;

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

import model.dao.FollowDAO;
import model.vo.BlackVO;
import model.vo.FollowVO;
import model.vo.MemberVO;
import util.GC;
import util.HibernateUtil;

public class FollowDAOjdbc implements FollowDAO {
//	private static final String URL = GC.URL;
//	private static final String USERNAME = GC.USERNAME;
//	private static final String PASSWORD = GC.PASSWORD;
//	private DataSource ds;
//	public FollowDAOjdbc(){
//		try {
//			Context ctx = new InitialContext();
//			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String SELECT_BY_MEMBERID = "SELECT f.memberId, followId, memberAccount "
//			+ "FROM Follow f join Member m ON followId = m.memberId WHERE f.memberId = ?";

	@Override
	public List<FollowVO> selectByMemberId(int memberId) {
		List<FollowVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from FollowVO where memberId = ?").setParameter(0, memberId);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String SELECT_ALL = "SELECT f.*,m.memberAccount FROM follow f Join member m ON f.followId = m.memberId";
	 
	@Override
	public List<FollowVO> selectAll() {
		List<FollowVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from FollowVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String INSERT = "insert into follow(memberId, followId) values(?, ?)";

	@Override
	public int insert(FollowVO bean) {
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

//	private static final String DELETE = "delete from Follow where followId=? and memberId=?";

	@Override
	public int delete(int followId, int memberId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from FollowVO where memberId = ? and followId = ?").setParameter(0, memberId).setParameter(1, followId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		FollowDAO follow = new FollowDAOjdbc();
		for (FollowVO temp : follow.selectByMemberId(2)) {
			System.out.println(temp);
			System.out.println(temp.getMember().getMemberAccount());
			System.out.println(temp.getMember().getMemberId());
		}
	}
}
