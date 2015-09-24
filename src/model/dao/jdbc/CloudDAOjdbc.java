package model.dao.jdbc;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.CloudDAO;
import model.vo.CloudVO;
import util.HibernateUtil;

/**
 * @author iTV小組成員
 *
 */
public class CloudDAOjdbc implements CloudDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
	// private DataSource ds;
	// public CloudDAOjdbc(){
	// try {
	// Context ctx = new InitialContext();
	// this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	// private static final String SELECT_ALL = "SELECT * FROM Cloud";
	/**
	 * 查詢所有會員雲端硬碟裡的所有檔案
	 * 
	 * @return List<CloudVO>
	 */
	@Override
	public List<CloudVO> selectAll() {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from CloudVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_MEMBERID = "SELECT * FROM Cloud
	// WHERE memberId = ?";
	/**
	 * 查詢某會員雲端硬碟內所有檔案
	 * 
	 * @param memberId
	 *            文章類別名稱
	 * @return true 增加成功; false 增加失敗
	 */
	@Override
	public List<CloudVO> selectByMemberId(int memberId) {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from CloudVO where memberId = ?").setParameter(0, memberId);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_FILENAME = "SELECT * FROM Cloud
	// WHERE memberId = ? And fileName like ?";

	@Override
	public List<CloudVO> selectByFileName(int memberId, String fileName) {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from CloudVO where memberId = ? and fileName like ?")
					.setParameter(0, memberId).setParameter(1, "%" + fileName + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// 搜尋輸入時間部分尚未解決時間轉換問題，請記得。
	// private static final String SELECT_BY_TIME = "SELECT * FROM Cloud WHERE
	// memberId = ? AND (modifyTime BETWEEN ? AND ? )";

	@Override
	public List<CloudVO> selectByTime(int memberId, java.util.Date fromTime, java.util.Date toTime) {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from CloudVO where memberId = ? and modifyTime between ? and ?")
					.setParameter(0, memberId).setParameter(1, fromTime).setParameter(2, toTime);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_FILETYPE = "SELECT * FROM Cloud
	// WHERE memberId = ? AND fileType = ?";

	@Override
	public List<CloudVO> selectByFileType(int memberId, String fileType) {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from CloudVO where memberId = ? and fileType = ?")
					.setParameter(0, memberId).setParameter(1, fileType);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_FILENAME_AND_TIME = "SELECT * FROM
	// Cloud WHERE memberId = ? AND fileName like ? AND (modifyTime BETWEEN ?
	// AND ? )";

	@Override
	public List<CloudVO> selectByFileNameAndTime(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime) {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from CloudVO where memberId = ? and modifyTime between ? and ? and fileName like ?")
					.setParameter(0, memberId).setParameter(1, fromTime).setParameter(2, toTime)
					.setParameter(3, "%" + fileName + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_FILENAME_AND_FILETYPE = "SELECT *
	// FROM Cloud WHERE memberId = ? AND fileType = ? AND fileName like ?";

	@Override
	public List<CloudVO> selectByFileNameAndFileType(int memberId, String fileType, String fileName) {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from CloudVO where memberId = ? and fileType =  ? and fileName like ?")
					.setParameter(0, memberId).setParameter(1, fileType).setParameter(2, "%" + fileName + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_FILETYPE_AND_TIME = "SELECT * FROM
	// Cloud WHERE memberId = ? AND fileType = ? AND (modifyTime BETWEEN ? AND ?
	// )";

	@Override
	public List<CloudVO> selectByFileTypeAndTime(int memberId, java.util.Date fromTime, java.util.Date toTime,
			String fileType) {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from CloudVO where memberId = ? and modifyTime between ? and ? and fileType = ?")
					.setParameter(0, memberId).setParameter(1, fromTime).setParameter(2, toTime)
					.setParameter(3, fileType);
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String SELECT_BY_FILENAME_FILETYPE_AND_TIME =
	// "SELECT * FROM Cloud WHERE memberId = ? AND fileType = ? AND fileName
	// like ? AND (modifyTime BETWEEN ? AND ? )";

	@Override
	public List<CloudVO> selectByFileNameFileTypeAndTime(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime, String fileType) {
		List<CloudVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery(
							"from CloudVO where memberId = ? and modifyTime between ? and ? and fileType = ? and fileName like ?")
					.setParameter(0, memberId).setParameter(1, fromTime).setParameter(2, toTime)
					.setParameter(3, fileType).setParameter(4, "%" + fileName + "%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}

	// private static final String INSERT = "INSERT INTO Cloud(memberId,
	// fileName, fileType, filePath, fileSize) VALUES (?, ?, ?, ?, ?)";

	@Override
	public int insert(CloudVO file) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(file);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String UPDATE_FILE = "UPDATE Cloud SET filePath = ?, fileSize = ?, modifyTime = GETUTCDATE() WHERE fileId = ?";

	@Override
	public int updateFile(CloudVO file) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(file);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String UPDATE_FILENAME = "UPDATE Cloud SET fileName = ?, filePath = ? WHERE fileId = ?";

	@Override
	public int updateFileName(int fileId, String fileName, String filePath) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			CloudVO file = (CloudVO) session.get(CloudVO.class, fileId);
			file.setFileName(fileName);
			file.setFilePath(filePath);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

//	private static final String DELETE = "DELETE FROM Cloud WHERE fileId = ?";

	@Override
	public int delete(int fileId) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from CloudVO where fileId = ?").setParameter(0, fileId);
			result = query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		CloudDAOjdbc dao = new CloudDAOjdbc();
//		List<CloudVO> list = dao.selectAll();
//		for(CloudVO bean : list){
//			System.out.println(bean.getFileName());
//			System.out.println(bean.getFilePath());
//			System.out.println(bean.getFileType());
//		}
//		List<CloudVO> list2 = dao.selectByFileName(1, "檔");
//		for(CloudVO bean : list2){
//			System.out.println(bean.getFileName());
//			System.out.println(bean.getFilePath());
//			System.out.println(bean.getFileType());
//		}
//		List<CloudVO> list3 = dao.selectByFileNameAndFileType(2, "pdf", "檔");
//		for(CloudVO bean : list3){
//			System.out.println(bean.getFileName());
//			System.out.println(bean.getFilePath());
//			System.out.println(bean.getFileType());
//		}
//		List<CloudVO> list4 = dao.selectByFileNameAndTime(3, "檔", new java.util.Date(1200), new java.util.Date());
//		for(CloudVO bean : list4){
//			System.out.println(bean.getFileName());
//			System.out.println(bean.getFilePath());
//			System.out.println(bean.getFileType());
//		}
//		List<CloudVO> list5 = dao.selectByFileNameFileTypeAndTime(4, "檔", new java.util.Date(1200), new java.util.Date(), "java");
//		for(CloudVO bean : list5){
//			System.out.println(bean.getFileName());
//			System.out.println(bean.getFilePath());
//			System.out.println(bean.getFileType());
//		}
//		List<CloudVO> list6 = dao.selectByFileType(1, "mp4");
//		for(CloudVO bean : list6){
//			System.out.println(bean.getFileName());
//			System.out.println(bean.getFilePath());
//			System.out.println(bean.getFileType());
//		}
//		List<CloudVO> list7 = dao.selectByFileTypeAndTime(2, new java.util.Date(1200), new java.util.Date(), "docx");
//		for(CloudVO bean : list7){
//			System.out.println(bean.getFileName());
//			System.out.println(bean.getFilePath());
//			System.out.println(bean.getFileType());
//		}
//		List<CloudVO> list8 = dao.selectByTime(3, new java.util.Date(1200), new java.util.Date());
//		for(CloudVO bean : list8){
//			System.out.println(bean.getFileName());
//			System.out.println(bean.getFilePath());
//			System.out.println(bean.getFileType());
//		}
		List<CloudVO> list9 = dao.selectByMemberId(4);
		for(CloudVO bean : list9){
			System.out.println(bean.getFileName());
			System.out.println(bean.getFilePath());
			System.out.println(bean.getFileType());
		}
	}
}
