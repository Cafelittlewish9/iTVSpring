package model.dao;

import java.util.List;

import model.vo.VideoCommentsVO;

public interface VideoCommentsDAO {

	public List<VideoCommentsVO> selectAll();
	
	List<VideoCommentsVO> selectAllASC();
	
	public List<VideoCommentsVO> selectByVideoId(int videoId);

	public int insert(VideoCommentsVO videoComments);

	public int update(VideoCommentsVO videoComments);

	public int delete(int commentId);

}