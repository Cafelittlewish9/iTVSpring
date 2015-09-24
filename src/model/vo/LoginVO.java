package model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class LoginVO implements Serializable {
	private java.util.Date loginTime;
	private String ip;
	private String memberAccount;

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(loginTime);
		return "你的登入位置 IP: " + ip + " (" + date + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof LoginVO)) {
			return false;
		}
		LoginVO bean = (LoginVO) obj;
		return new EqualsBuilder().append(this.loginTime, bean.getLoginTime()).append(this.ip, bean.getIp()).isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.loginTime).append(this.ip).toHashCode();
	}

	public java.util.Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
}
