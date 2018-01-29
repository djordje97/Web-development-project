/**
 * 
 */
$(document).ready(function() {
		var userNameInput = $("input[name='userName']");
		var passwordInput = $("input[name='password']");
		var nameInput = $("input[name='name']");
		var surnameInput = $("input[name='surname']");
		var emailInput = $("input[name='email']");
		var messageParagraph  = $('#message');
		
	$('#submit').on('click',function(event){
		var userName=userNameInput.val();
		var password=passwordInput.val();
		var name=nameInput.val();
		var surname=surnameInput.val();
		var email=emailInput.val();
		if(userName=="" || password =="" || email=="")
			{
			messageParagraph.text("You must fill username,email and password fields");
			event.preventDefault();
			return false;
			}
		
		$.post('RegisterServlet', {'userName': userName, 'password': password,'name': name,'surname':surname,'email':email}, function(data) { 
			console.log('stigao odgovor');
			console.log(data);
			
			if (data.status == 'success') {
				window.location.replace('index.html');
			}
			if(data.status=='failure'){
				messageParagraph.text(data.message)
			}
		});
		event.preventDefault();
		return false;
	});
		
	});

function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
} 

function btnCancel() {
	window.location.replace('index.html');
}


	
