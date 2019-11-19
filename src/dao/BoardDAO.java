package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	
	public static BoardDAO getIns() {
		return instance;
	}
	
	public int write(BoardVO data) {
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO boards (title, content, writer, files) VALUES(?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, data.getTitle());
			pstmt.setString(2, data.getContent());
			pstmt.setString(3, data.getWriter());
			pstmt.setString(4, data.getFiles()); //파일은 일단 공백으로
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public List<BoardVO> getList(int page){
		String sql = "SELECT * FROM boards ORDER BY id DESC LIMIT ?, 10";
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO temp = new BoardVO();
				temp.setId(rs.getInt("id"));
				temp.setTitle(rs.getString("title"));
				temp.setWriter(rs.getString("writer"));
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		return list;
		
	}
}






