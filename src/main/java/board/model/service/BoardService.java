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

public class BoardService {

	JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
	BoardDao boardDao = new BoardDao();
	
	public List<Board> selectAllBoardList() {
		List<Board> boardList = new ArrayList<Board>();
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			boardList = boardDao.selectAllBoardList(conn);
		} finally {
			jdbcTemplate.close(conn);
		}
		
		return boardList;
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

}
