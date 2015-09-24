package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.ReplyArticleDAO;
import model.vo.ReplyArticleVO;
import util.HibernateUtil;

public class ReplyArticleDAOjdbc implements ReplyArticleDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
	// private DataSource ds;
	//
	// public ReplyArticleDAOjdbc() {
	// try {
	// Context ctx = new InitialContext();
	// this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private static final String SELECT_ALL = "SELECT r.* , m.memberAccount ,
	// memberPhoto FROM ReplyArticle r Join member m on r.memberId =
	// m.memberId";

	@Override
	public List<ReplyArticleVO> selectAll() {
		List<ReplyArticleVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ReplyArticleVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_ARTICLEID = "SELECT replyArticleId,
	// r.memberId, articleId, replyContent, publishTime, modifyTime,
	// memberAccount, memberPhoto FROM ReplyArticle r JOIN Member m ON
	// r.memberId = m.memberId WHERE articleId = ?";

	@Override
	public List<ReplyArticleVO> selectByArticleId(int articleId) {
		List<ReplyArticleVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ReplyArticleVO where articleId = ?").setParameter(0, articleId);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String INSERT = "INSERT INTO ReplyArticle(memberId,
	// articleId, replyContent) VALUES (?, ?, ?)";

	@Override
	public int insert(ReplyArticleVO bean) {
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

	// private static final String UPDATE = "UPDATE ReplyArticle SET
	// replyContent = ?, modifyTime = GETUTCDATE() WHERE replyArticleId = ?";

	@Override
	public int update(ReplyArticleVO bean) {
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

	// private static final String DELETE = "UPDATE ReplyArticle SET
	// replyContent = N'文章已被刪除', modifyTime = GETUTCDATE() WHERE replyArticleId
	// = ?";

	@Override
	public int delete(int replyArticleId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ReplyArticleVO bean = (ReplyArticleVO) session.get(ReplyArticleVO.class, replyArticleId);
			bean.setReplyContent("文章已被刪除");
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		ReplyArticleDAOjdbc dao = new ReplyArticleDAOjdbc();
		List<ReplyArticleVO> list = dao.selectByArticleId(4);
		for (ReplyArticleVO bean : list) {
			System.out.println(bean.getReplyArticleId());
			System.out.println(bean.getArticle().getArticleContent());
			System.out.println(bean.getMember().getMemberGoogle());
		}
	}
}
