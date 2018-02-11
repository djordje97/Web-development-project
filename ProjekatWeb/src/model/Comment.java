package model;


public class Comment {
private int id;
private String content;
private String date;
private User owner;
private Video video;
private int likeNumber;
private int dislikeNumber;
private boolean deleted;





public Comment(int id, String content, String date, User owner, Video video, int likeNumber, int dislikeNumber, boolean deleted) {
	super();
	this.id = id;
	this.content = content;
	this.date = date;
	this.owner = owner;
	this.video = video;
	this.likeNumber = likeNumber;
	this.dislikeNumber = dislikeNumber;
	this.deleted=deleted;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}



public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public User getOwner() {
	return owner;
}

public void setOwner(User owner) {
	this.owner = owner;
}

public Video getVideo() {
	return video;
}

public void setVideo(Video video) {
	this.video = video;
}


public int getLikeNumber() {
	return likeNumber;
}

public void setLikeNumber(int likeNumber) {
	this.likeNumber = likeNumber;
}

public int getDislikeNumber() {
	return dislikeNumber;
}

public void setDislikeNumber(int dislikeNumber) {
	this.dislikeNumber = dislikeNumber;
}


public boolean isDeleted() {
	return deleted;
}

public void setDeleted(boolean deleted) {
	this.deleted = deleted;
}

@Override
public String toString() {
	return "Comment [id=" + id + ", content=" + content + ", date=" + date + ", owner=" + owner + ", video=" + video
			+ ", likeNumber=" + likeNumber + ", dislikeNumber=" + dislikeNumber + ", deleted=" + deleted + "]";
}






}
