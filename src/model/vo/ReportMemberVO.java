package model.vo;

import java.io.Serializable;

public class ReportMemberVO implements Serializable {
	private int orderId;
//	private int reportedMemberId;
	private java.util.Date reportTime;
	private String reportReason;
	private MemberVO member;

	@Override
	public String toString() {
		// VO只要simpleDateFromat就死，註解之後進資料庫格式也正確，所以先註解。
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String date = sdf.format(reportTime);
		return orderId + "被檢舉的會員ID為: " + member.getMemberId() + " (" + reportTime + ")";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

//	public int getReportedMemberId() {
//		return reportedMemberId;
//	}
//
//	public void setReportedMemberId(int reportedMemberId) {
//		this.reportedMemberId = reportedMemberId;
//	}

	public java.util.Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(java.util.Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
}
