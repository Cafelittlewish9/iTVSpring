package model.dao.jdbc;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.LoginDAO;
import model.vo.LoginVO;
import util.HibernateUtil;

public class LoginDAOjdbc implements LoginDAO {
//	private static final String URL = GC.URL;
//	private static final String USERNAME = GC.USERNAME;
//	private static final String PASSWORD = GC.PASSWORD;
//	private DataSource ds;
//
//	public LoginDAOjdbc(){
//		try {
//			Context ctx = new InitialContext();
//			this.ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
//	private static final String SELECT_BY_MEMBERACCOUNT = "select * from Login where memberAccount = ?";

	@Override
	public List<LoginVO> selectAll(String memberAccount) {
		List<LoginVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from LoginVO where memberAccount = ?").setParameter(0, memberAccount);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String SELECT_ALL = "select * from Login";

	@Override
	public List<LoginVO> selectAll() {
		List<LoginVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from LoginVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String SELECT_LAST_TIME = "SELECT TOP 1 * FROM Login WHERE memberAccount = ? ORDER BY logintime DESC";

	@Override
	public LoginVO select(String memberAccount) {
		LoginVO bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from LoginVO where memberAccount = ? ORDER BY logintime DESC")
					.setParameter(0, memberAccount);
			bean = (LoginVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	private static final String INSERT = "insert into Login(ip, memberAccount) values(?, ?)";

	@Override
	public int insert(LoginVO bean) {
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

	public static void main(String[] args) {
		LoginDAO temp = new LoginDAOjdbc();
		System.out.println(temp.select("Pikachu"));

		// LoginVO bean = new LoginVO();
		// bean.setIp("192.168.26.39");
		// bean.setLoginTime(new java.util.Date());
		// bean.setMemberAccount("Pikachu");
		// System.out.println(temp.insert(bean));
	}
}