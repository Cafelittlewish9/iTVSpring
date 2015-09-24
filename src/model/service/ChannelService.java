//package model.service;
//
//import java.util.Collection;
//import model.dao.ChannelDAO;
//import model.dao.jdbc.ChannelDAOjdbc;
//import model.vo.BroadcastOrderVO;
//import model.vo.ChannelVO;
//
//public class ChannelService {
//	private ChannelDAO dao;
//
//	public ChannelService() {
//		this.dao = new ChannelDAOjdbc();
//	}
//
//	public Collection<ChannelVO> allChannel(int memberId) {
//		return dao.selectByMemberId(memberId);
//	}
//
//	public boolean addChannel(ChannelVO bean) {
//		boolean result = false;
//		if (bean != null) {
//			int temp = dao.insert(bean);
//			if (temp == 1) {
//				result = true;
//			}
//		}
//		return result;
//	}
//
//	public boolean changeChannel(ChannelVO bean) {
//		boolean result = false;
//		if (bean != null) {
//			int temp = dao.update(bean);
//			if (temp == 1) {
//				result = true;
//			}
//		}
//		return result;
//	}
//
//	public boolean removeChannel(int memberId, byte channelNo) {
//		return dao.delete(memberId, channelNo);
//	}
//
//	// return false是成功刪除的意思，注意!!!!
//	public boolean removeAllChannel(int memberId) {
//		return dao.deleteAll(memberId);
//	}
//}
