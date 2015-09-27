package model.dao.jdbc;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.MemberDAO;
import model.vo.MemberVO;
import util.ConvertType;
import util.HibernateUtil;

public class MemberDAOjdbc implements MemberDAO {
	// private static final String URL = GC.URL;
	// private static final String USERNAME = GC.USERNAME;
	// private static final String PASSWORD = GC.PASSWORD;
	// private DataSource ds;
	// private static final String INSERT = "INSERT INTO member
	// (memberAccount,memberPassword,memberEmail,broadcastWebsite) VALUES (?,
	// cast( ? as varbinary(50)), ?,?)";
	//
	// public MemberDAOjdbc(){
	// try {
	// Context ctx = new InitialContext();
	// this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	@Override
	public int insert(MemberVO member) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(member);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String INSERT2 = "INSERT INTO member
	// (memberEmail,memberPassword,memberAccount,broadcastWebsite) VALUES
	// (?,cast( ? as varbinary(50)), ?,?)";

	@Override
	public int insert2(MemberVO member) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(member);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String GET_MEMBER_LIST = "SELECT
	// memberId,memberAccount, broadcastWebsite FROM member ORDER BY
	// memberAccount";

	@Override
	public List<MemberVO> getMemberList() {
		List<MemberVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	// private static final String GET_ID = "SELECT memberId FROM member WHERE
	// memberAccount=?";

	@Override
	public int getId(String memberAccount) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("select memberId from MemberVO where memberAccount = ?").setParameter(0,
					memberAccount);
			result = (Integer) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Integer> searchId(String memberAccount) {
		List<Integer> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("select memberId from MemberVO where memberAccount like ?")
					.setParameter(0, "%" + memberAccount + "%");
			result = (List<Integer>) query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// 新增
	// private static final String UPDATE = "UPDATE member SET memberPassword=?,
	// memberEmail=?, memberFB=?, memberGoogle=?, memberTwitter=?,
	// memberNickname=?,"
	// +
	// "memberBirthday=?,memberPhoto=?,memberSelfIntroduction=?,broadcastTitle=?,broadcastClassName=?,"
	// + "broadcastTime=?,broadcastDescription=? WHERE memberId=?";

	@Override
	public int update(MemberVO member) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(member);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// private static final String FIND_BY_PK = "SELECT
	// memberId,memberAccount,memberEmail,memberFB,memberGoogle,memberTwitter,memberName,"
	// +
	// "memberNickname,memberBirthday,memberPhoto,memberRegisterTime,memberSelfIntroduction,"
	// +
	// "broadcastWebsite,broadcastTitle,broadcastClassName,broadcastTime,broadcastDescription,"
	// + "broadcastWatchTimes FROM member WHERE memberId=?";

	@Override
	public MemberVO findByPK(int memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MemberVO bean = null;
		try {
			session.beginTransaction();
			bean = (MemberVO) session.get(MemberVO.class, memberId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	// 設定用戶是否被停權
	// private static final String SWITCH_SUSPEND = "UPDATE member SET
	// suspendMember = ? WHERE memberId = ?";

	@Override
	public int switchSuspend(int memberId, boolean suspendRight) {
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MemberVO bean = (MemberVO) session.get(MemberVO.class, memberId);
			bean.setSuspendMember(suspendRight);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getMemberAccount(String memberAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String memberAccountResult = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("select memberAccount from MemberVO where memberAccount = ?")
					.setParameter(0, memberAccount);
			memberAccountResult = (String) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return memberAccountResult;
	}

	@Override
	public MemberVO findByMemberAccount(String memberAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MemberVO bean = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberVO where memberAccount = ?").setParameter(0, memberAccount);
			bean = (MemberVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public MemberVO findByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MemberVO bean = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberVO where memberEmail = ?").setParameter(0, email);
			bean = (MemberVO) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}
	// private static final String UPDATE_PHOTO = "UPDATE Member SET memberPhoto
	// = ? WHERE memberAccount = ?";

	@Override
	public int updatePhoto(int memberId, byte[] photo) {
		int result = -1;
		MemberVO bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (MemberVO) session.get(MemberVO.class, memberId);
			bean.setMemberName(ConvertType.convertToBase64(photo, "jpeg"));
			bean.setMemberPhoto(photo);
			session.getTransaction().commit();
			result = 1;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args) throws SQLException, ParseException {

		MemberDAO temp = new MemberDAOjdbc();
		// // memberDao的insert，與insert2的差異在於用戶需輸入memberAccount
		// MemberVO member1 = new MemberVO();
		// member1.setMemberAccount("godzilla");
		// member1.setMemberPassword("G".getBytes());
		// member1.setMemberEmail("godzilla@gmail.com");
		// member1.setMemberRegisterTime(new java.util.Date());
		// member1.setMemberSelfIntroduction("");
		// member1.setBroadcastWebsite("");
		// member1.setBroadcastTitle("");
		// member1.setBroadcastClassName("");
		// member1.setBroadcastWatchTimes(0);
		// member1.setSuspendMember(false);
		// int count1 = temp.insert2(member1);
		// System.out.println("insert " + count1 + " rows");

		// memberDao的insert2，與insert的差異在於用戶少輸入memberAccount
		// MemberVO member2 = new MemberVO();
		// member2.setMemberPassword("shu".getBytes());
		// member2.setMemberEmail("crazy@gmail.com");
		// int count2 = temp.insert2(member2);
		// System.out.println("insert2 " + count2 + " rows");

		// memberDao的get member list
		List<MemberVO> members = temp.getMemberList();
		for (MemberVO member : members) {
			System.out.print(member.getMemberId() + ", ");
			System.out.print(member.getMemberAccount() + ", ");
			System.out.print(member.getMemberPhoto() + ", ");
			System.out.println(member.getBroadcastWebsite());
		}

		// memberDao的getId
		// System.out.print(temp.getId("godzilla"));

		// memberDao的find by PrimaryKey
		// MemberVO member3 = temp.findByPK(3);
		// System.out.println(member3);
		// System.out.println("memberId =" + member3.getMemberId());
		// System.out.println("memberAccount = " + member3.getMemberAccount());
		// System.out.println("memberPassword = " +
		// member3.getMemberPassword());
		// System.out.println("memberEmail = " + member3.getMemberEmail());
		// System.out.println("memberFB = " + member3.getMemberFB());
		// System.out.println("memberGoogle = " + member3.getMemberGoogle());
		// System.out.println("memberTwitter = " + member3.getMemberTwitter());
		// System.out.println("memberName = " + member3.getMemberName());
		// System.out.println("memberNickname = " +
		// member3.getMemberNickname());
		// System.out.println("memberBirthday = " +
		// member3.getMemberBirthday());
		// System.out.println("memberPhoto = " + member3.getMemberPhoto());
		// System.out.println("memberRegisterTime = " +
		// member3.getMemberRegisterTime());
		// System.out.println("memberSelfIntroduction = " +
		// member3.getMemberSelfIntroduction());
		// System.out.println("broadcastWebsite = " +
		// member3.getBroadcastWebsite());
		// System.out.println("broadcastTitle = " +
		// member3.getBroadcastTitle());
		// System.out.println("broadcastClassName = " +
		// member3.getBroadcastClassName());
		// System.out.println("broadcastTime = " + member3.getBroadcastTime());
		// System.out.println("broadcastDescription = " +
		// member3.getBroadcastDescription());
		// System.out.println("broadcastWatchTimes = " +
		// member3.getBroadcastWatchTimes());
		//
		// update
		// MemberVO member4 = new MemberVO();
		// member4.setMemberPassword("normal".getBytes());
		// member4.setMemberEmail("normal@yahoo.com");
		// member4.setMemberFB("abuse");
		// member4.setMemberGoogle("abnormal@gmail.com");
		// member4.setMemberTwitter("cure");
		// member4.setMemberNickname("insane");
		// java.text.SimpleDateFormat converter = new
		// java.text.SimpleDateFormat("yyyy-MM-dd");
		// member4.setMemberBirthday(converter.parse("2015-2-29"));
		// member4.setMemberPhoto("".getBytes());
		// member4.setMemberSelfIntroduction("Why so serious?");
		// member4.setBroadcastTitle("crazy world");
		// member4.setBroadcastClassName("life");
		// member4.setBroadcastTime(converter.parse("2015-11-30"));
		// member4.setBroadcastDescription("I try to be a good person");
		// member4.setMemberId(17);
		// member4.setMemberAccount("xxxx");
		// int count3 = temp.update(member4);
		// System.out.println("update " + count3 + " rows");
		// temp.switchSuspend(17, true);
	}
}