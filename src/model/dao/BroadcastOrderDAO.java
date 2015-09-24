package model.dao;

import java.util.List;

import model.vo.BroadcastOrderVO;

public interface BroadcastOrderDAO {

	public List<BroadcastOrderVO> selectAll();

	public List<BroadcastOrderVO> selectByMemberAccountOrBroadcastTitle(int memberId, String broadcastTitle);
	
	public BroadcastOrderVO selectByMemberId(int memberId);

	public int insert(BroadcastOrderVO bean);

	public int update(BroadcastOrderVO bean);

	public int delete(int memberId);

}