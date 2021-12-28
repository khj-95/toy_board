package common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.code.Config;

@WebServlet("/download")
public class FileHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileHandler() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//저장된 파일 위치
		String filePath = Config.UPLOAD_PATH.DESC + request.getParameter("savePath") + request.getParameter("renameFileName");
		//저장할 파일 경로
		String savePath = "C:\\CODE\\upload\\" + request.getParameter("savePath");
		//저장된 파일
		File inputFile = new File(filePath);
		//저장할 파일 경로 디렉토리
		File outputFile = new File(savePath);
		//저장할 파일 경로 디렉토리 존재하지 않은 경우 디렉토리 생성
		if(!outputFile.exists()) {
			outputFile.mkdir();
		}
		//파일 생성
		File saveFile = new File(savePath + request.getParameter("originFileName"));
		saveFile.createNewFile();
		//저장된 파일 정보를 읽어 와서 저장할 파일에 쓰기
		try(FileInputStream fis = new FileInputStream(inputFile);
				FileOutputStream fos = new FileOutputStream(saveFile);) {
			//the next byte of data, or -1 if the end of the file is reached.
			int data;
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//파일명이 한글인 경우도 있어서 URLEncoder 클래스의 encode 메서드를 사용하여 UTF-8로 인코딩
		String originFileName = URLEncoder.encode(request.getParameter("originFileName"),"UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + originFileName);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
