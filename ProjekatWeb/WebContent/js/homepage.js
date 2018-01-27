/**
 * 
 */

$(document).ready(function() {
	var userNameInput = $("input[name='userName']");
	var passwordInput = $("input[name='password']");
	var messageParagraph = $('#message');
	$('#signIn').on('click', function(event) { 
		var userName = userNameInput.val();
		var password = passwordInput.val();
		if(userName=="" || password =="")
			messageParagraph.text("You must enter your username and password");
		userNameInput.on('focus',function(){
			messageParagraph.text("");
			event.preventDefault();
			return false;
		});
		passwordInput.on('focus',function(){
			messageParagraph.text("");
			event.preventDefault();
			return false;
		});
		console.log('userName: ' + userName);
		console.log('password: ' + password);

		
		$.post('Login', {'userName': userName, 'password': password}, function(data) { 
			console.log('stigao odgovor');
			console.log(data);

			if (data.status == 'success') {
				window.location.replace('User.html');
			}
		});
		
		console.log('poslat zahtev');

		event.preventDefault();
		return false;
	});
});
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.margiLeft = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.body.style.backgroundColor = "white";
}