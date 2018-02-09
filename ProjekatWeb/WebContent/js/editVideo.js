$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var visibility=$('#visibility');
	var allowComments=$('#allowComments');
	var allowRating=$('#allowRating');
	var description=$('#description');
	var submit=$('#submit'); 
	
	$.get('VideoServlet',{'id':id},function(data){
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
		description.val(data.video.description);
	});
	
	submit.on('click',function(event){
		var visibilityValue=visibility.val();
		var allowCommentsValue=allowComments.val();
		var allowRatingValue=false;
		if(allowRating.is(':checked')){
			allowRatingValue=true;
		}
		var descriptionValue=description.val();
		var params={
				'visibility':visibilityValue,
				'allowComments':allowCommentsValue,
				'allowRating':allowRatingValue,
				'description':descriptionValue,
				'status':'edit',
				'videoId':id
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
