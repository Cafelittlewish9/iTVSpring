package model.service;

import java.util.Collection;
import java.util.List;

import model.dao.VideoDAO;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.MemberVO;
import model.vo.VideoVO;

public class VideoService {
	private VideoDAO dao;

	public VideoService() {
		this.dao = new VideoDAOjdbc();
	}

	public Collection<VideoVO> hotVideo() {
		return dao.selectAll();
	}

	public Collection<VideoVO> searchMemberId(int memberId) {
		return dao.selectByMemberId(memberId);
	}

	public Collection<VideoVO> searchVideo(String videoTitle) {
		return dao.selectByVideoTitle(videoTitle);
	}
	
	public Collection<VideoVO> searchVideoName(String videoName) {
		return dao.selectByVideoName(videoName);
	}

	public Collection<VideoVO> videoClassList(String videoClassName) {
		return dao.selectByVideoClassName(videoClassName);
	}

	public boolean uploadVideo(VideoVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public boolean updateVideo(VideoVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.update(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public boolean removeVideo(int videoId) {
		boolean result = false;
		int temp = dao.delete(videoId);
		if (temp == 1) {
			result = true;
		}
		return result;
	}

	// 不知道是否應該要有一個全刪的方法
	public static void main(String[] args) {
		VideoService service = new VideoService();
		VideoVO bean = new VideoVO();
		// 是說不是應該要會員登入之後才准上傳嗎？還是我們很大方，他願意上傳就上傳，與會員的差異就只在能不能針對實況留言？！
		MemberVO member = new MemberVO();

		bean.setMemberId(5);
		bean.setVideoWebsite(member.getBroadcastWebsite());
		bean.setVideoClassName("㊣↙煞氣a↗㊣☆");
		bean.setVideoDescription("㊣↙煞氣a正牌哥吉拉之澎湖海報你把我一世英名都毀惹↗㊣☆ ");
		bean.setVideoName("㊣↙煞氣a哥吉拉↗㊣☆");
		// 按目前資料庫的結果看來應該是上傳路徑，那不就應該要有個IO？！
		bean.setVideoPath("G:\\godzillaRocks.avi");
		boolean result = service.uploadVideo(bean);
		System.out.println(result);
		// System.out.println(service.searchBlackAccount(1));
	}

}
