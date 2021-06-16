package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

import dto.BoardDTO;
import dto.ReplyDTO;
import oracle.jdbc.driver.DBConversion;

public class BoardDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private static DataSource dataSource;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	/* 1. 접속 해제 */
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) { con.close(); }
			if (ps != null) { ps.close(); }
			if (rs != null) { rs.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 게시글 목록 조회 */
	public List<BoardDTO> selectList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, AUTHOR, TITLE, CONTENT, HIT, IP, POSTDATE FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getLong(1));
				dto.setAuthor(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setIp(rs.getString(6));
				dto.setPostdate(rs.getDate(7));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	/* 게시글 count */
	public int countBoard() {
		int count = 0;
		try {
			con = dataSource.getConnection();
			sql = "SELECT COUNT(NO) FROM BOARD ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return count;
	}
	
	/* 게시글 작성 */
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAuthor());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getIp());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	
	/* 게시글 보기 */
	public BoardDTO selectBoardByNo(long no) {
		BoardDTO dto = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, AUTHOR, TITLE, CONTENT, HIT, IP, POSTDATE"
			   + " FROM BOARD"
			   + " WHERE NO = ? ";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setNo(rs.getLong(1));
				dto.setAuthor(rs.getNString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setIp(rs.getString(6));
				dto.setPostdate(rs.getDate(7));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return dto;
	}
	
	/* 조회수 증가 */
	public void updateHit(long no) {
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	/* 댓글 작성 */
	public int insertReply(ReplyDTO Re_dto) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO REPLY VALUES (REPLY_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, Re_dto.getAuthor());
			ps.setString(2, Re_dto.getContent());
			ps.setString(3, Re_dto.getIp());
			ps.setLong(4, Re_dto.getBoard_no());
			result = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
	/* 댓글조회 */
	public ReplyDTO selectReplyByNo(long no) {
		ReplyDTO Re_dto = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, AUTHOR, CONTENT, IP, BOARD_NO, POSTDATE FROM REPLY WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				Re_dto = new ReplyDTO();
				Re_dto.setNo(rs.getLong(1));
				Re_dto.setAuthor(rs.getString(2));
				Re_dto.setContent(rs.getString(3));
				Re_dto.setIp(rs.getString(4));
				Re_dto.setBoard_no(rs.getLong(5));
				Re_dto.setPostdate(rs.getDate(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return Re_dto;
	}
	
	/* 게시글 삭제 */
	public int deleteBoard(long no) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return result;
	}
	
	/* topHit */
	public BoardDTO topHit() {
		BoardDTO dto = null;
		try {
			con = dataSource.getConnection();
			sql = "COUNT()";
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, null);
		}
		return dto;
	}
}