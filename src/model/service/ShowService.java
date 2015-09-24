package model.service;

import java.util.Collection;
import model.dao.ShowDAO;
import model.dao.jdbc.ShowDAOjdbc;
import model.vo.ShowVO;

public class ShowService {
	private ShowDAO dao;

	public ShowService() {
		this.dao = new ShowDAOjdbc();
	}

	public Collection<ShowVO> showList(int memberId) {
		Collection<ShowVO> list = dao.selectJoinVideo(memberId);
		return list;
	}

	public ShowVO checkShow(int memberId, int videoId) {
		ShowVO list = dao.selectByIdAndWebsite(memberId, videoId);
		return list;
	}

	public Collection<ShowVO> addShow(ShowVO bean) {
		Collection<ShowVO> list = null;
		int result = dao.insert(bean);
		if (result == 1) {
			list = this.showList(bean.getMemberId());
		}
		return list;
	}

	public Collection<ShowVO> changeShow(ShowVO bean) {
		Collection<ShowVO> list = null;
		boolean result = false;
		if (bean != null) {
			int temp = dao.update(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return list;
	}
	public Collection<ShowVO> removeShow(ShowVO bean) {
		Collection<ShowVO> result = null;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = dao.selectJoinMember(bean.getMemberId());
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// ShowService service = new ShowService();
		// for(ShowVO bean:service.showList(2)){
		// System.out.println(bean);
		// System.out.println(bean.getTitle());
		// }
	}
}
