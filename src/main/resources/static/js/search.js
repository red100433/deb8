var path = window.location.pathname;
var keyword = $('h2').text();

$('.post-container').css("margin-top", "30px");

$('.tablinks').on('click', function() {
	// 일단 다 지우기
	$('.card-item').not('li:first').remove();
	
	// 첫번째 검색결과 가져오기 
	getMoreResult(this.id, 999999999);
	
    // 자기 아닌 탭 active 풀기
    $('.tablinks').removeClass('active');
    $(this).addClass('active');
});

$('#old-item').on("click", function() {
	getMoreResult($('.active').prop('id'), $(".card-item").last().data('id'));
});

$('#post').click();

function getMoreResult(type, lastId) {
	var url = "/search/" + type + '/' + keyword + '/' + lastId;
	
	sendAjax('GET', url).done(function(result) {
		// 가져온 데이터가 10개 (디폴트 페이징 수) 보다 적으면 더보기 버튼 숨김
		if(result.length < 10) {
			$('#show-item-alert').hide();
		}
		for (var i = 0; i < result.length; i++) {
			showPost(result[i], type);
		}
	});
}

function showPost(result, type) {
	$('#show-item-alert').before(setCardItem(result, type));
}