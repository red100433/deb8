var heartedList;
getHeartedList();

function sendAjax(method, url, body) {
	return $.ajax({
		url : url,
		type : method,
		data: body,
		dataType : 'json'
	});
}

function setCardItem(result, type) {
	var newItem = $('.card-item').first().clone();
	if(type == 'post') {
		newItem.closest('li').data('id', result.id);
		newItem.find('.user-name').text(result.writer.name);
		newItem.find('.user-name').prop("href", '/' + result.writer.code);
		newItem.find('.post-time').text(moment(result.time).fromNow());
		newItem.find('.item-hearts').text(result.hearts);
		newItem.find('.item-content').text(result.contents);
		newItem.find('.item-topic').text(result.topic.title);
		newItem.find('.item-topic').prop("href", '/topic/' + result.topic.code);
		
		// 내가 쓴 포스트 이면 수정 삭제 버튼을 활성화 한다
		if(result.writer.code == $.cookie("code")) {
			newItem.find('.more-box').show();
		}
	} else {
		var target = newItem.find('.item-topic');
		target.prop('style', 'font-size: 15px; padding-bottom: 15px; display:block; font-weight: bold');
		target.text(result.title);
		target.prop("href", '/topic/' + result.code);
		// 하트 아이콘 제거
		newItem.find('.heart-icon').hide();
	}
	
	// 내가 누른 하트이면 미리 표시해준다
	if($.inArray(result.id, heartedList) != -1) {
		newItem.find('.heart-icon').text("favorite");
		newItem.find('.heart-icon').css("color", "Tomato");
	}
	
	newItem.show();
	return newItem;
}

$("#search-form").submit(function(e){
	if($(".search-input").val().length == 0) {
		return false;
	}
});

// 게시글 수정 삭제
$(document).on('click', '.edit', function() {
	var li = $(this).closest('li');
	var itemContent = li.find('.item-content');
	var content = itemContent.text();
	
	itemContent.replaceWith(
			'<div class="edit-box"><textarea class="edit-content">'+
			'</textarea><button class="edit_btn">수정</button></div>'
	);
	
	$('.edit-content').val(content);
	
});

$(document).on('click', '.remove', function() {
	if (confirm("정말 삭제하시겠습니까?")) {
		var li = $(this).closest('li');
		var postId = li.data('id');
		
		sendAjax('DELETE', '/post/delete/' + postId)
			.done(function(isDeleted){
				if(isDeleted) {
					li.remove();
				}
			}).fail(function(result) {
				alert(result.responseText);
			});
	}
});

$(document).on('click', '.edit_btn', function() {
	if(confirm("수정하시겠습니까?")) {
		var postId = $(this).closest('li').data('id');
		
		var editBox = $('.edit-box');
		var itemContent = $('.edit-content');
		var content = itemContent.val();
		
		var body = { contents : content, id : postId };
		
		sendAjax('PUT', '/post/update', body)
			.done(function(isUpdated) {
				if(isUpdated) {
					console.log("update success");
					editBox.replaceWith('<p class="item-content">'+ content +'</p>');
				}
			}).fail(function(result) {
				alert(result.responseText);
			});
	}
});

//이 밑으로 마음에 들어요 시스템
$(document).on('click', '.heart-box', function() {
	var heartCnt = $(this).find('.item-hearts');
	var heartIcon = $(this).find('.heart-icon');
	var num = parseInt(heartCnt.text());
	var postId = $(this).closest('li').data('id');
	
	if(heartIcon.text() == "favorite") {
		sendAjax('DELETE', '/heart/' + postId)
			.done(function(result) {
				if(result) {
					heartIcon.text("favorite_border");
					heartCnt.text(num - 1);
				}
			}).fail(function(result) {
				alert(result.responseText);
			});
		
	} else {
		sendAjax('POST', '/heart/' + postId)
			.done(function(result) {
				if(result) {
					heartIcon.text("favorite");
					heartIcon.css("color", "Tomato");
					heartCnt.text(num + 1);
				}
			}).fail(function(result) {
				alert(result.responseText);
			});
	}
});

function getHeartedList() {
	sendAjax('GET', '/heart').done(function(result){
		heartedList = result;
	});
}