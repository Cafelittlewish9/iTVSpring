package model.dao;

import java.util.List;

import model.vo.VideoVO;

public interface VideoDAO {

	public List<VideoVO> selectByVideoTitle(String videoTitle);
	
	public List<VideoVO> selectByVideoName(String videoName);

	public List<VideoVO> selectByVideoClassName(String videoClassName);
	
	public List<VideoVO> selectByMemberId(int memberId);

	public List<VideoVO> selectAll();

	public int insert(VideoVO bean);

	public int update(VideoVO bean);

	public int delete(int videoId);

}