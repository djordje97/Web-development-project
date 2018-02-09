
$(document).ready(function(){
	var userName = window.location.search.slice(1).split('&')[0].split('=')[1];
	var name=$('#name');
	var surname=$('#surname');
	var password=$('#password');
	var description=$('#description');
	var role=$('#role');
	var blocked=$('#blocked');
	var submit=$('#submit'); 
	var roleLabel=$('#roleLabel'); 
	var blockedLabel=$('#blockedLabel'); 
	role.hide();
	blocked.hide();
	roleLabel.hide();
	blockedLabel.hide();
	$.get('UserServlet',{'userName':userName},function(data){
		if(data.user.role == "ADMIN"){
			role.show();
			blocked.show();
			roleLabel.show();
			blockedLabel.show();
		}
		name.val(data.owner.name);
		surname.val(data.owner.surname);
		password.val(data.owner.password);
		description.val(data.owner.channeDescription);
		if(data.owner.role == "USER"){
			role.val('user');
		}
		else{
			role.val('admin');
		}

		if(data.owner.blocked == true){
			blocked.prop('checked',true);
		}
		else{
			blocked.prop('checked',false);
		}
	});
	
	submit.on('click',function(event){
		var nameValue=name.val();
		var surnameValue=surname.val();
		var passwordValue=password.val();
		var descriptionValue=description.val();
		var roleValue=role.val();
		var blockedValue=false;
		if(blocked.is(':checked')){
			blockedValue=true;
		}
		var params={
				'name':nameValue,
				'surname':surnameValue,
				'password':passwordValue,
				'description':descriptionValue,
				'role':roleValue,
				'blocked':blockedValue,
				'status':'edit',
				'userName':userName
			};
		
		$.post('UserServlet',params,function(data){
			if(data.status=="success"){
				var location="User.html?userName="+userName;
				window.location.replace(location);
			}
		});

		event.preventDefault();
		return false;
	});
});

function myFunction() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}