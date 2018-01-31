/**
 * 
 */

$(document).ready(function() {
	var userNameInput = $("input[name='userName']");
	var passwordInput = $("input[name='password']");
	var messageParagraph = $('#message');
	var content=$('.row');
	
	$.get('GetVideosServlet',{},function(data){
		console.log("Stigao odgovor");
		for(it in data.videos){
			content.append(
					'<div class="column">'+
					'<div class="content">'+
			     	'<p><img src="' +data.videos[it].videoPicture+ '" alt="Video Picture"></p>'+
			      	'<h4><a href="Video.html?id='+data.videos[it].id+'">'+data.videos[it].videoName+'</a></h4>'+
			      	'<p class="user-link"><img src="photos/slika.jpg" class="avatar">'+
					'<a href="#">'+data.videos[it].owner.userName+'</a></p>'+
			      	'<p class="date-rating">Date:'+data.videos[it].date+'</p>'+
			      	'<p class="date-rating">views:'+data.videos[it].numberOfviews+'</p>'+
			   		 '</div>'+
					'</div>'
					);
		}
	});
	$('#signIn').on('click', function(event) { 
		var userName = userNameInput.val();
		var password = passwordInput.val();
		if(userName=="" || password =="")
			messageParagraph.text("You must enter your username and password fields");
			userNameInput.on('focus',function(event){
			messageParagraph.text("");
			event.preventDefault();
			return false;
		});
		passwordInput.on('focus',function(event){
			messageParagraph.text("");
			event.preventDefault();
			return false;
		});
		console.log('userName: ' + userName);
		console.log('password: ' + password);

		
		$.post('LoginServlet', {'userName': userName, 'password': password}, function(data) { 
			console.log('stigao odgovor');
			console.log(data);

			if (data.status == 'success') {
				window.location.replace('User.html');
			}
			if (data.status == 'failure') {
			messageParagraph.text("You input wrong username or password");
			}
		});
		
		console.log('poslat zahtev');

		event.preventDefault();
		return false;
	});
});

function openNav() {
    document.getElementById("mySidelogin").style.width = "280px";
}
function closeNav() {
    document.getElementById("mySidelogin").style.width = "0";
}