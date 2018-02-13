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
		
		var userName=userNameInput.val();
		var password=passwordInput.val();
		var name=nameInput.val();
		var surname=surnameInput.val();
		var email=emailInput.val();
		
		var photo = new FormData($("input[name='file']"));

		jQuery.each($('input[name^="media"]')[0].files, function(i, file) {
		    form_data.append(i, file);
		});
		
		var data={'userName':userName,'password':password,'name':name,'surname':surname,'email':email,
			'photo':photo};
		$('#submit').on('click',function(event){
			
			iQuery.ajax({
			    type: 'POST',
			    cache: false,
			    processData: false,
			    contentType: false,
			    data: data,
			    url: 'RegisterServlet',

			    success: function(data) {
			        	
			        
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


	
