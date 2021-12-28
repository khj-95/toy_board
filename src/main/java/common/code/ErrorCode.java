package common.code;

public enum ErrorCode {
	
	DATABASE_ACCESS_ERROR("데이터베이스와 통신 중 에러가 발생하였습니다."),
	FAILED_PASSWORD_ERROR("비밀번호가 일치하지 않습니다.",null),
	FAILED_FILE_UPLOAD_ERROR("파일업로드에 실패했습니다.",null);
	
	
	public final String MSG;
	public final String URL;
	
	ErrorCode(String msg) {
		this.MSG = msg;
		this.URL = "/board/board-list";
	}
	
	ErrorCode(String msg, String url) {
		this.MSG = msg;
		this.URL = url;
	}

}
