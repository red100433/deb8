sendAjax('GET', '/user/get').done(function(result){
	$('#name').text(result.name);
	$('#email').text(result.email);
	$('#address').text('http://www.deb8.com/'+result.code);
	$('#bio').val(result.bio);
	$('#user_btn').click();
});

$(function() {
	$('#user_btn').on('click', function(){
		onTablClick(this, $('#user-detail-modify'));
	});

	$('#topic_btn').on('click', function(){
		onTablClick(this, $('#topic-modify'));
		getTopicList();
	});
	
	$('#passwd_btn').on('click', function() {
		onTablClick(this, $('#passwd-modify'));
	});

	$('#logout_btn').on('click', function(){
		location.href = '/logout';
	});

	$('#delete_btn').on('click', function(){
		var password = prompt('deb8을 떠나실건가요? \n\n회원탈퇴를 하시려면 비밀번호를 입력해주세요');
		var body = {passwd : password};
		
		sendAjax('GET', '/passwd/check', body).done(function(isExist) {
			if(isExist) {
				sendAjax('DELETE', '/user/delete')
					.done(function(result) {
						if(result) {
							location.href="/logout";
						}
					});
			} else {
				alert('패스워드가 올바르지 않습니다.')
			}
		});
	});
});

// 주제 변경 버튼 
$(document).on('click', '.topic-edit', function(){
	var title = $(this).closest('.item-main').find('.topic-title');
	var content = title.text();
	
	title.replaceWith(
			'<div id="edit-box"><input id="edit-title">'+ 
			'<button id="edit_btn">수정</button></div>'
		);
	
	$('#edit-title').val(content);
});

$(document).on('click', '#edit_btn',function(){
	var topicId = $(this).closest('li').data('id');
	var title = $('#edit-title').val();
	var body = { title : title, id : topicId};
	
	sendAjax('PUT', "/topic/update", body)
		.done(function(result) {
			if(result) {
				alert('수정되었습니다. 새로고침 해주세요');
			}
		}).fail(function(result) {
			alert(result.responseText);
		});
});

// 주제 삭제 버튼 
$(document).on('click', '.topic-remove', function(){
	if(confirm("관련된 모든 생각들이 삭제됩니다. 진행하시겠습니까?")) {
		var li = $(this).closest('li');
		var topicId = li.data('id');
		
		sendAjax('DELETE', '/topic/delete/' + topicId)
		.done(function(result) {
			if(result) {
				li.remove();
			}
		}).fail(function(result) {
			alert(result.responseText);
		});
	}
});

// 사용자 소개 정보 수정
$('#user_edit_btn').on('click', function() {
	var body = { bio : $('#bio').val() };
	
	sendAjax('PUT', "/user/update/bio", body).done(function(result) {
		if(result) {
			alert('수정 되었습니다');
		}
	});
});

// 비밀번호 변경
$('#passwd_edit_btn').on('click', function() {
	var passwd = $('#new-passwd').val();
	var passwdConfirm = $('#passwd-confirm').val();
	
	if(passwd == passwdConfirm) {
		var body = { passwd : $('#current-passwd').val() };
		console.log(body);
		
		sendAjax('GET', '/passwd/check', body).done(function(isValid) {
			if(isValid) {
				var body = {passwd : passwd};
				sendAjax('PUT', '/user/update/passwd', body).done(function(result) {
					if(result) {
						alert('패스워드가 변경되었습니다.');
					}
				}).fail(function(result) {
					alert(result.responseText);
				});
			} else {
				alert('현재 패스워드가 올바르지 않습니다.')
			}
		});
	} else {
		alert('새 패스워드가 일치하지 않습니다. 동일하게 입력해주세요.');
	}
});



function onTablClick(link, clicked) {
	$('.tabcontent').hide();
	clicked.show();
	$('.tablinks').removeClass('active');
	$(link).addClass('active');
}

function getTopicList() {
	// 일단 다 지우기
	$('.card-item').not('li:first').remove();
	
	sendAjax('GET', "/settings/get/topiclist").done(function(result){
		for(var i=0; i<result.length; i++) {
			setTopicCard(result[i]);
		}
	});
}

function setTopicCard(result) {
	var newItem = $('.card-item').first().clone();
	newItem.closest('li').data('id', result.id);
	newItem.find('.topic-title').prop("href", '/topic/' + result.code);
	newItem.find('.topic-title').text(result.title);
	newItem.show();
	
	$('ul').append(newItem);
}
