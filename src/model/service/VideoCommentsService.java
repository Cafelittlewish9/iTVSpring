package model.service;

import java.util.List;

import model.dao.VideoCommentsDAO;
import model.dao.jdbc.VideoCommentsDAOjdbc;
import model.vo.VideoCommentsVO;

public class VideoCommentsService {
	private VideoCommentsDAO dao;
	
	public VideoCommentsService(){
		this.dao = new VideoCommentsDAOjdbc();
	}
	
	public List<VideoCommentsVO> selectAllComments(){
		return dao.selectAll();
	}
	public List<VideoCommentsVO> videoCommentsList(int videoId){
		return dao.selectByVideoId(videoId);
	}
	
	public boolean insertVideoComments(VideoCommentsVO bean){
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}
	
	public boolean updateVideoComments(VideoCommentsVO bean){
		boolean result = false;
		if (bean != null) {
			int temp = dao.update(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}
	
	public boolean deleteVideoComments(int commentId){
		boolean result = false;
		int temp = dao.delete(commentId);
		if (temp == 1) {
			result = true;
		}
		return result;
	}
	
	
	public static void main(String[] args){
		VideoCommentsService service = new VideoCommentsService();
//		boolean bool = service.insertVideoComments(1, 4, "安安");
//		boolean bool = service.updateVideoComments("修改安安", java.sql.Timestamp.valueOf("1911-01-01 10:10:10.000"), 17);
		boolean bool = service.deleteVideoComments(17);
		
		System.out.println(bool);
		
	}
	
	
	
}
