package model;


import java.util.ArrayList;

public class User {
	public enum Role {USER, ADMIN};
	
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String channeDescription;
	private Role role;
	private String registrationDate;
	private boolean blocked;
	private ArrayList<User> subscribers;
	private ArrayList<LikeDislike> likedVideos;
	private ArrayList<LikeDislike> likedComments;
	private boolean deleted;
	public int subsNumber;
	public ArrayList<String> subscribersUserName=new ArrayList<String>();
	

	public User(String userName, String password, String name, String surname, String email, String channeDescription,
			Role role, String registrationDate, boolean blocked, ArrayList<User> subscribers,
			ArrayList<LikeDislike> likedVideos, ArrayList<LikeDislike> likedComments, boolean deleted) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.channeDescription = channeDescription;
		this.role = role;
		this.registrationDate = registrationDate;
		this.blocked = blocked;
		this.subscribers = subscribers;
		this.likedVideos = likedVideos;
		this.likedComments = likedComments;
		this.deleted = deleted;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getChanneDescription() {
		return channeDescription;
	}


	public void setChanneDescription(String channeDescription) {
		this.channeDescription = channeDescription;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public int getSubsNumber() {
		return subsNumber;
	}



	public void setSubsNumber(int subsNumber) {
		this.subsNumber = subsNumber;
	}



	public ArrayList<String> getSubscribersUserName() {
		return subscribersUserName;
	}



	public void setSubscribersUserName(ArrayList<String> subscribersUserName) {
		this.subscribersUserName = subscribersUserName;
	}



	public String getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}


	public boolean isBlocked() {
		return blocked;
	}


	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}


	public ArrayList<User> getSubscribers() {
		return subscribers;
	}


	public void setSubscribers(ArrayList<User> subscribers) {
		this.subscribers = subscribers;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public ArrayList<LikeDislike> getLikedVideos() {
		return likedVideos;
	}


	public void setLikedVideos(ArrayList<LikeDislike> likedVideos) {
		this.likedVideos = likedVideos;
	}


	public ArrayList<LikeDislike> getLikedComments() {
		return likedComments;
	}


	public void setLikedComments(ArrayList<LikeDislike> likedComments) {
		this.likedComments = likedComments;
	}

	


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", channeDescription=" + channeDescription + ", role=" + role
				+ ", registrationDate=" + registrationDate + ", blocked=" + blocked + ", subscribers=" + subscribers
				+ ", likedVideos=" + likedVideos + ", likedComments=" + likedComments + ", deleted=" + deleted
				+ ", subsNumber=" + subsNumber + ", subscribersUserName=" + subscribersUserName + "]";
	}


	

	


}
