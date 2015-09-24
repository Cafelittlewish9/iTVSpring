package model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.dao.BroadcastOrderDAO;
import model.dao.MemberDAO;
import model.dao.jdbc.BroadcastOrderDAOjdbc;
import model.dao.jdbc.MemberDAOjdbc;
import model.vo.BroadcastOrderVO;

public class BroadcastOrderService {
	private BroadcastOrderDAO dao;
	private MemberDAO mdao;

	public BroadcastOrderService() {
		this.dao = new BroadcastOrderDAOjdbc();
		this.mdao = new MemberDAOjdbc();
	}

	public Collection<BroadcastOrderVO> broadcastOrder() {
		return dao.selectAll();
	}

//	public Collection<BroadcastOrderVO> searchBroadcast(String keyword) {
//		Collection<BroadcastOrderVO> list = null;
//		if (keyword != null && keyword.trim().length() != 0) {
//			List<Integer> temp = mdao.searchId(keyword);
//			if (temp != null && !temp.isEmpty()) {
//				list = new ArrayList<BroadcastOrderVO>();
//				for (Integer i : temp) {
//					list = dao.selectByMemberAccountOrBroadcastTitle(keyword, keyword);
//				}
//			}
//		}
//		return list;
//	}

	public BroadcastOrderVO searchAccount(String memberAccount) {
		BroadcastOrderVO result = null;
		if (memberAccount != null && memberAccount.trim().length() != 0) {
			result = dao.selectByMemberId(mdao.getId(memberAccount));
		}
		return result;
	}

	public BroadcastOrderVO createBroadcast(BroadcastOrderVO bean) {
		BroadcastOrderVO result = null;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = dao.selectByMemberId(bean.getMember().getMemberId());
			}
		}
		return result;
	}

	public boolean changeTitle(BroadcastOrderVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.update(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public boolean removeBroadcast(String memberAccount) {
		boolean result = false;
		if (memberAccount != null && memberAccount.trim().length() != 0) {
			int temp = dao.delete(mdao.getId(memberAccount));
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		BroadcastOrderService service = new BroadcastOrderService();
		BroadcastOrderVO bean = service.searchAccount("A");
		System.out.println(bean);
	}
}
