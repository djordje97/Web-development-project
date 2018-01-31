package model;


public class LikeDislike {
	private int id;
	private boolean liked;
	private String likeDate;
	private Video likedVideo;
	private Comment likedComment;
	private User owner;
	
	
	public LikeDislike(int id, boolean liked, String likeDate, Video likedVideo, Comment likedComment, User owner) {
		super();
		this.id = id;
		this.liked = liked;
		this.likeDate = likeDate;
		this.likedVideo = likedVideo;
		this.likedComment = likedComment;
		this.owner = owner;
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
	public String getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(String likeDate) {
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
	
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "LikeDislike [id=" + id + ", liked=" + liked + ", likeDate=" + likeDate + ", likedVideo=" + likedVideo
				+ ", likedComment=" + likedComment + ", owner=" + owner + "]";
	}
	
	
	
	
	
	
	

}
