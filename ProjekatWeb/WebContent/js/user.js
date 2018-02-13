$(document).ready(function(){
	var userName = window.location.search.slice(1).split('&')[0].split('=')[1];
	var username=$('#username');
	var date=$('#date');
	var description=$('#description');
	var subsNumber=$('#subsNumber');
	var blocked=$('#blocked');
	var role=$('#role');
	var videosInput=$('#videos');
	var subsInput=$('#subscribers');
	var subscribe=$('#subscribe');
	var unsub=$('#unsubscribe');
	var nav=$('.topnav');
	var menu=$('.user-menu');
	var photo=$('#userPhoto');
	var menuButton=$('.menu-button');
	var desc=$('#desc');
	var asc=$('#asc');
	unsub.hide();
	menuButton.hide();
	$.get('UserServlet',{'userName':userName},function(data){
		username.text(data.owner.userName);
		date.text("Registration: "+data.owner.registrationDate);
		description.text(data.owner.channeDescription);
		subsNumber.text("Subscribers: "+data.subNumber);
		var src='photos/'+userName+'.jpg';
		console.log(src);
		photo.attr('src',src);
		if(data.owner.blocked == false){
			blocked.text("Not blocked");
		}
		else
			blocked.text("Blocked");
		role.text("Role: "+data.owner.role);
		
		for(it in data.videos){
			videosInput.append(
					'<div class="column">'+
					'<div class="content">'+
			     	'<p><img src="' +data.videos[it].videoPicture+ '" alt="Video Picture"></p>'+
			      	'<h3><a href="Video.html?id='+data.videos[it].id+'">'+data.videos[it].videoName+'</a></h3>'+
			      	'<p class="user-link"><img src="photos/'+data.videos[it].owner.userName+'.jpg" class="avatar">'+
					'<a href="User.html?userName='+data.videos[it].owner.userName+'">'+data.videos[it].owner.userName+'</a></p>'+
			      	'<p class="date-rating">Date:'+data.videos[it].date+'</p>'+
			      	'<p class="date-rating">views:'+data.videos[it].numberOfviews+'</p>'+
			   		 '</div>'+
					'</div>'
					);
		}
	
		for(i in data.subs){
			subsInput.append(
					'<div class="column">'+
					'<div class="content">'+
						'<p class="user-link">'+
							'<img src="photos/'+data.subs[i].userName+'.jpg" class="avatar" style="width:100px;height:100px;"> <br/> <a style="font-size:24px;margin-left:15px;" href="User.html?userName='+data.subs[i].userName+'">'+data.subs[i].userName+'</a>'+
						'</p>'+
						'<p class="date-rating" style="margin:0;">Subscribers:'+data.subs[i].subsNumber+'</p>'+
					'</div>'+
				'</div>');
		}
		$('#order').on('click',function(event){
			$('.orderVideos').fadeIn();	
			event.preventDefault();
			return false;
		});
	
		$('#orderSubmit').on('click',function(event){
			var column=$('#orderComment').val();
			var ascDesc=asc.val();
			if(desc.is(':checked')){
			var ascDesc=desc.val();
			}
			$.post('VideoServlet',{'status':"order",'column':column,'ascDesc':ascDesc,'userName':userName},function(data){
				if(data.stat == "success"){
					videosInput.empty();
					for(it in data.videos){
						videosInput.append(
								'<div class="column">'+
								'<div class="content">'+
						     	'<p><img src="' +data.videos[it].videoPicture+ '" alt="Video Picture"></p>'+
						      	'<h3><a href="Video.html?id='+data.videos[it].id+'">'+data.videos[it].videoName+'</a></h3>'+
						      	'<p class="user-link"><img src="photos/'+data.videos[it].owner.userName+'.jpg" class="avatar">'+
								'<a href="User.html?userName='+data.videos[it].owner.userName+'">'+data.videos[it].owner.userName+'</a></p>'+
						      	'<p class="date-rating">Date:'+data.videos[it].date+'</p>'+
						      	'<p class="date-rating">views:'+data.videos[it].numberOfviews+'</p>'+
						   		 '</div>'+
								'</div>'
								);
					}
					
					$('.orderVideos').fadeOut();	
				}
			});
			event.preventDefault();
			return false;
		});
		
		if(data.user == null){
			nav.append('<a href="index.html">Home</a>');
			subscribe.on('click',function(event){
				alert("You must sign in first");
				event.preventDefault();
				return false;
			});
		}
		if(data.user!=null){
			nav.append('<a href="LogOutServlet">Sign out</a> <a href="User.html?userName='+data.user.userName+'">My profile</a> <a href="index.html">Home</a>');
			if(data.isSubscribed == "subscribe"){
				unsub.show();
				subscribe.hide();
			}
			if(data.user.role == "ADMIN"){
				nav.append('<a href="Admin.html">Admin page</a>');
			}
			if(data.user.userName == data.owner.userName){
				nav.append( ''+'<a href="AddVideo.html">Add video</a>');
			}
			if(data.user.userName == data.owner.userName || data.user.role== "ADMIN"){
				menuButton.show();
				menu.append('<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>'+
						'<a href="EditUser.html?userName='+data.owner.userName+'">Edit</a>'+'<a href="#" id="delete">Delete</a>')
						
						
						$('#delete').on('click',function(event){
							console.log(userName);
							var x=confirm("Are you shure?");
							if(x){
							$.post('UserServlet',{'userName':userName,'status':"delete"},function(data){
									window.location.replace('LogOutServlet');
							
							});
							}
							event.preventDefault();
							return false;
						});
			}
				
			
			if(data.user.userName != data.owner.userName){
				subscribe.on('click',function(event){
					var subsUp=data.subNumber
					$.post('SubsServlet',{'channel':data.owner.userName,'subscriber':data.user.userName},function(data){
						console.log("stigao odgovor")
						if(data.status == "success"){
							alert("Subscribe success");
							unsub.show();
							subscribe.hide();
							subsNumber.text("Subscribers: "+data.subN);
						}
						
					});
					
					event.preventDefault();
					return false;
				});
				
				unsub.on('click',function(event){
					$.get('SubsServlet',{'channel':data.owner.userName,'subscriber':data.user.userName,},function(data){
						console.log("stigao odgovor")
						if(data.status == "success"){
							alert("Unsubscribe success");
							subscribe.show();
							unsub.hide();
							subsNumber.text("Subscribers: "+data.subN);
						}
						
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
