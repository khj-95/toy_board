package common.code;

public enum Config {
	
	DOMAIN("http://localhost:9090"),
	UPLOAD_PATH("C:\\CODE\\upload\\");
	
	public final String DESC;
	
	Config(String desc) {
		this.DESC = desc;
	}

}
