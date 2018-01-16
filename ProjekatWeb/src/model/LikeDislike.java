package model;

import java.sql.Date;

public class LikeDislike {
	private int id;
	private boolean liked;
	private Date likeDate;
	private Video likedVideo;
	
	private Comment likedComment;
	public LikeDislike(int id, boolean liked, Date likeDate, Video likedVideo, Comment likedComment) {
		super();
		this.id = id;
		this.liked = liked;
		this.likeDate = likeDate;
		this.likedVideo = likedVideo;
		this.likedComment = likedComment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	public Video getLikedVideo() {
		return likedVideo;
	}
	public void setLikedVideo(Video likedVideo) {
		this.likedVideo = likedVideo;
	}
	public Comment getLikedComment() {
		return likedComment;
	}
	public void setLikedComment(Comment likedComment) {
		this.likedComment = likedComment;
	}
	
	@Override
	public String toString() {
		return "LikeDislike [id=" + id + ", liked=" + liked + ", likeDate=" + likeDate + ", likedVideo=" + likedVideo
				+ ", likedComment=" + likedComment + "]";
	}
	
	
	
	
	

}
