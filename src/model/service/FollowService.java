package model.service;

import java.util.Collection;
import model.dao.FollowDAO;
import model.dao.jdbc.FollowDAOjdbc;
import model.vo.FollowVO;

public class FollowService {
	private FollowDAO dao;

	public FollowService() {
		this.dao = new FollowDAOjdbc();
	}

	public boolean follow(FollowVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	//!!!!是反過來的，例如輸入(1,2) 在資料庫裡會消失的是 "memberID:2 followID:1"!!!!!
	//感謝測試員，說明一下：因為sql指令的where條件確實是先寫followId才是memberId，2015/9/21已修正
	public boolean unfollow(int followId, int memberId) {
		boolean result = false;
		int temp = dao.delete(followId, memberId);
		if (temp == 1) {
			result = true;
		}
		return result;
	}
	
	public Collection<FollowVO> followList(int memberId) {
		return dao.selectByMemberId(memberId);
	}
}
