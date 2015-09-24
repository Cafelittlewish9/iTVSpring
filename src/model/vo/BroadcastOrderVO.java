package model.vo;

import java.io.Serializable;

public class BroadcastOrderVO implements Serializable {
//	private String memberAccount;
	private String broadcastSite;
	private String broadcastTitle;
	private java.util.Date broadcastTime;
	private long broadcastWatchTimes;
	private MemberVO member;

	@Override
	public String toString() {
		return " Account: " + member.getMemberAccount() + " 標題: " + broadcastTitle + " 網址: " + broadcastSite + System.lineSeparator();
	}

//	public String getMemberAccount() {
//		return memberAccount;
//	}
//
//	public void setMemberAccount(String memberAccount) {
//		this.memberAccount = memberAccount;
//	}

	public String getBroadcastSite() {
		return broadcastSite;
	}

	public void setBroadcastSite(String broadcastSite) {
		this.broadcastSite = broadcastSite;
	}

	public String getBroadcastTitle() {
		return broadcastTitle;
	}

	public void setBroadcastTitle(String broadcastTitle) {
		this.broadcastTitle = broadcastTitle;
	}

	public java.util.Date getBroadcastTime() {
		return broadcastTime;
	}

	public void setBroadcastTime(java.util.Date broadcastTime) {
		this.broadcastTime = broadcastTime;
	}

	public long getBroadcastWatchTimes() {
		return broadcastWatchTimes;
	}

	public void setBroadcastWatchTimes(long broadcastWatchTimes) {
		this.broadcastWatchTimes = broadcastWatchTimes;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
}
