package model.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FollowVO implements Serializable {
	private int memberId;
//	private int followId;
	private MemberVO member;
	
	@Override
	public String toString() {
		return memberId + ": " + member.getMemberAccount() + " (被追隨的)";
	}
//	@Override
//	public boolean equals(Object obj) {
//		if (obj == this) {
//			return true;
//		}
//		if (!(obj instanceof FollowVO)) {
//			return false;
//		}
//		FollowVO bean = (FollowVO) obj;
//		return new EqualsBuilder()
//				.append(this.memberId, bean.getMemberId())
//				.append(this.followId, bean.getFollowId())
//				.isEquals();
//
//	}
//
//	@Override
//	public int hashCode() {
//		return new HashCodeBuilder().append(this.memberId).append(this.followId).toHashCode();
//	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
//	public int getFollowId() {
//		return followId;
//	}
//	public void setFollowId(int followId) {
//		this.followId = followId;
//	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
}
