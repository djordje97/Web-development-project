package projekatWeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.User;
import model.Video;
import model.Video.Visibility;

public class VideoDAO {
	
	public static int getVideoId() {
		Connection conn = ConnectionMenager.getConnection();
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM video";
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

	public static ArrayList<Video> userVideo(String userName) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM video WHERE ownerUserName = ? AND deleted = ? ORDER BY createdDate DESC";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String videoUrl = rset.getString(index++);
				String videoPicture = rset.getString(index++);
				String videoName = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean allowComment = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				boolean Videoblocked = rset.getBoolean(index++);
				boolean allowViews = rset.getBoolean(index++);
				int views = rset.getInt(index++);
				String createDate = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				String user = rset.getString(index++);
				User u = UserDAO.get(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, videoUrl, videoPicture, videoName, description, visibility, allowComment,
						likeNumber, dislikeNumber, Videoblocked, allowViews, views, createDate, u, deleted);
				videos.add(v);
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
		return videos;
	}
	
	public static ArrayList<Video> userPublicVideo(String userName) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM video WHERE visibility = ? AND ownerUserName = ? AND deleted = ? ORDER BY createdDate DESC";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setString(2, userName);
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String videoUrl = rset.getString(index++);
				String videoPicture = rset.getString(index++);
				String videoName = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean allowComment = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				boolean Videoblocked = rset.getBoolean(index++);
				boolean allowViews = rset.getBoolean(index++);
				int views = rset.getInt(index++);
				String createDate = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				String user = rset.getString(index++);
				User u = UserDAO.get(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, videoUrl, videoPicture, videoName, description, visibility, allowComment,
						likeNumber, dislikeNumber, Videoblocked, allowViews, views, createDate, u, deleted);
				videos.add(v);
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
		return videos;
	}
	
	public static ArrayList<Video> OrderAllUserVideo(String userName,String column,String ascDesc) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM video WHERE ownerUserName = ? AND deleted = ? ORDER BY "+column+" "+ascDesc;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String videoUrl = rset.getString(index++);
				String videoPicture = rset.getString(index++);
				String videoName = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean allowComment = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				boolean Videoblocked = rset.getBoolean(index++);
				boolean allowViews = rset.getBoolean(index++);
				int views = rset.getInt(index++);
				String createDate = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				String user = rset.getString(index++);
				User u = UserDAO.get(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, videoUrl, videoPicture, videoName, description, visibility, allowComment,
						likeNumber, dislikeNumber, Videoblocked, allowViews, views, createDate, u, deleted);
				videos.add(v);
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
		return videos;
	}
	
	public static ArrayList<Video> OrderPublicUserVideo(String userName,String column,String ascDesc) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM video WHERE visibility = ? AND ownerUserName = ? AND deleted = ? ORDER BY "+column+" "+ascDesc;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setString(2, userName);
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String videoUrl = rset.getString(index++);
				String videoPicture = rset.getString(index++);
				String videoName = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean allowComment = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				boolean Videoblocked = rset.getBoolean(index++);
				boolean allowViews = rset.getBoolean(index++);
				int views = rset.getInt(index++);
				String createDate = rset.getString(index++);
				boolean deleted = rset.getBoolean(index++);
				String user = rset.getString(index++);
				User u = UserDAO.get(user);
				if(u== null) {
					continue;
				}else {
				Video v = new Video(id, videoUrl, videoPicture, videoName, description, visibility, allowComment,
						likeNumber, dislikeNumber, Videoblocked, allowViews, views, createDate, u, deleted);
				videos.add(v);
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
		return videos;
	}
	
	public static ArrayList<Video> publicVideo() {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM video WHERE visibility = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String videoUrl = rset.getString(index++);
				String videoPicture = rset.getString(index++);
				String videoName = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean allowComment = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				boolean Videoblocked = rset.getBoolean(index++);
				boolean allowViews = rset.getBoolean(index++);
				int views = rset.getInt(index++);
				Date d = rset.getDate(index++);
				boolean deleted = rset.getBoolean(index++);
				String user = rset.getString(index++);
				User u = UserDAO.get(user);
				String createDate=dateToString(d);
				Video v = new Video(id, videoUrl, videoPicture, videoName, description, visibility, allowComment,
						likeNumber, dislikeNumber, Videoblocked, allowViews, views, createDate, u, deleted);
				videos.add(v);
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
		return videos;
	}

	public static ArrayList<Video> allVideo() {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM video WHERE (visibility = ? OR visibility = ?)  AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PUBLIC");
			pstmt.setString(2, "PRIVATE");
			pstmt.setBoolean(3, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String videoUrl = rset.getString(index++);
				String videoPicture = rset.getString(index++);
				String videoName = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean allowComment = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				boolean Videoblocked = rset.getBoolean(index++);
				boolean allowViews = rset.getBoolean(index++);
				int views = rset.getInt(index++);
				Date d = rset.getDate(index++);
				boolean deleted = rset.getBoolean(index++);
				String user = rset.getString(index++);
				User u = UserDAO.get(user);
				String createDate=dateToString(d);
				Video v = new Video(id, videoUrl, videoPicture, videoName, description, visibility, allowComment,
						likeNumber, dislikeNumber, Videoblocked, allowViews, views, createDate, u, deleted);
				videos.add(v);
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
		return videos;
	}
	
	public static Video getVideo(int id) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM video WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int index = 2;
				String videoUrl = rset.getString(index++);
				String videoPicture = rset.getString(index++);
				String videoName = rset.getString(index++);
				String description = rset.getString(index++);
				Visibility visibility = Visibility.valueOf(rset.getString(index++));
				boolean allowComment = rset.getBoolean(index++);
				int likeNumber = rset.getInt(index++);
				int dislikeNumber = rset.getInt(index++);
				boolean Videoblocked = rset.getBoolean(index++);
				boolean allowViews = rset.getBoolean(index++);
				int views = rset.getInt(index++);
				Date d = rset.getDate(index++);
				boolean deleted = rset.getBoolean(index++);
				String user = rset.getString(index++);
				User u = UserDAO.get(user);
				String createDate=dateToString(d);
				Video v = new Video(id, videoUrl, videoPicture, videoName, description, visibility, allowComment,
						likeNumber, dislikeNumber, Videoblocked, allowViews, views, createDate, u, deleted);
				return v;
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

	public static boolean updateVideo(Video video) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE video SET description = ?, visibility = ?, allowComment = ?, allowRating = ?, blocked = ?, deleted = ?,views = ?, likeNumber = ?, dislikeNumber = ?  WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, video.getDescription());
			pstmt.setString(index++, video.getVisibility().toString());
			pstmt.setBoolean(index++, video.isAllowComments());
			pstmt.setBoolean(index++, video.isAllowRating());
			pstmt.setBoolean(index++, video.isBlocked());
			pstmt.setBoolean(index++, video.isDeleted());
			pstmt.setInt(index++, video.getNumberOfviews());
			pstmt.setInt(index++, video.getNumberOfLikes());
			pstmt.setInt(index++, video.getNumberOfDislikes());
			pstmt.setInt(index++, video.getId());

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
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
	public static boolean addVideo(Video video) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO video(videoUrl,videosPicture,videoName,description,visibility,allowComment,likeNumber,dislikeNumber,blocked,allowRating,views,createdDate,deleted,ownerUserName)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, video.getVideosUrl());
			pstmt.setString(index++, video.getVideoPicture());
			pstmt.setString(index++, video.getVideoName());
			pstmt.setString(index++, video.getDescription());
			pstmt.setString(index++, video.getVisibility().toString());
			pstmt.setBoolean(index++, video.isAllowComments());
			pstmt.setInt(index++, video.getNumberOfLikes());
			pstmt.setInt(index++, video.getNumberOfDislikes());
			pstmt.setBoolean(index++, video.isBlocked());
			pstmt.setBoolean(index++, video.isAllowRating());
			pstmt.setInt(index++, video.getNumberOfviews());
			Date myDate=stringToDateForWrite(video.getDate());
			java.sql.Date date=new java.sql.Date(myDate.getTime());
			pstmt.setDate(index++,date);
			pstmt.setBoolean(index++, video.isDeleted());
			pstmt.setString(index++, video.getOwner().getUserName());

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
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

	public static String dateToString(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("dd.MM.yyyy");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}

	public static Date stringToDate(String datum) {

		try {
			DateFormat formatvr = new SimpleDateFormat("dd.MM.yyyy");

			return formatvr.parse(datum);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}
	public static String dateToStringForWrite(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}
	public static Date stringToDateForWrite(String datum) {

		try {
			DateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd");

			return formatvr.parse(datum);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}
	

}
