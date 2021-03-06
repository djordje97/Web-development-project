$(document).ready(function(){
	var userName = window.location.search.slice(1).split('&')[0].split('=')[1];
	var url=$('#url');
	var name=$('#name');
	var visibility=$('#visibility');
	var allowComments=$('#allowComments');
	var allowRating=$('#allowRating');
	var description=$('#description');
	var message=$('#message');
	var submit=$('#submit');
	var nav=$('.topnav');
	
	nav.append('<a href="User.html?userName='+userName+'">My profile</a> <a href="index.html">Home</a>');
	submit.on('click',function(event){
		var urlValue=url.val();
		var nameValue=name.val();
		var visibilityValue=visibility.val();
		var allowCommentsValue=allowComments.val();
		var allowRatingValue=allowRating.val();
		var descriptionValue=description.val();
		if(urlValue == "" || nameValue == ""){
			message.text("Url and name fields can't be empty");
			return false;
		}
		var params={
				'url':urlValue,
				'name':nameValue,
				'visibility':visibilityValue,
				'allowComments':allowCommentsValue,
				'allowRating':allowRatingValue,
				'description':descriptionValue,
				'status':'add'
			};
		
		$.post('VideoServlet',params,function(data){
			if(data.status=="success"){
				alert("You add video");
			}
		});
		event.preventDefault();
		return false;
	});
	
});