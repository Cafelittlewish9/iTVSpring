package model.dao;

import java.util.List;

import model.vo.BroadcastOrderVO;

public interface BroadcastOrderDAO {

	public List<BroadcastOrderVO> selectAll();

	public List<BroadcastOrderVO> selectByMemberAccountOrBroadcastTitle(String memberAccount, String broadcastTitle);
	
	public BroadcastOrderVO selectByMemberAccount(String memberAccount);

	public int insert(BroadcastOrderVO bean);

	public int update(BroadcastOrderVO bean);

	public int delete(String memberAccount);

}