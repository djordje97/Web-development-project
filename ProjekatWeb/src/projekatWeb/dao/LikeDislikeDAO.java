package projekatWeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.LikeDislike;


public class LikeDislikeDAO {
	
	
	public static int getLikeId() {
		Connection conn = ConnectionMenager.getConnection();
		int id=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM likeDislike";
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
	
	public static LikeDislike getVideosLikeByOwner(int videoId,String ownerUserName) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM likeDislikevideo JOIN likeDislike on likeDislikevideo.likeId = likeDislike.id WHERE likeDislikeVideo.videoId = ? AND likeDislike.ownerUserName = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videoId);
			pstmt.setString(2, ownerUserName);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				int videosId=rset.getInt(index++);
				int likeId=rset.getInt(index++);
				boolean isLike=rset.getBoolean(index++);
				Date d=rset.getDate(index++);
				String owner=rset.getString(index++);
				String date=VideoDAO.dateToString(d);
				return  new LikeDislike(likeId, isLike, date, VideoDAO.getVideo(videosId), null, UserDAO.get(owner));
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
	
	public static LikeDislike getCommentLikeByOwner(int commentId,String ownerUserName) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM likeDislikeComment JOIN likeDislike on likeDislikeComment.likeId = likeDislike.id WHERE likeDislikeComment.commentId = ? AND likeDislike.ownerUserName = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentId);
			pstmt.setString(2, ownerUserName);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				int videosId=rset.getInt(index++);
				int likeId=rset.getInt(index++);
				boolean isLike=rset.getBoolean(index++);
				Date d =rset.getDate(index++);
				String owner=rset.getString(index++);
				String date=VideoDAO.dateToString(d);
				return  new LikeDislike(likeId, isLike, date, VideoDAO.getVideo(videosId), null, UserDAO.get(owner));
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
	
	
	public static int getVideosLikeNumber(int videoId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM likeDislikeVideo JOIN likeDislike on likeDislikeVideo.likeId = likeDislike.id WHERE liked= ? AND likeDislikeVideo.videoId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, videoId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
					int likeNumber =rset.getInt(1);
					
					return likeNumber;
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
		return 0;
	}
	public static int getCommentLikeNumber(int commentId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM likeDislikeComment JOIN likeDislike on likeDislikeComment.likeId = likeDislike.id WHERE liked= ? AND likeDislikeComment.commentId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, commentId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
					int likeNumber =rset.getInt(1);
					
					return likeNumber;
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
		return 0;
	}
	
	public static boolean addLikeDislike(LikeDislike likeDislike) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO likeDislike(liked,likeDate,ownerUserName) VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, likeDislike.isLiked());
			Date myDate=VideoDAO.stringToDateForWrite(likeDislike.getLikeDate());
			java.sql.Date date=new java.sql.Date(myDate.getTime());
			pstmt.setDate(2, date);
			pstmt.setString(3, likeDislike.getOwner().getUserName());
			
			return pstmt.executeUpdate() == 1;


		}  catch (Exception ex) {
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
	
	public static boolean addVideoLikeDislike(int likeId, int videoId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query="INSERT INTO likeDislikeVideo(likeId,videoId) VALUES(?, ?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, likeId);
			pstmt.setInt(2, videoId);
			 return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
			try {conn.rollback();} catch (SQLException ex1) {ex1.printStackTrace();}
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}

			try {conn.setAutoCommit(true);} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	public static boolean addCommentLikeDislike(int likeId, int commentId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query="INSERT INTO likeDislikeComment(likeId,commentId) VALUES(?, ?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, likeId);
			pstmt.setInt(2, commentId);
			 return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
			try {conn.rollback();} catch (SQLException ex1) {ex1.printStackTrace();}
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}

			try {conn.setAutoCommit(true);} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	public static boolean updateLike(LikeDislike l) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE likeDislike SET liked = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, l.isLiked());
			pstmt.setInt(2, l.getId());
			
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
	
	public static int getVideosDislikeNumber(int videoId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM likeDislikeVideo JOIN likeDislike on likeDislikeVideo.likeId = likeDislike.id WHERE liked= ? AND likeDislikeVideo.videoId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, videoId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
					int likeNumber =rset.getInt(1);
					
					return likeNumber;
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
		return 0;
	}

	
	public static int getCommentDislikeNumber(int commentId) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM likeDislikeComment JOIN likeDislike on likeDislikeComment.likeId = likeDislike.id WHERE liked= ? AND likeDislikeComment.commentId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, commentId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
					int likeNumber =rset.getInt(1);
					
					return likeNumber;
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
		return 0;
	}

}
