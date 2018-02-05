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
	unsub.hide();
	
	$.get('UserServlet',{'userName':userName},function(data){
		username.text(data.owner.userName);
		date.text("Registration: "+data.owner.registrationDate);
		description.text(data.owner.channelDescription);
		subsNumber.text("Subscribers: "+data.subNumber);
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
			      	'<p class="user-link"><img src="photos/slika.jpg" class="avatar">'+
					'<a href="User.html?userName='+data.videos[it].owner.userName+'">'+data.videos[it].owner.userName+'</a></p>'+
			      	'<p class="date-rating">Date:'+data.videos[it].date+'</p>'+
			      	'<p class="date-rating">views:'+data.videos[it].numberOfviews+'</p>'+
			   		 '</div>'+
					'</div>'
					);
		}
	
		for(i in data.subs){
			subsInput.append(
					'<div class="column2">'+
					'<div class="content">'+
						'<p class="user-link">'+
							'<img src="photos/slika.jpg" class="avatar"> <a href="User.html?userName='+data.subs[i].userName+'">'+data.subs[i].userName+'</a>'+
						'</p>'+
						'<p class="date-rating">Subscribers:'+data.subs[i].subsNumber+'</p>'+
					'</div>'+
				'</div>');
		}
		
		if(data.user == null){
			subscribe.on('click',function(event){
				alert("You must sign in first");
				event.preventDefault();
				return false;
			});
		}
		if(data.user!=null){
			if(data.isSubscribed == "subscribe"){
				unsub.show();
				subscribe.hide();
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
