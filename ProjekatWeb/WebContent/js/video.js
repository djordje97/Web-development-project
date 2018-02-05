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
	var comments=$('.comments');
	var submitComm=$('#submit-comment');
	var contentComm=$('#textArea');
	var sub=false;
	
	unsub.hide();
	$.get('VideoServlet',{'id':id},function(data){
		console.log(data.video);
			video.attr("src",data.video.videosUrl+"?rel=0&autoplay=1");
			videoName.text(data.video.videoName);
			videoViews.text("Views:"+data.video.numberOfviews);
			likeNumber.text(data.video.numberOfLikes);
			dislikeNumber.text(data.video.numberOfDislikes);
			userName.text(data.video.owner.userName);
			userName.attr('href','User.html?userName='+data.video.owner.userName);
			date.text(data.video.date);
			description.text(data.video.description);
			
			if(data.video.allowComments == true ||(data.user!=null && (data.user.userName == data.video.owner.userName || data.user.role == "ADMIN"))){
				for( i in data.comments){
					comments.append(
							'<div class="comment">'+
							'<p class="user-link">'+
								'<img src="photos/slika.jpg" class="avatar"><a href="User.html?userName='+data.comments[i].owner.userName+'" id="userName">'+data.comments[i].owner.userName+'</a>'+
							'</p>'+
							'<div class="like-dislike">'+
								'<i class="fa fa-thumbs-o-up" style="font-size: 20px;" id="like"></i>'+
								'<p id="like-number" style="font-size: 16px;">202</p>'+
								'<i class="fa fa-thumbs-o-down" style="font-size: 20px;" id="dislike"></i>'+
								'<p id="dislike-number" style="font-size: 16px;">102</p>'+
							'</div>'+
							'<p id="date">'+data.comments[i].date+'</p>'+
							'<p id="comment-content">'+data.comments[i].content+'</p>'+
						'</div>'
							);
				}
			}

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
				if(data.video.allowComments == true){
					addComment.show();
					submitComm.on('click',function(event){
						var content=contentComm.val();
						
					$.post('CommentServlet',{'content':content,'owner':data.user.userName,'video':data.video.id},function(data){
						if(data.status=="success")
							comments.append(
									'<div class="comment">'+
									'<p class="user-link">'+
										'<img src="photos/slika.jpg" class="avatar"><a href="User.html?userName='+data.owner+'" id="userName">'+data.owner+'</a>'+
									'</p>'+
									'<div class="like-dislike">'+
										'<i class="fa fa-thumbs-o-up" style="font-size: 20px;" id="like"></i>'+
										'<p id="like-number" style="font-size: 16px;">202</p>'+
										'<i class="fa fa-thumbs-o-down" style="font-size: 20px;" id="dislike"></i>'+
										'<p id="dislike-number" style="font-size: 16px;">102</p>'+
									'</div>'+
									'<p id="date">'+data.date+'</p>'+
									'<p id="comment-content">'+data.content+'</p>'+
								'</div>'
									);
						 contentComm.value='';
					});
						event.preventDefault();
						return false;
					});
				}
				
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

