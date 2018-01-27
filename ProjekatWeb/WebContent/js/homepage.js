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
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}