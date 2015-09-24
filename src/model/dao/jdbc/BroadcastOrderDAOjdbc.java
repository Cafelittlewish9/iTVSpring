package model.dao.jdbc;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.BroadcastOrderDAO;
import model.vo.BroadcastOrderVO;
import util.HibernateUtil;

public class BroadcastOrderDAOjdbc implements BroadcastOrderDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;

	// private DataSource datasource;
	//
	// public BroadcastOrderDAOjdbc() {
	// try {
	// InitialContext context = new InitialContext();
	// this.datasource = (DataSource) context.lookup("java:comp/env/jdbc/DB");
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private static final String SELECT_ALL = "SELECT b.*,m.broadcastWebsite
	// FROM BroadcastOrder b Join member m ON b.memberAccount = m.memberAccount
	// ORDER BY broadcastTime DESC";

	@Override
	public List<BroadcastOrderVO> selectAll() {
		List<BroadcastOrderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from BroadcastOrderVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_MEMBERACCOUNT_OR_BROADCASTTITLE =
	// "SELECT * FROM BroadcastOrder WHERE memberAccount like ? OR
	// broadcastTitle like ?";

	@Override
	public List<BroadcastOrderVO> selectByMemberAccountOrBroadcastTitle(int memberId, String broadcastTitle) {
		List<BroadcastOrderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from BroadcastOrderVO WHERE memberAccount = ? OR broadcastTitle like ?")
					.setParameter(0, memberId).setParameter(1, "%" + broadcastTitle + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_MEMBERACCOUNT = "SELECT * FROM
	// BroadcastOrder WHERE memberAccount = ?";

	@Override
	public BroadcastOrderVO selectByMemberId(int memberId) {
		BroadcastOrderVO bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from BroadcastOrderVO WHERE memberId = ?").setParameter(0, memberId);
			bean = (BroadcastOrderVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	// private static final Strings INSERT = "INSERT INTO
	// BroadcastOrder(memberAccount, broadcastSite, broadcastTitle,
	// broadcastTime) VALUES(?, ?, ?, ?)";

	@Override
	public int insert(BroadcastOrderVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String UPDATE = "UPDATE BroadcastOrder SET
	// broadcastTitle = ? WHERE memberAccount = ?";

	@Override
	public int update(BroadcastOrderVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String DELETE = "DELETE FROM BroadcastOrder WHERE
	// memberAccount = ?";

	@Override
	public int delete(int memberId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from BroadcastOrderVO where memberId = ?").setParameter(0,
					memberId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		// SelectAll
		BroadcastOrderDAO temp = new BroadcastOrderDAOjdbc();
		List<BroadcastOrderVO> list = temp.selectAll();
		for (BroadcastOrderVO bean : list) {
			System.out.println(list);
			System.out.println(bean.getMember().getMemberAccount());
		}

		// Insert
		// String memberAccount = "FUN";
		// String broadcastWebsite =
		// "http://itvvm.cloudapp.net/ITV/LiveShow?memberAccount=FUN";
		// String broadcastTitle = "TGIF";
		// BroadcastOrderVO tempinsert = new BroadcastOrderVO();
		// tempinsert.setMemberAccount(memberAccount);
		// tempinsert.setBroadcastWebsite(broadcastWebsite);
		// tempinsert.setBroadcastTitle(broadcastTitle);
		// tempinsert.setBroadcastTime(new
		// java.sql.Date(System.currentTimeMillis()));
		//
		// BroadcastOrderDAO dao = new BroadcastOrderDAOjdbc();
		// int insertlist =
		// dao.insert(tempinsert.getMemberAccount(),tempinsert.getBroadcastWebsite(),tempinsert.getBroadcastTitle(),tempinsert.getBroadcastTime());
		// System.out.println("Insert : "+ insertlist);

		// Update
		// String memberAccount = "FUN";
		// String broadcastTitle = "TGIF IS F";
		//
		// BroadcastOrderVO tempupdate = new BroadcastOrderVO();
		// tempupdate.setMemberAccount(memberAccount);
		// tempupdate.setBroadcastTitle(broadcastTitle);
		//
		// BroadcastOrderDAO dao = new BroadcastOrderDAOjdbc();
		// int updatelist =
		// dao.update(tempupdate.getBroadcastTitle(),tempupdate.getMemberAccount());
		// System.out.println("Update : "+ updatelist);

		// Delete
		// BroadcastOrderDAO dao = new BroadcastOrderDAOjdbc();
		// boolean d = dao.delete("FUN");
		// if(d==true){
		// System.out.println("Delete : Success!!!");
		// }else{
		// System.out.println("Delete : Fail!!!");
		// }
	}
}