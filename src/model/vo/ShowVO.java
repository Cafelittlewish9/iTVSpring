package model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ShowVO implements Serializable {
	private int memberId;
	private java.util.Date showTime;
//	private int videoId;
	private VideoVO video;

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(showTime);
		return "節目網址: " + video.getVideoWebsite() + " (" + date + ")" + video.getVideoId();
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (obj == this) {
//			return true;
//		}
//		if (!(obj instanceof ShowVO)) {
//			return false;
//		}
//		ShowVO bean = (ShowVO) obj;
//		return new EqualsBuilder().append(this.memberId, bean.getMemberId()).append(this.videoId, bean.getVideoId())
//				.isEquals();
//
//	}
//
//	@Override
//	public int hashCode() {
//		return new HashCodeBuilder().append(this.memberId).append(this.videoId).toHashCode();
//	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public java.util.Date getShowTime() {
		return showTime;
	}

	public void setShowTime(java.util.Date showTime) {
		this.showTime = showTime;
	}

//	public int getVideoId() {
//		return videoId;
//	}
//
//	public void setVideoId(int videoId) {
//		this.videoId = videoId;
//	}

	public VideoVO getVideo() {
		return video;
	}

	public void setVideo(VideoVO video) {
		this.video = video;
	}
}
