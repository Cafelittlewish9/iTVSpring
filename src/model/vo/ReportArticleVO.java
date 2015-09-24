package model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReportArticleVO implements Serializable {
	private int orderId;
//	private int reportedArticleId;
	private java.util.Date reportTime;
	private String reportReason;
	private ArticleVO article;

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(reportTime);
		return orderId + "被檢舉的文章ID為: " + article.getArticleId() + " (" + date + ")";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

//	public int getReportedArticleId() {
//		return reportedArticleId;
//	}
//
//	public void setReportedArticleId(int reportedArticleId) {
//		this.reportedArticleId = reportedArticleId;
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

	public ArticleVO getArticle() {
		return article;
	}

	public void setArticle(ArticleVO article) {
		this.article = article;
	}
}
