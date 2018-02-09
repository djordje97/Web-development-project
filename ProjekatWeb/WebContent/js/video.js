/**
 * 
 */

$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var video=$('#video');
	var videoName=$('#videoName');
	var videoViews=$('#videoViews');
	var likeNumber=$('#like-video-number');
	var dislikeNumber=$('#dislike-video-number');
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
	var likeVideo=$('#likeVideo');
	var dislikeVideo=$('#dislikeVideo');
	var commentLikeNumber=$('#like-number');
	var commentDislikeNumber=$('#dislike-number');
	var nav=$('.topnav');
	var sub=false;
	menu.hide();
	
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
								'<button  id="commentLikeButton" name="like" value="'+data.comments[i].id+'"><i class="fa fa-thumbs-o-up" style="font-size: 20px; color:green;" id="like"></i></button>'+
								'<p id="like-number" class="'+data.comments[i].id+'" style="font-size: 16px;">'+data.comments[i].likeNumber+'</p>'+
								'<button id="commentLikeButton" name="dislike" value="'+data.comments[i].id+'"><i class="fa fa-thumbs-o-down" style="font-size: 20px; color:red;" id="dislike"></i></button>'+
								'<p id="dislike-number" class="'+data.comments[i].id+'" style="font-size: 16px;" commentId="'+data.id+'">'+data.comments[i].dislikeNumber+'</p>'+
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
				nav.append('<a href="index.html">Home</a>');
			}
			
			if(data.video.allowComments == true && data.user!=null){
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
									'<button id="commentLikeButton" name="like" value="'+data.id+'"><i class="fa fa-thumbs-o-up" style="font-size: 20px; color:green" id="like"></i></button>'+
									'<p id="like-number" class="'+data.id+'" style="font-size: 16px;" commentId="'+data.id+'">'+data.likeNumber+'</p>'+
									'<button id="commentLikeButton"  name="dislike" value="'+data.id+'"><i class="fa fa-thumbs-o-down" style="font-size: 20px; color:red;" id="dislike"></i></button>'+
									'<p id="dislike-number" class="'+data.id+'" style="font-size: 16px;" commentId="'+data.id+'">'+data.dislikeNumber+'</p>'+
								'</div>'+
								'<p id="date">'+data.date+'</p>'+
								'<p id="comment-content">'+data.content+'</p>'+

							'</div>'
								);
				});
					event.preventDefault();
					return false;
				});
			}
			if(data.user!=null){
				
				nav.append(' <a href="LogOutServlet">Sign out</a> <a href="User.html?userName='+data.user.userName+'">My profile</a> <a href="index.html">Home</a>'
						);
				likeVideo.on('click',function(event){
					
				$.get('LikeDislikeVideoServlet',{'id':data.video.id},function(data){
						likeNumber.text(data.likeNumber);
						dislikeNumber.text(data.dislikeNumber);
						
				});
					
					event.preventDefault();
					return false;
				});
				
				dislikeVideo.on('click',function(event){
					
					$.post('LikeDislikeVideoServlet',{'id':data.video.id},function(data){
							dislikeNumber.text(data.dislikeNumber);
							likeNumber.text(data.likeNumber);
							
					});
						
						event.preventDefault();
						return false;
					});

				$('button').on('click',function(event){
					var name=$(this).attr("name");
					var id=$(this).val();
					console.log(id);
					
					if (name == "like"){
				$.get('LikeDislikeCommentServlet',{'id':id},function(data){
					var getByLike="#like-number."+id;
					var getByDislike="#dislike-number."+id;
					console.log(getByLike);
						$(getByLike).text(data.likeNumber);
						$(getByDislike).text(data.dislikeNumber);
					
				});
					
					event.preventDefault();
					return false;
				
				}else{
				$.post('LikeDislikeCommentServlet',{'id':id},function(data){
					var getByLike="#like-number."+id;
					var getByDislike="#dislike-number."+id;
					$(getByDislike).text(data.dislikeNumber);
					$(getByLike).text(data.likeNumber);
					
					
				});
					

				}
				});
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
					'<a href="EditVideo.html?id='+data.video.id+'">Edit</a>'+'<a href="#">Delete</a>'
					);
		}
		if(data.user.role == 'ADMIN'){
			menu.show();
			userMenu.append(
					'<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>'+
					'<a href="#">Block</a>'+ '<a href="#">Delete</a>'
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

