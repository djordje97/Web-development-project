$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var visibility=$('#visibility');
	var allowComments=$('#allowComments');
	var allowRating=$('#allowRating');
	var block=$('#blockLabel');
	var blocked=$('#block');
	var description=$('#description');
	var submit=$('#submit'); 
	block.hide();
	
	$.get('VideoServlet',{'id':id},function(data){
		
		$('.topnav').append('<a href="User.html?userName='+data.user.userName+'">My profile</a> <a href="index.html">Home</a>');
		if(data.user.role == "ADMIN"){
			block.show();
		}
		if(data.video.visibility == "PUBLIC"){
			visibility.val('public');
		}
		else if(data.video.visibility == "PRIVATE"){
			visibility.val('private');
		}
		else{
			visibility.val('unlisted');
		}
		if(data.video.allowComments == true){
			allowComments.val('true');
		}
		else{
			allowComments.val('false');
		}
		
		if(data.video.allowRating == true){
			allowRating.prop('checked',true);
		}
		else{
			allowRating.prop('checked',false);
		}
		if(data.video.blocked == true){
			blocked.prop('checked',true);
		}
		else{
			blocked.prop('checked',false);
		}
		description.val(data.video.description);
	});
	
	submit.on('click',function(event){
		var visibilityValue=visibility.val();
		var allowCommentsValue=allowComments.val();
		var allowRatingValue=false;
		if(allowRating.is(':checked')){
			allowRatingValue=true;
		}
		var blockValue=false;
		if(blocked.is(':checked')){
			blockValue=true;
		}
		console.log(blockValue);
		var descriptionValue=description.val();
		var params={
				'id':id,
				'visibility':visibilityValue,
				'allowComments':allowCommentsValue,
				'allowRating':allowRatingValue,
				'description':descriptionValue,
				'block':blockValue,
				'status':'edit'
			};
		
		$.post('VideoServlet',params,function(data){
			if(data.status=="success"){
				var location="Video.html?id="+id;
				window.location.replace(location);
			}
		});

		event.preventDefault();
		return false;
	});
});
