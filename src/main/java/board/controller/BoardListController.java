package board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.Board;
import board.model.service.BoardService;
import common.file.FileDTO;
import common.file.FileUtil;
import common.file.MultiPartParams;

@WebServlet("/board/*")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardService = new BoardService();
	
    public BoardListController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Board board = new Board();
		Integer bdIdx;
		
		String[] uriArr = request.getRequestURI().split("/");
		
		switch(uriArr[uriArr.length - 1]) {
		case "board-list": 
			//페이징
			int page = 1;
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			page = page == 0 ? 1 : page;
			Map<String, Object> pageAndBoards = boardService.selectAllBoardList(page);
			request.setAttribute("pageAndBoard", pageAndBoards);
			
			request.getRequestDispatcher("BoardList").forward(request, response);
			
			break;
			
		case "board-form": 
			
			request.getRequestDispatcher("WriteBoard").forward(request, response);
			
			break;
			
		case "write-board":
			
			MultiPartParams mp = new FileUtil().fileUpload(request);
			List<FileDTO> fileDTOs = mp.getFilesInfo();
			
			board.setWriter(mp.getParameter("writer"));
			board.setTitle(mp.getParameter("title"));
			board.setContent(mp.getParameter("content"));
			
			boardService.insertBoard(board, fileDTOs);
					
			response.sendRedirect("/board/board-list");
			
			break;
		case "modify-form":
			
			bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
			
			Map<String, Object> boardForModify = boardService.selectBoardByBdIdx(bdIdx);
			
			request.setAttribute("boardForModify", boardForModify);
			
			request.getRequestDispatcher("ModifyBoard").forward(request, response);
			
			break;
		case "modify-board":
			
			MultiPartParams mpForModify = new FileUtil().fileUpload(request);
			List<FileDTO> fileDTOsForModify = mpForModify.getFilesInfo();
			
			bdIdx = Integer.parseInt(mpForModify.getParameter("bdIdx"));
			
			board.setBdIdx(bdIdx);
			board.setWriter(mpForModify.getParameter("writer"));
			board.setTitle(mpForModify.getParameter("title"));
			board.setContent(mpForModify.getParameter("content"));
			
			String[] keepFiles = mpForModify.getParameterValues("keepFiles");
			
			boardService.updateBoardByBdIdx(board,fileDTOsForModify, keepFiles);
			
			response.sendRedirect("/board/board-detail?bdIdx="+ mpForModify.getParameter("bdIdx"));
			break;
		case "board-detail":
			
			bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
			//조회수 업데이트
			boardService.updateBoardViewsByBdIdx(bdIdx);
			
			Map<String, Object> boardForDetail = boardService.selectBoardByBdIdx(bdIdx);
			
			request.setAttribute("boardForDetail", boardForDetail);
			
			request.getRequestDispatcher("DetailBoard").forward(request, response);
			break;
		case "delete-board":
			bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
			
			boardService.deleteBoardByBdIdx(bdIdx);
			
			response.sendRedirect("/board/board-list");
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
