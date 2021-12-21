package common.file;

import java.sql.Date;

public class FileDTO {
	
	private Integer flIdx;
	private Integer memBdIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private Date regDate;
	private Integer isDel;
	
	public Integer getFlIdx() {
		return flIdx;
	}
	public void setFlIdx(Integer flIdx) {
		this.flIdx = flIdx;
	}
	public Integer getMemBdIdx() {
		return memBdIdx;
	}
	public void setMemBdIdx(Integer memBdIdx) {
		this.memBdIdx = memBdIdx;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public String getRenameFileName() {
		return renameFileName;
	}
	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	@Override
	public String toString() {
		return "FileDTO [flIdx=" + flIdx + ", memBdIdx=" + memBdIdx + ", originFileName=" + originFileName
				+ ", renameFileName=" + renameFileName + ", savePath=" + savePath + ", regDate=" + regDate + ", isDel="
				+ isDel + "]";
	}
	
	
	

}
