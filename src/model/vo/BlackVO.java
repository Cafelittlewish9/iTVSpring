package model.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BlackVO implements Serializable {
	private int memberId;
//	private int blackedId;
	private MemberVO member;

	@Override
	public String toString() {
		return memberId + ": " + member.getMemberId() + " (被黑的)";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof BlackVO)) {
			return false;
		}
		BlackVO bean = (BlackVO) obj;
		return new EqualsBuilder()
				.append(this.memberId, bean.getMemberId())
				.append(this.member.getMemberId(), bean.getMember().getMemberId())
				.isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.memberId).append(this.member.getMemberId()).toHashCode();
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
//
//	public int getBlackedId() {
//		return blackedId;
//	}
//
//	public void setBlackedId(int blackedId) {
//		this.blackedId = blackedId;
//	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
}
