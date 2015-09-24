package model.dao.jdbc;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.VideoDAO;
import model.vo.VideoVO;
import util.HibernateUtil;

public class VideoDAOjdbc implements VideoDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
	// private DataSource datasource;
	//
	// public VideoDAOjdbc() {
	// try {
	// InitialContext context = new InitialContext();
	// this.datasource = (DataSource) context.lookup("java:comp/env/jdbc/DB");
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	// private static final String SELECT_BY_VIDEOTITLE = "SELECT
	// v.*,m.memberAccount FROM video v Join member m ON v.memberId = m.memberId
	// WHERE videoTitle LIKE ?";

	@Override
	public List<VideoVO> selectByVideoTitle(String videoTitle) {
		List<VideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoVO where videoTitle like ?").setParameter(0, "%" + videoTitle + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_VIDEONAME = "SELECT
	// v.*,m.memberAccount FROM video v Join member m ON v.memberId = m.memberId
	// WHERE videoName = ?";

	@Override
	public List<VideoVO> selectByVideoName(String videoName) {
		List<VideoVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoVO where videoName = ?").setParameter(0, videoName);
			result = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String SELECT_BY_VIDEOCLASSNAME = "SELECT
	// v.*,m.memberAccount FROM video v Join member m ON v.memberId = m.memberId
	// WHERE videoClassName = ?";

	@Override
	public List<VideoVO> selectByVideoClassName(String videoClassName) {
		List<VideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoVO where videoClassName = ?").setParameter(0, videoClassName);
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_MEMBERID = "SELECT
	// v.*,m.memberAccount FROM video v Join member m ON v.memberId = m.memberId
	// WHERE m.memberId=?";

	@Override
	public List<VideoVO> selectByMemberId(int memberId) {
		List<VideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoVO where memberId = ?").setParameter(0, memberId);
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_ALL = "SELECT v.*,m.memberAccount FROM
	// video v Join member m ON v.memberId = m.memberId ORDER BY videoWatchTimes
	// DESC";

	@Override
	public List<VideoVO> selectAll() {
		List<VideoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from VideoVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String INSERT = "insert into video(memberId,
	// videoWebsite, videoClassName, videoTitle, videoName, videoPath,
	// videoWatchTimes, videoDescription) values (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public int insert(VideoVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String UPDATE_DESCRIPTION = "update video set
	// videoTitle = ?,videoDescription = ?, videoDescriptionModifyTime =
	// GETUTCDATE() where videoId = ?";

	@Override
	public int update(VideoVO bean) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
			result = 1;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String DELETE = "delete from video where videoId =
	// ?";

	@Override
	public int delete(int videoId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from VideoVO where videoId = ?").setParameter(0, videoId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		// SelectALL
		VideoDAO temp = new VideoDAOjdbc();
		List<VideoVO> list = temp.selectAll();
		System.out.println(list);

		// Insert
		// String url =
		// "http://nextinnovation.cloudapp.net/ITV/PlayVideo.jsp?filename=";
		// String path = "../mp4/";
		// String videoname = "Mamamoo - Um Oh Ah Yeh";
		// VideoVO tempinsert = new VideoVO();
		// tempinsert.setMemberId(2);
		// tempinsert.setVideoWebsite(url+videoname);
		// tempinsert.setVideoClassName("mv");
		// tempinsert.setVideoName(videoname);
		// tempinsert.setVideoPath(path+videoname+".mp4");
		// tempinsert.setVideoWatchTimes(1000);
		//
		// VideoDAO dao = new VideoDAOjdbc();
		// VideoVO insertlist = dao.insert(tempinsert);
		// System.out.println("Insert : "+ insertlist);

		// Update
		// String url =
		// "http://nextinnovation.cloudapp.net/ITV/PlayVideo.jsp?filename=";
		// String path = "../mp4/";
		// String videoname = "Mamamoo";
		//
		// VideoVO tempupdate = new VideoVO();
		// tempupdate.setMemberId(3);
		// tempupdate.setVideoWebsite(url+videoname);
		// tempupdate.setVideoClassName("mv");
		// tempupdate.setVideoName(videoname);
		// tempupdate.setVideoPath(path+videoname+".mp4");
		// tempupdate.setVideoUploadTime(new
		// java.sql.Date(System.currentTimeMillis()));
		// tempupdate.setVideoWatchTimes(2000);
		// tempupdate.setVideoDescription("");
		// tempupdate.setVideoId(14);
		//
		// VideoDAO dao = new VideoDAOjdbc();
		// VideoVO updatelist =
		// dao.update(tempupdate.getMemberId(),tempupdate.getVideoWebsite(),tempupdate.getVideoClassName(),
		// tempupdate.getVideoName(),tempupdate.getVideoPath(),tempupdate.getVideoUploadTime(),tempupdate.getVideoWatchTimes(),tempupdate.getVideoDescription(),tempupdate.getVideoId());
		// System.out.println("Update : "+ updatelist);

		// Delete
		// VideoDAO dao = new VideoDAOjdbc();
		// boolean d = dao.delete(13);
		// if(d==true){
		// System.out.println("Delete : Success!!!");
		// }else{
		// System.out.println("Delete : Fail!!!");
		// }
	}
}
