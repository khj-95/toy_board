package common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	private static JDBCTemplate instance;
	
	public JDBCTemplate() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	public Connection getConnection() {
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "prac";
		String password = "1234";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(PreparedStatement pstm) {
		try {
			if(pstm != null && !pstm.isClosed()) {
				pstm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rset, PreparedStatement pstm) {
		close(rset);
		close(pstm);
	}
	
	public void close(ResultSet rset, Statement stmt) {
		close(rset);
		close(stmt);
	}
	
}
