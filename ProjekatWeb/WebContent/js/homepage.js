/**
 * 
 */

$(document).ready(function() {
	var arrow=$('.arrow-up');
	var form=$('.login-form');
	var status=false;
	$('#login').click(function(event) {
		if(status==false){
			arrow.fadeIn();
			form.fadeIn();
			status=true;
		}else{
			arrow.fadeOut();
			form.fadeOut();
			status=false;
		}
	});
	var userNameInput = $('#userNameInput');
	var passwordInput = $('#passwordInput');
	var messageParagraph = $('#message');
	$('#logInSubmit').on('click', function(event) { // izvršava se na klik na dugme
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

		// kontrola toka se račva na 2 grane
		$.post('Login', {'userName': userName, 'password': password}, function(data) { // u posebnoj programskoj niti se šalje (asinhroni) zahtev
			// tek kada stigne odgovor izvršiće se ova anonimna funkcija
			console.log('stigao odgovor');
			console.log(data);

			if (data.status == 'success') {
				window.location.replace('User.html');
			}
		});
		// program se odmah nastavlja dalje, pre nego što stigne odgovor
		console.log('poslat zahtev');

		// zabraniti da browser obavi podrazumevanu akciju pri događaju
		event.preventDefault();
		return false;
	});
});