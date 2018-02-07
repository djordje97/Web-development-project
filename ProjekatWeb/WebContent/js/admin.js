
$(document).ready(function(){
	
	var users=$('#users');
	
	$.get('AdminServlet',{},function(data){
		
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
			$.post('AdminServlet',{'userName':userName},function(data){
					var id="#"+userName;
					if(usrName == data.logged.userName){
						$(id).remove();
						$.get('LogOutServlet',{},function(){
							
						});
					}
					$(id).remove();
			});
			
			event.preventDefault();
			return false;
		});
		
	});
	

});