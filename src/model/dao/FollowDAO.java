package model.dao;

import java.util.List;

import model.vo.FollowVO;

public interface FollowDAO {

	public List<FollowVO> selectByMemberId(int memberId);

	public List<FollowVO> selectAll();

	public int insert(FollowVO bean);

	public int delete(int followId, int memberId);

}