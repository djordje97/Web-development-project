package model;

import java.sql.Date;
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
	private Date registrationDate;
	private boolean blocked;
	private ArrayList<User> subscribers;
	private ArrayList<LikeDislike> likedVideos;
	private ArrayList<LikeDislike> likedComment;
	
	
	public User(String username, String password, String name, String surname, String email, String channeDescription,
			Role role, Date registrationDate, boolean blocked, ArrayList<User> subscribers,
			ArrayList<LikeDislike> likedVideos, ArrayList<LikeDislike> likedComment) {
		super();
		this.userName = username;
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
		this.likedComment = likedComment;
	}


	public String getUsername() {
		return userName;
	}


	public void setUsername(String username) {
		this.userName = username;
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


	public Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Date registrationDate) {
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


	public ArrayList<LikeDislike> getLikedVideos() {
		return likedVideos;
	}


	public void setLikedVideos(ArrayList<LikeDislike> likedVideos) {
		this.likedVideos = likedVideos;
	}


	public ArrayList<LikeDislike> getLikedComment() {
		return likedComment;
	}


	public void setLikedComment(ArrayList<LikeDislike> likedComment) {
		this.likedComment = likedComment;
	}


	@Override
	public String toString() {
		return "User [username=" + userName + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", channeDescription=" + channeDescription + ", role=" + role
				+ ", registrationDate=" + registrationDate + ", blocked=" + blocked + ", subscribers=" + subscribers
				+ ", likedVideos=" + likedVideos + ", likedComment=" + likedComment + "]";
	}



}
