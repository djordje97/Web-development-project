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
	$.get('UserServlet',{'userName':userName},function(data){
		username.text(data.user.userName);
		date.text("Registration: "+data.user.registrationDate);
		description.text(data.user.channelDescription);
		subsNumber.text("Subscribers: "+data.subNumber);
		if(data.user.blocked == false){
			blocked.text("Not blocked");
		}
		else
			blocked.text("Blocked");
		role.text("Role: "+data.user.role);
		
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
		
	});
});