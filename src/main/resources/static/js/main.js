var path = window.location.pathname;
getMainList(path.split('/')[2]);
// 더보기 버튼 지원하지 않음 (한꺼번에 보여준다)
$('#show-item-alert').hide();

function getMainList(type) {
	sendAjax('GET', '/recommend/' + type).done(
		function(result) {
			for (var i = 0; i < result.length; i++) {
				showResult(result[i], type);
			}
			
			$('.card').css('margin', '10px');
	});
}

function showResult(result, type) {
	$('.card-item').first().after(setCardItem(result, type));
}

$('#title_btn').on('click', function() {
	var title =  $('#title-input').val();
	
	if(title.length == 0) {
		return
	}
	
	var body =  { title : title};
	
	sendAjax('POST', '/topic/create', body)
		.done(function(result) {
			window.location.href = '/topic/' + result.code;
		}).fail(function(result) {
			alert(result.responseText);
		});
});