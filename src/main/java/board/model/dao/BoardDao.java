package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import board.model.dto.Board;
import common.file.FileDTO;
import common.jdbc.JDBCTemplate;

public class BoardDao {
	
	JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

	public List<Board> selectAllBoardList(Connection conn)  {
		List<Board> boardList = new ArrayList<Board>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from board order by reg_date desc";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Board board = new Board();
				board.setBdIdx(rset.getInt("bd_idx"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setViews(rset.getInt("views"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rset, stmt);
		}
		
		return boardList;
		
	}

	public void insertBoard(Board board, Connection conn) {
		PreparedStatement pstm = null;
		String query = "insert into board(bd_idx,writer,title,content) values(sc_bd_idx.nextval, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, board.getWriter());
			pstm.setString(2, board.getTitle());
			pstm.setString(3, board.getContent());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstm);
		}
		
	}

	public void insertFile(List<FileDTO> fileDTOs, Connection conn) {
		PreparedStatement pstm = null;
		String query = "insert into file_info(fl_idx,mem_bd_idx,origin_file_name,rename_file_name,save_path) values(sc_fl_idx.nextval, sc_bd_idx.currval, ?,?,?)";
		
		try {
			pstm = conn.prepareStatement(query);
			for (FileDTO fileDTO : fileDTOs) {
				pstm.setString(1,fileDTO.getOriginFileName());
				pstm.setString(2, fileDTO.getRenameFileName());
				pstm.setString(3, fileDTO.getSavePath());
				pstm.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstm);
		}
		
	}
	
	public void insertFile(FileDTO fileDTO, Connection conn) {
		PreparedStatement pstm = null;
		String query = "insert into file_info(fl_idx,mem_bd_idx,origin_file_name,rename_file_name,save_path) values(sc_fl_idx.nextval, ?, ?,?,?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1,fileDTO.getMemBdIdx());
			pstm.setString(2,fileDTO.getOriginFileName());
			pstm.setString(3, fileDTO.getRenameFileName());
			pstm.setString(4, fileDTO.getSavePath());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstm);
		}
		
	}

	public Board selectBoardByBdIdx(Integer bdIdx, Connection conn) {
		Board board = new Board();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from board where bd_idx = " + bdIdx;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				board.setBdIdx(rset.getInt("bd_idx"));
				board.setWriter(rset.getString("writer"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setViews(rset.getInt("views"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rset, stmt);
		}
		
		return board;
	}

	public List<FileDTO> selectFileByBdIdx(Integer bdIdx, Connection conn) {
		List<FileDTO> files = new ArrayList<FileDTO>();
		
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from file_info where is_del = 0 and mem_bd_idx = " + bdIdx;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				FileDTO file = new FileDTO();
				file.setFlIdx(rset.getInt("fl_idx"));
				file.setMemBdIdx(rset.getInt("mem_bd_idx"));
				file.setOriginFileName(rset.getString("origin_file_name"));
				file.setRenameFileName(rset.getString("rename_file_name"));
				file.setSavePath(rset.getString("save_path"));
				file.setRegDate(rset.getDate("reg_date"));
				files.add(file);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rset, stmt);
		}
		
		return files;
	}
	
	public void updateBoard(Board board, Connection conn) {
		PreparedStatement pstm = null;
		String query = "update board set writer=?,title=?,content=? where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, board.getWriter());
			pstm.setString(2, board.getTitle());
			pstm.setString(3, board.getContent());
			pstm.setInt(4, board.getBdIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstm);
		}
		
	}
	
	public void updateBoardViews(Board board, Connection conn) {
		PreparedStatement pstm = null;
		String query = "update board set views=? where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, board.getViews());
			pstm.setInt(2, board.getBdIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstm);
		}
		
	}
	
	public void updateFile(FileDTO fileDTO, Connection conn) {
		PreparedStatement pstm = null;
		String query = "update file_info set is_del=? where fl_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, fileDTO.getIsDel());
			pstm.setInt(2, fileDTO.getFlIdx());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstm);
		}
		
	}

	public void deleteBoard(Integer bdIdx, Connection conn) {
		PreparedStatement pstm = null;
		String query = "delete from board where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstm);
		}
		
	}
	
	public void deleteFile(Integer bdIdx, Connection conn) {
		PreparedStatement pstm = null;
		String query = "delete from file_info where mem_bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstm);
		}
		
	}

	public int selectBoardCnt(Connection conn) {
		int boardCnt = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from board";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				boardCnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rset, stmt);
		}
		
		return boardCnt;
	}

	public List<Board> selectBoardList(Connection conn, Map<String, Integer> map) {
		List<Board> boardList = new ArrayList<Board>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from (select rownum rnum, board.* from (select b.* from board b order by reg_date desc) board) where rnum between ? and ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, map.get("startBoard"));
			pstm.setInt(2, map.get("lastBoard"));
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setBdIdx(rset.getInt("bd_idx"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setViews(rset.getInt("views"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rset, pstm);
		}
		
		return boardList;
	}

	

	

}
