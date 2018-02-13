
$(document).ready(function(){
	
	var users=$('#users');
	var desc=$('#desc');
	var asc=$('#asc');
	
	$.get('AdminServlet',{},function(data){
		$('.topnav').append(' <a href="LogOutServlet">Sign out</a> <a href="User.html?userName='+data.logged.userName+'">My profile</a> <a href="index.html">Home</a>'+
		'<div class="search-container">'+
	    '<form>'+
	      '<input type="text" placeholder="Search users..." onkeyup="myFunction()" id="search" name="search">'+
	    '</form>'+
	  '</div>');
		for(i in data.users){
			users.append('<div class="column" id="'+data.users[i].userName+'">'+
			'<div class="card">'+
			'<img src="photos/slika.jpg" alt="User">'+
			'<div class="container-info">'+
				'<h2 id="username">'+data.users[i].userName+'</h2>'+
				'<p id="name">'+data.users[i].name+'</p>'+
				'<p id="surname">'+data.users[i].surname+'</p>'+
				'<p id="email">'+data.users[i].email+'</p>'+
				'<p id="role">'+data.users[i].role+'</p>'+
				'<p id="sub">'+
					'<button id="delete" value="'+data.users[i].userName+'">Delete</button>'+
				'</p>'+
			'</div>'+
		'</div>'+
	'</div>')
		}
		
		$('button').on('click',function(event){
			var userName=$(this).val();
			console.log(userName);
			var x=confirm("Are you shure?");
			if(x){
			$.post('AdminServlet',{'userName':userName,'status':"delete",},function(data){
					var id="#"+userName;
					if(usrName == data.logged.userName){
						$(id).remove();
						$.get('LogOutServlet',{},function(){
							
						});
					}
					$(id).remove();
			});
			}
			event.preventDefault();
			return false;
		});
		
	});
	
	$('#order').on('click',function(event){
		var column=$('#orderComment').val();
		var ascDesc=asc.val();
		if(desc.is(':checked')){
			var ascDesc=desc.val();
		}
		$.post('AdminServlet',{'status':"order",'ascDesc':ascDesc,'column':column},function(data){
			if(data.stat=="success"){
				users.empty();
				for(i in data.users){
					users.append('<div class="column" id="'+data.users[i].userName+'">'+
					'<div class="card">'+
					'<img src="photos/slika.jpg" alt="User">'+
					'<div class="container-info">'+
						'<h2 id="username">'+data.users[i].userName+'</h2>'+
						'<p id="name">'+data.users[i].name+'</p>'+
						'<p id="surname">'+data.users[i].surname+'</p>'+
						'<p id="email">'+data.users[i].email+'</p>'+
						'<p id="role">'+data.users[i].role+'</p>'+
						'<p id="sub">'+
							'<button id="delete" value="'+data.users[i].userName+'">Delete</button>'+
						'</p>'+
					'</div>'+
				'</div>'+
			'</div>')
				}
			}
		});
		console.log(column);
		console.log(ascDesc)
		event.preventDefault();
		return false;
	});
	

});

function myFunction() {
	var input = $('#search').val().toUpperCase();
	$(".column").each(function(){
		  if($(this).html().toUpperCase().includes(input)){
		    $(this).show();
		  }
		  else{
			$(this).hide();
		  }
	});
}