package model;


public class Video {
	public enum Visibility {
		PUBLIC, PRIVATE, UNLISTED
	}

	private int id;
	private String videosUrl;
	private String videoPicture;
	private String videoName;
	private String description;
	private Visibility visibility;
	private boolean allowComments;
	private int numberOfLikes;
	private int numberOfDislikes;
	private boolean blocked;
	private boolean allowRating;
	private int numberOfviews;
	private String date;
	private User owner;
	private boolean deleted;

	public Video(int id, String videosUrl, String videoPicture, String videoName, String description,
			Visibility visibility, boolean allowComments, int numberOfLikes, int numberOfDislikes, boolean blocked,
			boolean allowRating, int numberOfviews, String date, User owner, boolean deleted) {
		super();
		this.id = id;
		this.videosUrl = videosUrl;
		this.videoPicture = videoPicture;
		this.videoName = videoName;
		this.description = description;
		this.visibility = visibility;
		this.allowComments = allowComments;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
		this.blocked = blocked;
		this.allowRating = allowRating;
		this.numberOfviews = numberOfviews;
		this.date = date;
		this.owner = owner;
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideosUrl() {
		return videosUrl;
	}

	public void setVideosUrl(String videosUrl) {
		this.videosUrl = videosUrl;
	}

	public String getVideoPicture() {
		return videoPicture;
	}

	public void setVideoPicture(String videosPicture) {
		this.videoPicture = videosPicture;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public boolean isAllowComments() {
		return allowComments;
	}

	public void setAllowComments(boolean allowComments) {
		this.allowComments = allowComments;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public int getNumberOfDislikes() {
		return numberOfDislikes;
	}

	public void setNumberOfDislikes(int numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isAllowRating() {
		return allowRating;
	}

	public void setAllowRating(boolean allowRating) {
		this.allowRating = allowRating;
	}

	public int getNumberOfviews() {
		return numberOfviews;
	}

	public void setNumberOfviews(int numberOfviews) {
		this.numberOfviews = numberOfviews;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", videosUrl=" + videosUrl + ", videosPicture=" + videoPicture + ", videoName="
				+ videoName + ", description=" + description + ", visibility=" + visibility + ", allowComments="
				+ allowComments + ", numberOfLikes=" + numberOfLikes + ", numberOfDislikes=" + numberOfDislikes
				+ ", blocked=" + blocked + ", allowViews=" + allowRating + ", numberOfviews=" + numberOfviews + ", date="
				+ date + ", owner=" + owner + ", deleted=" + deleted + "]";
	}

}
