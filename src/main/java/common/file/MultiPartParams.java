package common.file;

import java.util.List;
import java.util.Map;

public class MultiPartParams {
	
	private Map<String, List> params;
	
	public MultiPartParams(Map<String, List> params) {
		this.params = params;
	}
	
	public String getParameter(String name) {
		if(name.equals("files")) {
			throw new RuntimeException("files는 사용할 수 없는 파라미터 명입니다.");
		}
		return (String)params.get(name).get(0);
	}

	public String[] getParameterValues(String name) {
		if(name.equals("files")) {
			throw new RuntimeException("files는 사용할 수 없는 파라미터 명입니다.");
		}
		List<String> res = params.get(name);
		return res!=null ? res.toArray(new String[res.size()]): null;
	}

	public List<FileDTO> getFilesInfo() {
		return params.get("files");
	}

	

}
