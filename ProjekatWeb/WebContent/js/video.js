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
	var desc=$('#desc');
	var asc=$('#asc');
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
			
				for( i in data.comments){
					if(data.comments[i].owner != null){
					comments.append(
							'<div class="comment" id="'+data.comments[i].id+'">'+
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
							'<input type="button"  id="editComment" class="'+data.comments[i].owner.userName+'" name="'+data.comments[i].id+'" value="Edit">'+
							'<input type="button"  id="deleteComment" class="'+data.comments[i].owner.userName+'" name="'+data.comments[i].id+'" value="Delete">'+
						'</div>'
							);
					}
					if(data.user!=null){
					if(data.user.role == "ADMIN"){
						continue;
					}
					if(data.comments[i].owner.userName != data.user.userName){
					var userN='input[type=button]'+'.'+data.comments[i].owner.userName;
					console.log(userN);
					$(userN).hide();
					}
					
					}else{
						$('input[type=button]').hide();
					}
				}
			
				$('#order').on('click',function(event){
					var column=$('#orderComment').val();
					var ascDesc=asc.val();
					if(desc.is(':checked')){
						var ascDesc=desc.val();
					}
					console.log(ascDesc);
					$.get('CommentServlet',{'id':id,'ascDesc':ascDesc,'column':column},function(data){
						if(data.status == "success"){
							comments.empty();
							for( i in data.comments){
								if(data.comments[i].owner != null){
								comments.append(
										'<div class="comment" id="'+data.comments[i].id+'">'+
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
										'<input type="button"  id="editComment" class="'+data.comments[i].owner.userName+'" name="'+data.comments[i].id+'" value="Edit">'+
										'<input type="button"  id="deleteComment" class="'+data.comments[i].owner.userName+'" name="'+data.comments[i].id+'" value="Delete">'+
									'</div>'
										);
								}
								if(data.user!=null){
								if(data.user.role == "ADMIN"){
									continue;
								}
								if(data.comments[i].owner.userName != data.user.userName){
								var userN='input[type=button]'+'.'+data.comments[i].owner.userName;
								console.log(userN);
								$(userN).hide();
								}
								
								}else{
									$('input[type=button]').hide();
								}
							}
						}
					});
					event.preventDefault();
					return false;
				});
				
				if(data.video.allowComments == false){
					addComment.hide();
					comments.hide();
				}
				if(data.video.allowRating == false){
					likeNumber.hide();
					dislikeNumber.hide();
					likeVideo.hide();
					dislikeVideo.hide();
				}
				
				if (data.status == "visiter" || data.user.blocked == true){
					addComment.hide();
					menu.hide();
					nav.append('<a href="index.html">Home</a>'
					);
					subscribe.show();
				}
				if(data.user!= null){
					
					if(data.user.userName == data.video.owner.userName || data.user.role == "ADMIN"){
						comments.show();
						likeNumber.show();
						dislikeNumber.show();
						likeVideo.show();
						dislikeVideo.show();
					}
					nav.append(' <a href="LogOutServlet">Sign out</a> <a href="User.html?userName='+data.user.userName+'">My profile</a> <a href="index.html">Home</a>');
					if(data.video.allowComments == true && data.user.blocked == false){
						addComment.show();
						submitComm.on('click',function(event){
							var content=contentComm.val();
							console.log(data.video.id);
						$.post('CommentServlet',{'content':content,'owner':data.user.userName,'video':data.video.id,'status': "add"},function(data){
							if(data.status=="success")
								comments.append(
										'<div class="comment" id="'+data.id+'">'+
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
										'<input type="button"  id="editComment" name="'+data.id+'" value="Edit">'+
										'<input type="button"  id="deleteComment" name="'+data.id+'" value="Delete">'+

									'</div>'
										);
						});
							event.preventDefault();
							return false;
						});
					}
					if(data.user.blocked == false){
						$('input[type=button]#deleteComment').on('click',function(event){
							
							var id=$(this).attr('name');
							console.log(id);
							$.post('CommentServlet',{'id':id,'status':"delete"},function(data){
								if(data.stat == "success"){
									var forRemove='.comment#'+id;
									$(forRemove).remove();
								}
							});
							
							event.preventDefault();
							return false;
						});
						
						$('input[type=button]#editComment').on('click',function(event){
							
							var id=$(this).attr('name');
							console.log(id);
							var select='#'+id+' #comment-content';
							var dateSelect='#'+id+' #date';
							console.log(select);
							var oldContent=$(select).text();
							console.log(oldContent);
							$('#textAreaUpdate').val(oldContent);
							$('.editCommentDiv').fadeIn();
							
							$('.editCommentDiv #editCommentSubmit').on('click',function(event){
								var content=$('#textAreaUpdate').val();
								$.post('CommentServlet',{'id':id,'status':"edit",'content':content},function(data){
									if(data.stat == "success"){
										var oldContent=$(select).text(content);
										var oldDate=$(dateSelect).text(data.newDate);
										$('.editCommentDiv').fadeOut();
									}
								});
								
								event.preventDefault();
								return false;
							});
							
							$('.editCommentDiv #cancel').on('click',function(event){
								$('.editCommentDiv').fadeOut();
								
								event.preventDefault();
								return false;
							});
							event.preventDefault();
							return false;
						});
						
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
								'<a href="EditVideo.html?id='+data.video.id+'">Edit</a>'+ '<a href="#" id="delete">Delete</a>'
								);
						$('#delete').on('click',function(event){
							console.log(id);
							$.post('VideoServlet',{'videoId':id,'status':"delete"},function(data){
									window.location.replace('index.html');
							
							});
							
							event.preventDefault();
							return false;
						});
					}
					
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

