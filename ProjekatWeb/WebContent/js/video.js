/**
 * 
 */

$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var video=$('#video');
	var videoName=$('#videoName');
	var videoViews=$('#videoViews');
	var likeNumber=$('#like-number');
	var dislikeNumber=$('#dislike-number');
	var userName=$('#userName');
	var date=$('#date');
	var description=$('#description');
	$.get('VideoServlet',{'id':id},function(data){
		console.log(data.video);
		if(data.status=="success"){
			video.attr("src",data.video.videosUrl+"?rel=0&autoplay=1");
			videoName.text(data.video.videoName);
			videoViews.text("Views:"+data.video.numberOfviews);
			likeNumber.text(data.video.numberOfLikes);
			dislikeNumber.text(data.video.numberOfDislikes);
			userName.text(data.video.owner.userName);
			date.text(data.video.date);
			description.text(data.video.description);
		}
	});
});
	
function openNav() {
    document.getElementById("user-menu").style.width = "230px";
}
function closeNav() {
    document.getElementById("user-menu").style.width = "0";
}
function setURL(url){
    document.getElementById('video').src = url;
}