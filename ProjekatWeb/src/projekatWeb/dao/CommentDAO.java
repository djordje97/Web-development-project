package projekatWeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Comment;
import model.User;
import model.Video;

public class CommentDAO {
	
	public static ArrayList<Comment> getComments(int videosId) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Comment> comments = new ArrayList<Comment>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM comment WHERE videoId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videosId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String content = rset.getString(index++);
				String commentDate = rset.getString(index++);
				String owner = rset.getString(index++);
				User u = UserDAO.get(owner);
				Video video=VideoDAO.getVideo(videosId);
				Comment c=new Comment(id, content, commentDate, u, video);
				comments.add(c);
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
			String query = "INSERT INTO comment (content, commentDate, ownerUserName, videoId) VALUES (?, ?, ?, ? )";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, comment.getContent());
			pstmt.setString(index++, comment.getDate());
			pstmt.setString(index++, comment.getOwner().getUserName());
			pstmt.setInt(index++, comment.getVideo().getId());
		
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
