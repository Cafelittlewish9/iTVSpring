package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.ShowDAO;
import model.vo.ShowVO;
import model.vo.VideoVO;
import util.ConvertType;
import util.HibernateUtil;

public class ShowDAOjdbc implements ShowDAO {
	// private DataSource datasource;
	//
	// public ShowDAOjdbc() {
	// try {
	// InitialContext context = new InitialContext();
	// this.datasource = (DataSource) context.lookup("java:comp/env/jdbc/DB");
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	// private static final String SELECT_BY_ID_JOIN_MEMBER = "SELECT s.*,
	// broadcastTitle FROM show s Join member m ON website = broadcastWebsite
	// WHERE s.memberId = ? ORDER BY showTime";

	@Override
	public List<ShowVO> selectJoinMember(int memberId) {
		List<ShowVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ShowVO where memberId = ? order by showTime asc").setParameter(0,
					memberId);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_ID_JOIN_VIDEO = "SELECT s.*,
	// videoTitle FROM show s Join Video v ON website = videoWebsite WHERE
	// s.memberId = ? ORDER BY showTime ASC";

	@Override
	public List<ShowVO> selectJoinVideo(int memberId) {
		List<ShowVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ShowVO where memberId = ? order by showTime asc").setParameter(0,
					memberId);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_ID_AND_WEBSITE = "SELECT * FROM
	// show WHERE memberId = ? and website = ? ORDER BY showTime ASC";

	@Override
	public ShowVO selectByIdAndWebsite(int memberId, int videoId) {
		ShowVO bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ShowVO where memberId = ? and videoId = ? order by showTime asc")
					.setParameter(0, memberId);
			bean = (ShowVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	// private static final String SELECT_ALL = "SELECT s.*,m.memberAccount FROM
	// show s Join member m ON s.memberId = m.memberId";

	@Override
	public List<ShowVO> selectAll() {
		List<ShowVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ShowVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String INSERT = "insert into show(memberId, website)
	// values (?, ?)";

	@Override
	public int insert(ShowVO bean) {
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

	// ======沒註解，因為要implement進來才行。======
	// private static final String UPDATE = "update show set showTime = ?,
	// website = ? where memberId = ? and showTime = ?";

	@Override
	public int update(ShowVO bean) {
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
	// ======沒註解，因為要implement進來才行。======

	// private static final String DELETE = "delete from show where memberId = ?
	// and website = ?";

	@Override
	public int delete(int memberId, int videoId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from ShowVO where memberId = ? and videoId = ?")
					.setParameter(0, memberId).setParameter(1, videoId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {

		// ShowDAOjdbc dao = new ShowDAOjdbc();
		// List<ShowVO> list = dao.selectJoinMember(2);
		// for (ShowVO bean : list) {
		// System.out.println(bean);
		// System.out.println(bean.getTitle());
		// }

	}

}
