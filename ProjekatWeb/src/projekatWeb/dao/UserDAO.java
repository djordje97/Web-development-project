package projekatWeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;
import model.User.Role;

public class UserDAO {

	public static User get(String userName) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM users WHERE userName = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				String password = rset.getString(index++);
				String name = rset.getString(index++);
				String surname = rset.getString(index++);
				String email = rset.getString(index++);
				String channelDescription = rset.getString(index++);
				Role role = Role.valueOf(rset.getString(index++));
				String registrationDate = rset.getString(index++);
				boolean blocked = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				User u = new User(userName, password, name, surname, email, channelDescription, role, registrationDate,
						blocked, null, null, null,deleted);
				pstmt.close();
				rset.close();

				query = "SELECT * FROM subscribe WHERE masterUser = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userName);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					int index2 = 2;
					String subscriber = rset.getString(index2++);
					u.subscribersUserName.add(subscriber);
				}

				u.setSubscribers(findSubscribers(u.subscribersUserName));
				u.setSubsNumber(getSubsNumber(userName));
				return u;

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
	
	public static ArrayList<User> getAll() {
		Connection conn = ConnectionMenager.getConnection();
		
		ArrayList<User> users=new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM users WHERE deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				String userName = rset.getString(index++);
				String password = rset.getString(index++);
				String name = rset.getString(index++);
				String surname = rset.getString(index++);
				String email = rset.getString(index++);
				String channelDescription = rset.getString(index++);
				Role role = Role.valueOf(rset.getString(index++));
				String registrationDate = rset.getString(index++);
				boolean blocked = rset.getBoolean(index++);
				boolean deleted = rset.getBoolean(index++);
				User u = new User(userName, password, name, surname, email, channelDescription, role, registrationDate,
						blocked, null, null, null,deleted);
				
				u.subscribersUserName=getSubsUserName(userName);
				u.setSubscribers(findSubscribers(u.subscribersUserName));
				u.setSubsNumber(getSubsNumber(userName));
				users.add(u);

			}
			return users;

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
	
	public static ArrayList<String> getSubsUserName(String userName) {
		ArrayList<String> subsUserName=new ArrayList<String>();
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM subscribe WHERE masterUser = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int index2 = 2;
				String subscriber = rset.getString(index2++);
				subsUserName.add(subscriber);
			}

			return subsUserName;
			
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


	public static int getSubsNumber(String userName) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT Count(*) FROM subscribe WHERE masterUser = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				int subs = rset.getInt(index);
				return subs;
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

	public static boolean addSubs(String masterUser, String subs) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO subscribe(masterUser,subscriber) VALUES(?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, masterUser);
			pstmt.setString(2, subs);
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

	public static boolean deleteSubs(String masterUser, String subs) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM subscribe WHERE masterUser=? AND subscriber= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, masterUser);
			pstmt.setString(2, subs);
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

	public static boolean addUser(User user) {
		Connection conn = ConnectionMenager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO users (userName, userPassword, nameu, surname, email, channelDescription, role, registrationDate, blocked,deleted) VALUES (?, ?, ?, ? ,? ,? , ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getUserName());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getName());
			pstmt.setString(index++, user.getSurname());
			pstmt.setString(index++, user.getEmail());
			pstmt.setString(index++, user.getChanneDescription());
			pstmt.setString(index++, user.getRole().toString());
			pstmt.setString(index++, user.getRegistrationDate());
			pstmt.setBoolean(index++, user.isBlocked());
			pstmt.setBoolean(index++, user.isDeleted());
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

	public static ArrayList<User> subscribedOn(String userName) {
		Connection conn = ConnectionMenager.getConnection();
		ArrayList<User> subscribed = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT masterUser FROM subscribe WHERE subscriber = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				String master = rset.getString(index);

				User u = get(master);
				subscribed.add(u);
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
		return subscribed;
	}

	public static int findSubscribed(String userName, String subscriber) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT COUNT(*) FROM subscribe WHERE  masterUser= ? AND subscriber = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, subscriber);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				int subs = rset.getInt(index);
				return subs;

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
	public static boolean updateUser(User user) {
		Connection conn = ConnectionMenager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE users SET channelDescription = ?, nameu = ?, surname = ?, userPassword = ?, blocked = ?, deleted = ?,role = ?  WHERE userName = ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getChanneDescription());
			pstmt.setString(index++, user.getName());
			pstmt.setString(index++, user.getSurname());
			pstmt.setString(index++, user.getPassword());
			pstmt.setBoolean(index++, user.isBlocked());
			pstmt.setBoolean(index++,user.isDeleted());
			pstmt.setString(index++, user.getRole().toString());
			pstmt.setString(index++, user.getUserName());

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

	public static ArrayList<User> findSubscribers(ArrayList<String> subscribersUserName) {
		ArrayList<User> list = new ArrayList<User>();
		if (subscribersUserName.isEmpty()) {
			return null;
		} else {
			for (String userName : subscribersUserName) {
				list.add(get(userName));
			}
			return list;
		}
	}
}
