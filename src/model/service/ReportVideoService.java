package model.service;

import java.util.Collection;

import model.dao.ReportVideoDAO;
import model.dao.VideoDAO;
import model.dao.jdbc.ReportVideoDAOjdbc;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.ReportVideoVO;

public class ReportVideoService {
	private ReportVideoDAO dao;
	private VideoDAO dao2;
	
	public ReportVideoService(){
		this.dao = new ReportVideoDAOjdbc();
		this.dao2 = new VideoDAOjdbc();
	}
	public Collection<ReportVideoVO> selectAllList(){
		return dao.selectAll();
	}
	public boolean addReportVideo(ReportVideoVO bean){
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}
	
	//刪除成功為false
	public boolean deleteVideo(ReportVideoVO bean){
		int result1 = dao2.delete(bean.getVideo().getVideoId());
		int result2 = dao.delete(bean.getOrderId());
		if (result1 == 1 && result2 == 1) {
			return true;
		} else {
			return false;
		}
	}	
}
