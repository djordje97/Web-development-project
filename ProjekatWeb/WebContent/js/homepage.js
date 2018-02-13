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
			      	'<h2><a href="Video.html?id='+data.videos[it].id+'">'+data.videos[it].videoName+'</a></h2>'+
			      	'<p class="user-link"><img src="photos/'+data.videos[it].owner.userName+'.jpg" class="avatar">'+
					'<a href="User.html?userName='+data.videos[it].owner.userName+'">'+data.videos[it].owner.userName+'</a></p>'+
			      	'<p class="date-rating">Date:'+data.videos[it].date+'</p>'+
			      	'<p class="date-rating">views:'+data.videos[it].numberOfviews+'</p>'+
			   		 '</div>'+
					'</div>'
					);
		}
		if(data.user !=null){
			$('#signInLink').replaceWith('<a href="LogOutServlet">Sign out</a>');
			$('#home').replaceWith('<a href="User.html?userName='+data.user.userName+'">My profile</a>');
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
				window.location.replace('User.html?userName='+userName);
			}
			if (data.status == 'failure') {
			messageParagraph.text("You input wrong username or password");
			}
		});
		
		console.log('poslat zahtev');

		event.preventDefault();
		return false;
	});
	$('input[type=button]').on('click',function(event){
		var input = $('#search').val().toUpperCase();
		$(".column").each(function(){
			  if($(this).html().toUpperCase().includes(input)){
			    $(this).show();
			  }
			  else{
				$(this).hide();
			  }
		});
	});
});

function openNav() {
    document.getElementById("mySidelogin").style.width = "280px";
}
function closeNav() {
    document.getElementById("mySidelogin").style.width = "0";
}

