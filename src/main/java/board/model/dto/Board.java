package board.model.dto;

import java.sql.Date;

public class Board {
	
	private Integer bdIdx;
	private String writer;
	private String title;
	private Date regDate;
	private String content;
	private Integer views;
	
	public Integer getBdIdx() {
		return bdIdx;
	}
	public void setBdIdx(Integer bdIdx) {
		this.bdIdx = bdIdx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", writer=" + writer + ", title=" + title + ", regDate=" + regDate
				+ ", content=" + content + ", views=" + views + "]";
	}

	
	

}
