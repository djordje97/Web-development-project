package model;

import java.util.Date;

public class Comment {
private int id;
private String content;
private Date date;
private User owner;
private Video video;



public Comment(int id, String content, Date date, User owner, Video video) {
	super();
	this.id = id;
	this.content = content;
	this.date = date;
	this.owner = owner;
	this.video = video;
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



public Date getDate() {
	return date;
}

public void setDate(Date date) {
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

@Override
public String toString() {
	return "Comment [id=" + id + ", content=" + content + ", date=" + date + ", owner=" + owner + ", video=" + video
			+ "]";
}





}
