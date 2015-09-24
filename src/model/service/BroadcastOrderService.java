package model.service;

import java.util.Collection;

import model.dao.BroadcastOrderDAO;
import model.dao.jdbc.BroadcastOrderDAOjdbc;
import model.vo.BroadcastOrderVO;

public class BroadcastOrderService {
	private BroadcastOrderDAO dao;

	public BroadcastOrderService() {
		this.dao = new BroadcastOrderDAOjdbc();
	}

	public Collection<BroadcastOrderVO> broadcastOrder() {
		return dao.selectAll();
	}

	public Collection<BroadcastOrderVO> searchBroadcast(String keyword) {
		Collection<BroadcastOrderVO> list = null;
		if (keyword != null && keyword.trim().length() != 0) {
			list = dao.selectByMemberAccountOrBroadcastTitle(keyword, keyword);
		}
		return list;
	}

	public BroadcastOrderVO searchAccount(String memberAccount) {
		BroadcastOrderVO list = null;
		if (memberAccount != null && memberAccount.trim().length() != 0) {
			list = dao.selectByMemberAccount(memberAccount);
		}
		return list;
	}

	public BroadcastOrderVO createBroadcast(BroadcastOrderVO bean) {
		BroadcastOrderVO result = null;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = dao.selectByMemberAccount(bean.getMemberAccount());
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
			int temp = dao.delete(memberAccount);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}
}
