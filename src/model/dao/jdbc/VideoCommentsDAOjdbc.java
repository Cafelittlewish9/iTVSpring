package model.dao.jdbc;

import java.sql.Connection;
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

import model.dao.VideoCommentsDAO;
import model.vo.MemberVO;
import model.vo.VideoCommentsVO;
import util.GC;
import util.HibernateUtil;

public class VideoCommentsDAOjdbc implements VideoCommentsDAO {
//	 private static final String URL = GC.URL;
//	 private static final String USERNAME = GC.USERNAME;
//	 private static final String PASSWORD = GC.PASSWORD;
//	private DataSource ds;
//
//	public VideoCommentsDAOjdbc() {
//		try {
//			Context ctx = new InitialContext();
//			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

//	private static final String SELECT_ALL = "SELECT vc.commentId, vc.memberId, vc.videoId, vc.commentContent, vc.commentTime, m.memberAccount FROM videoComments vc Join member m ON vc.memberId = m.memberId";

	@Override
	public List<VideoCommentsVO> selectAll() {
		List<VideoCommentsVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoCommentsVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

//	private static final String SELECT_BY_VIDEOID = "SELECT vc.commentId, vc.memberId, vc.videoId, vc.commentContent, vc.commentTime, m.memberAccount, m.memberPhoto FROM videoComments vc Join member m ON vc.memberId = m.memberId where videoId = ?";

	@Override
	public List<VideoCommentsVO> selectByVideoId(int videoId) {
		List<VideoCommentsVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from VideoCommentsVO where videoId = ?").setParameter(0, videoId);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	private static final String INSERT = "INSERT INTO videoComments (memberId,videoId,commentContent) VALUES (?,?,?)";

	@Override
	public int insert(VideoCommentsVO bean) {
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

//	private static final String UPDATE = "UPDATE videoComments SET commentContent=?,commentTime=? WHERE commentId=?";

	@Override
	public int update(VideoCommentsVO bean) {
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

	private static final String DELETE = "DELETE FROM videoComments WHERE commentId=?";

	@Override
	public int delete(int commentId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from VideoCommentsVO where commentId = ?").setParameter(0, commentId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args) {
		VideoCommentsDAO vcdao = new VideoCommentsDAOjdbc();
		VideoCommentsVO vcvo = new VideoCommentsVO();
		vcvo.setCommentId(18);
		// vcvo.setMemberId(4);
		// vcvo.setVideoId(5);
		vcvo.setCommentContent("我超越了");
		vcvo.setCommentTime(new java.util.Date());
		// System.out.println(vcdao.insert(vcvo));
		System.out.println(vcdao.update(vcvo));
		// System.out.println(vcdao.selectAll());
		// System.out.println(vcdao.delete(17));

		// insert時，DB抓的是GMT時間，update時則是傳本地端時間進DB

	}

}
