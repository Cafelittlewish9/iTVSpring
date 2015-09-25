package model.service;

import java.sql.SQLException;
import java.util.Arrays;

import model.dao.MemberDAO;
import model.dao.jdbc.MemberDAOjdbc;
import model.vo.MemberVO;
import util.ConvertType;
import util.ServicePasswordChange;

//註冊後轉update頁面要由controller處理
//查詢、修改個人資料、搜尋會員
public class MemberService {
	private MemberDAO dao;

	public MemberService() {
		this.dao = new MemberDAOjdbc();
	}

	// registry1採帳號密碼信箱註冊；registry2是採信箱密碼註冊
	public boolean registry1(MemberVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	// registry1採帳號密碼信箱註冊；registry2是採信箱密碼註冊
	public boolean registry2(MemberVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert2(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	// login1是採用帳號登入；login2是採信箱登入
	public MemberVO login1(String username, String password) {
		MemberVO member = null;
		if (username != null && username.trim().length() != 0) {
			MemberVO bean = dao.findByMemberAccount(username);
			if (bean != null) {
				if (!bean.isSuspendMember()) {
					if (!bean.isSuspendMember()) {
						if (this.comparePassword(password, bean.getMemberPassword())) {
							member = bean;
						}
					}
				}
			}
		}
		return member;
	}

	// login1是採用帳號登入；login2是採信箱登入
	public MemberVO login2(String usermail, String password) {
		MemberVO member = null;
		if (usermail != null && usermail.trim().length() != 0) {
			MemberVO bean = dao.findByEmail(usermail);
			if (bean != null) {
				if (!bean.isSuspendMember()) {
					if (this.comparePassword(password, bean.getMemberPassword())) {
						member = bean;
					}
				}
			}
		}
		return member;
	}

	public boolean comparePassword(String password1, byte[] password2) {
		boolean result = false;
		if (password1 != null && password1.trim().length() != 0 && password2 != null && password2.length != 0) {
			byte[] temp1 = password1.getBytes();
			if (Arrays.equals(temp1, password2)) {
				result = true;
			}
		}
		return result;
	}

	// 會員修改密碼
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		boolean result = false;
		if (username != null && username.trim().length() != 0) {
			MemberVO bean = this.login1(username, oldPassword);
			byte[] temp = newPassword.getBytes();
			bean.setMemberPassword(temp);
			dao.update(bean);
		}
		return result;
	}

	// ↓↓↓↓↓↓↓↓雖然很討厭他，但我還是測完了，但請再幫我測一次感恩。
	public String forgetPassword(String memberAccount) {
		String newPassword = null;
		if (memberAccount != null && memberAccount.trim().length() != 0) {
			MemberVO bean = dao.findByMemberAccount(memberAccount);
			if (bean != null) {
				String replace = Math.random() * 10000 + "";// 亂數產生一組數字去轉成md5回傳給使用者
				newPassword = ServicePasswordChange.getMD5Endocing(replace);
				byte[] temp = newPassword.getBytes();
				bean.setMemberPassword(temp);
				dao.update(bean);
			}
		}
		return newPassword;
	}
	// ↑↑↑↑↑↑↑雖然很討厭他，但我還是測完了，但請再幫我測一次感恩。

	// 會員查詢個資
	public MemberVO showMemberInfo(String username, String password) {
		MemberVO mvo = this.login1(username, password);
		return mvo;
	}

	// ↑是否是指連查詢個資都要輸入一次帳密？
	public MemberVO searchByMemberAccount(String memberAccount) {
		MemberVO bean = dao.findByMemberAccount(memberAccount);
		return bean;
	}

	// 更改、測試完成
	public boolean update(MemberVO bean) {
		boolean result = false;
		int temp = -1;
		if (bean != null) {
			temp = dao.update(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public boolean memberAccountHasBeanUsed(String memberAccount) {
		boolean result = false;
		String temp = dao.getMemberAccount(memberAccount);
		if (temp != null) {
			result = true;
		}
		return result;
	}

	public boolean changePhoto(MemberVO bean, byte[] photo) {
		boolean result = false;
		bean.setMemberPhoto(photo);
		if (dao.update(bean) == 1) {
			result = true;
		}
		return result;
	}

	// 測完了，沒問題可以動呦吼吼。
	public int suspendMember(int memberId, boolean suspendRight) {
		int result = -1;
		if (memberId != 0) {
			result = dao.switchSuspend(memberId, suspendRight);
		}
		return result;
	}

	public static void main(String[] args) throws SQLException {
		MemberService service = new MemberService();
		// MemberVO mvo = service.login1("niceguy", "E");
		// System.out.println("");
		// String result = service.registry2("madclown@gmail.com", "E");
		// System.out.println(result);

		// String result=service.registry1("niceguy", "E","madclown@gmail.com");
		// System.out.println("registry result="+result);
		// MemberVO mvo = service.login1("niceguy", "E");
		// System.out.println("VO info="+mvo);

		// MemberVO mvo = service.login2("Pikachu@gmail.com", "A");
		// System.out.println("VO info="+mvo);

		// String result = service.changePassword("niceguy",
		// "madclown@gmail.com", "E");
		// System.out.println("result="+result);

		// Member switchSuspend
		// MemberService memberService = new MemberService();
		// System.out.println(memberService.suspendMember(7, false));

	}
}
