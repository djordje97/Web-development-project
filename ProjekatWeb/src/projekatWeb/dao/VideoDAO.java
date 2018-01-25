package projekatWeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.User;
import model.Video;
import model.Video.Visibility;

public class VideoDAO {
	
	public static ArrayList<Video> userVideo(String userName){
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video>videos=new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
		String query = "SELECT * FROM video WHERE ownerUserName = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, userName);
		rset = pstmt.executeQuery();
		if (rset.next()) {
			int index = 1;
			int id = rset.getInt(index++);
			String videoUrl=rset.getString(index++);
			String videoPicture=rset.getString(index++);
			String description=rset.getString(index++);
			Visibility visibility=Visibility.valueOf(rset.getString(index++));
			boolean allowComment=rset.getBoolean(index++);
			int likeNumber=rset.getInt(index++);
			int dislikeNumber=rset.getInt(index++);
			boolean Videoblocked=rset.getBoolean(index++);
			int views=rset.getInt(index++);
			Date createDate=rset.getDate(index++);
			boolean deleted=rset.getBoolean(index++);
			String user=rset.getString(index++);
			User u =UserDAO.get(user);
			Video v= new Video(id, videoUrl, videoPicture, description, visibility, allowComment, likeNumber, dislikeNumber, Videoblocked, views, createDate, u, deleted);
			videos.add(v);
		}
		
		}catch (Exception ex) {
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
		return videos;
	}

}