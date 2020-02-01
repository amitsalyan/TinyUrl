const genTinyLinkUrl = 'api/tiny/genTinyLink'

$(document).ready(function(){
	$('#submitButton').click(function(){
		$('#invalidUrl').empty();
		let genTinyLink = {};
		genTinyLink['url'] = $('input').val();
		console.log('genTinyLink'+genTinyLink)
		$.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			'type' : 'POST',
			'url' : genTinyLinkUrl,
			'data' : JSON.stringify(genTinyLink),
			'dataType' : 'json',
			'success' : displayTinyLink,
			error : errorCallback
		});
	});
	
	function displayTinyLink(data){
		if(data.status=='ACTIVE'){
			$('#tinyUrl').html(data.tinyUrl)
			$('#tinyUrl').attr('href',data.tinyUrl);
		}else{
			$('#invalidUrl').html('Invalid URL')
		}
	}
	
})



function errorCallback(){
	
}



