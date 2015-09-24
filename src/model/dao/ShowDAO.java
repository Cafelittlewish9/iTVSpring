package model.dao;

import java.util.List;

import model.vo.ShowVO;

public interface ShowDAO {

	public List<ShowVO> selectJoinMember(int memberId);
	
	public List<ShowVO> selectJoinVideo(int memberId);
	
	public ShowVO selectByIdAndWebsite(int memberId, int videoId);

	public List<ShowVO> selectAll();

	public int insert(ShowVO bean);

	public int update(ShowVO bean);

	public int delete(int memberId, int videoId);

}