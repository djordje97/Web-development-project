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
	var subscribe=$('#subscribe');
	var addComment=$('.add-comment');
	var menu=$('.menu-button');
	var userMenu=$('.user-menu');
	var unsub=$('#unsubscribe');
	var sub=false;
	
	unsub.hide();
	showVideo();
	function showVideo(){
	$.get('VideoServlet',{'id':id},function(data){
		console.log(data.video);
			video.attr("src",data.video.videosUrl+"?rel=0&autoplay=1");
			videoName.text(data.video.videoName);
			videoViews.text("Views:"+data.video.numberOfviews);
			likeNumber.text(data.video.numberOfLikes);
			dislikeNumber.text(data.video.numberOfDislikes);
			userName.text(data.video.owner.userName);
			date.text(data.video.date);
			description.text(data.video.description);
			

			if (data.status == "visiter"){
			addComment.hide();
				menu.hide();
			}
			if(data.user == null){
				subscribe.on('click',function(event){
					alert("You must sign in first");
					event.preventDefault();
					return false;
				});
			}
			if(data.user!=null){
				addComment.show();
				menu.hide();
				if(data.isSubscribed == "subscribe"){
					unsub.show();
					subscribe.hide();
				}
				if(data.user.userName != data.video.owner.userName){
					subscribe.on('click',function(event){
						
						$.post('SubsServlet',{'channel':data.video.owner.userName,'subscriber':data.user.userName},function(data){
							console.log("stigao odgovor")
							if(data.status == "success"){
								alert("Subscribe success");
								unsub.show();
								subscribe.hide();
							}
							
						});
						
						event.preventDefault();
						return false;
					});
					
					unsub.on('click',function(event){
						$.get('SubsServlet',{'channel':data.video.owner.userName,'subscriber':data.user.userName,},function(data){
							console.log("stigao odgovor")
							if(data.status == "success"){
								alert("Unsubscribe success");
								subscribe.show();
								unsub.hide();
							}
							
						});
						event.preventDefault();
						return false;
					});
				}
			}
		if(data.user.userName == data.video.owner.userName){
			menu.show();
			userMenu.append(
					'<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>'+
					'<a href="#">Edit</a> <a href="#">Delete</a>'
					);
		}
		if(data.user.role == 'ADMIN'){
			userMenu.append(
					'<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>'+
					'<a href="#">Block</a> <a href="#">Delete</a>'
					);
		}
		
		
});
	}
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

