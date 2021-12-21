package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.model.dao.BoardDao;
import board.model.dto.Board;
import common.file.FileDTO;
import common.jdbc.JDBCTemplate;
import common.paging.Paging;

public class BoardService {

	JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
	BoardDao boardDao = new BoardDao();
	
	public Map<String, Object> selectAllBoardList(int page) {
		Map<String, Object> pageAndBoards = new HashMap<String,Object>();
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			int total = boardDao.selectBoardCnt(conn);
			int nowPage = page;
			int cntPerPage = 5;
			String url = "/board/board-list";
			Paging pageUtil = new Paging(total, nowPage, cntPerPage, url);
			pageAndBoards.put("paging",pageUtil);
			List<Board> boards = boardDao.selectBoardList(conn,
					(Map<String, Integer>) Map.of("startBoard",pageUtil.getStartBoard(),"lastBoard",pageUtil.getEndBoard()));
			pageAndBoards.put("boards",boards);
		} finally {
			jdbcTemplate.close(conn);
		}
		
		return pageAndBoards;
	}

	public void insertBoard(Board board, List<FileDTO> fileDTOs) {
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			
			boardDao.insertBoard(board, conn);
			if(fileDTOs != null) {
				boardDao.insertFile(fileDTOs, conn);
			}
			
			
			jdbcTemplate.commit(conn);
		} catch (Exception e) {
			jdbcTemplate.rollback(conn);
		} finally {
			jdbcTemplate.close(conn);
		}
		
	}

	public Map<String, Object> selectBoardByBdIdx(Integer bdIdx) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null; 
		
		try {
			conn = jdbcTemplate.getConnection();
			Board board = boardDao.selectBoardByBdIdx(bdIdx, conn);
			List<FileDTO> files = boardDao.selectFileByBdIdx(bdIdx, conn);
			map.put("board", board);
			map.put("files", files);
		} finally {
			jdbcTemplate.close(conn);
		}
		
		return map;
	}

	public void updateBoardByBdIdx(Board board, List<FileDTO> fileDTOsForModify, String[] keepFiles) {
		
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			
			boardDao.updateBoard(board, conn);
			List<FileDTO> prevFiles = boardDao.selectFileByBdIdx(board.getBdIdx(), conn);
			
			//이전 파일 리스트 - 유지 파일 리스트
			List<FileDTO> delFiles = new ArrayList<FileDTO>();
			if(prevFiles != null) {
				for (FileDTO fileDTO : prevFiles) {
					if(keepFiles != null) {
						for (String flIdx : keepFiles) {
							if(fileDTO.getFlIdx().equals(Integer.parseInt(flIdx))) {
								delFiles.add(fileDTO);
							}
						}
					} 
				}
				for (FileDTO delFileDTO : delFiles) {
					if(delFiles != null) {
						for (FileDTO fileDTO : prevFiles) {
							if(fileDTO.getFlIdx().equals(delFileDTO.getFlIdx())) {
								prevFiles.remove(fileDTO);
								break;
							}
						}
					}
				}
				
				for (FileDTO fileDTO : prevFiles) {
					fileDTO.setIsDel(1);
					boardDao.updateFile(fileDTO, conn);
				}
				
			}
			
			
			
			//새로 추가된 이미지 리스트
			if(fileDTOsForModify != null) {
				for (FileDTO fileDTO : fileDTOsForModify) {
					fileDTO.setMemBdIdx(board.getBdIdx());
					boardDao.insertFile(fileDTO, conn);
				}
			}
			
			jdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			jdbcTemplate.rollback(conn);
		} finally {
			jdbcTemplate.close(conn);
		}
		
	}

	public void deleteBoardByBdIdx(Integer bdIdx) {
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			
			boardDao.deleteBoard(bdIdx, conn);
			boardDao.deleteFile(bdIdx, conn);
			
			jdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			jdbcTemplate.rollback(conn);
		} finally {
			jdbcTemplate.close(conn);
		}
		
	}

	public void updateBoardViewsByBdIdx(Integer bdIdx) {
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			
			Board board = boardDao.selectBoardByBdIdx(bdIdx, conn);
			board.setViews(board.getViews()+ 1);
			boardDao.updateBoardViews(board, conn);
			
			jdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			jdbcTemplate.rollback(conn);
		} finally {
			jdbcTemplate.close(conn);
		}
		
	}

}
