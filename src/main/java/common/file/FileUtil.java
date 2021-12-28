package common.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class FileUtil {
	
	private static final int MAX_SIZE = 1024*1024*10;
	
	public MultiPartParams fileUpload(HttpServletRequest req) {
		
		Map<String, List> res = new HashMap<String, List>();
		List<FileDTO> fileDTOs = new ArrayList<FileDTO>();
		
		try {
			//http://www.servlets.com/cos/javadoc/com/oreilly/servlet/multipart/MultipartParser.html
			MultipartParser mp = new MultipartParser(req, MAX_SIZE);
			mp.setEncoding("UTF-8");
			//http://www.servlets.com/cos/javadoc/com/oreilly/servlet/multipart/Part.html
			Part part = null;
			while((part = mp.readNextPart()) != null) {
				if(part.isFile() && ((FilePart) part).getFileName() != null ) {
					//파일 처리
					FilePart filePart = (FilePart) part;
					FileDTO fileDTO = new FileDTO();
					//1. 유니크한 파일명 생성
					String renameFileName = UUID.randomUUID().toString();
					//2. 파일 경로 생성 (외부경로 + /연/월/일 형태로 작성)
					//2-1. /연/월/일 형태
					Calendar today = Calendar.getInstance();
					int year = today.get(Calendar.YEAR);
					int month = today.get(Calendar.MONTH) +1;
					int date = today.get(Calendar.DATE);
					String subPath = year + "/" + month + "/" + date + "/";
					//2-2. 저장경로를 웹어플리케이션 외부로 지정
					String savePath = "C:\\CODE\\upload\\" + subPath;
					File dir = new File(savePath);
					if(!dir.exists()) {
						dir.mkdirs();
					}
					//3. FileDTO 생성
					fileDTO.setOriginFileName(filePart.getFileName());
					fileDTO.setRenameFileName(renameFileName + "." + FilenameUtils.getExtension(filePart.getFileName()));
					fileDTO.setSavePath(subPath);
					//파일저장
					filePart.writeTo(new File(savePath + fileDTO.getRenameFileName())); 
					fileDTOs.add(fileDTO);
				} else if (part.isParam()){
					ParamPart paramPart = (ParamPart) part;
					//1. 해당 파라미터명으로 기존에 파라미터값이 존재할 경우
					if(res.containsKey(paramPart.getName())) {
						res.get(paramPart.getName()).add(paramPart.getStringValue());
					//2. 해당 파라미터명으로 처음 파라미터값이 저장되는 경우
					} else {
						List<String> param = new ArrayList<String>();
						param.add(paramPart.getStringValue());
						res.put(paramPart.getName(), param);
					}
				}
			}
			
			res.put("files", fileDTOs);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new MultiPartParams(res);
	}
	

}
