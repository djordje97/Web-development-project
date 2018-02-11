package projekatWeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Comment;
import model.User;
import model.Video;

public class CommentDAO {
	
	public static int getCommentId() {
		Connection conn = ConnectionMenager.getConnection();
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM comment";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
		
			if (rset.next()) {
				id=rset.getInt(1);
				
			}
			id++;
			return id;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return 0;
	}
	
	public static Comment getComment(int id) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM comment WHERE id = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 2;
				String content = rset.getString(index++);
				Date d= rset.getDate(index++);
				String owner = rset.getString(index++);
				int videoId=rset.getInt(index++);
				int likeNumber=rset.getInt(index++);
				int dislikeNumber=rset.getInt(index++);
				boolean deleted=rset.getBoolean(index++);
				User u = UserDAO.get(owner);
				Video video=VideoDAO.getVideo(videoId);
				String commentDate=VideoDAO.dateToString(d);
				return new Comment(id, content, commentDate, u, video,likeNumber,dislikeNumber,deleted);
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}
	
	public static ArrayList<Comment> getComments(int videosId) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Comment> comments = new ArrayList<Comment>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM comment WHERE videoId = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videosId);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String content = rset.getString(index++);
				 Date d= rset.getDate(index++);
				String owner = rset.getString(index++);
				int videoId=rset.getInt(index++);
				int likeNumber=rset.getInt(index++);
				int dislikeNumber=rset.getInt(index++);
				boolean deleted =rset.getBoolean(index++);
				User u = UserDAO.get(owner);
				Video video=VideoDAO.getVideo(videoId);
				String commentDate=VideoDAO.dateToString(d);
				if(u == null || video == null) {
					continue;
				}
				else {
					Comment c=new Comment(id, content, commentDate, u, video,likeNumber,dislikeNumber,deleted);
					comments.add(c);
				}
				
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return comments;
	}
	
	public static boolean addComment(Comment comment) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO comment (content, commentDate, ownerUserName, videoId, likeNumber, dislikeNumber, deleted) VALUES (?, ?, ?, ?, ?, ?, ? )";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, comment.getContent());
			Date myDate=VideoDAO.stringToDateForWrite(comment.getDate());
			java.sql.Date date=new java.sql.Date(myDate.getTime());
			pstmt.setDate(index++, date);
			pstmt.setString(index++, comment.getOwner().getUserName());
			pstmt.setInt(index++, comment.getVideo().getId());
			pstmt.setInt(index++, comment.getLikeNumber());
			pstmt.setInt(index++, comment.getDislikeNumber());
			pstmt.setBoolean(index++, comment.isDeleted());
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return false;
	}

	public static boolean updateComment(Comment comment) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE comment SET likeNumber =?, dislikeNumber = ?, deleted = ?, commentDate = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment.getLikeNumber());
			pstmt.setInt(2, comment.getDislikeNumber());
			pstmt.setBoolean(3, comment.isDeleted());
			Date d =VideoDAO.stringToDateForWrite(comment.getDate());
			java.sql.Date date=new java.sql.Date(d.getTime());
			pstmt.setDate(4, date);
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return false;
	}

}
