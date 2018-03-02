var stompClient = null;
var path = window.location.pathname;
var hasItemToSend = false;

// 사용자가 확인하지 않은 상태에서 갱신된 포스트 목록
var postPool = [];
var postCnt = 0;

// 0번째 포스트아이디를 가진 포스트보다 이전에 등록된 포스트를 일정량 만큼의 더 읽어들인다
// 첫 페이지 초기화
getMorePost(999999999);

connect();

function connect() {
	var socket = new SockJS('/postlive');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		stompClient.subscribe(path, function(post) {
			var body = $.parseJSON(post.body);
			
			postPool.push(body);
			postCnt++;
			
			$('#new-item').text('새로운 생각 ' + postCnt + '개 보기');
			$('#new-item-alert').show();
			
			if(hasItemToSend) {
				sendPostToUserHome(body);
			}
		});
	});
}

$(window).on('beforeunload', function () {
	stompClient.disconnect();
});

function sendPostToTopicHome() {
	stompClient.send('/live' + path + '/create', {}, JSON.stringify({
		'topicCode' : path.split('/')[2],
		'contents' : $('#post-content').val(),
	}));
	hasItemToSend = true;
}

function sendPostToUserHome(body) {
	stompClient.send('/live/' + body.writer.code + '/created', {}, JSON.stringify(body));
	hasItemToSend = false;
}

function showRecentPost(result) {
	$('#new-item-alert').after(setCardItem(result, 'post'));
}

function showPastPost(result) {
	$('#show-item-alert').before(setCardItem(result, 'post'));
}

function getMorePost(lastId) {
	var url = path + '/get/' + lastId;
	sendAjax('GET', url)
		.done(function(result) {
			if(result.length < 10) {
				$('#show-item-alert').hide();
			}
			for (var i = 0; i < result.length; i++) {
				showPastPost(result[i]);
			}
		});
}

$('#post_btn').on('click', function() {
	var postContent = $('#post-content').val();
	
	if(postContent.length > 0) {
		sendPostToTopicHome();
		$('#post-content').val('');
	}
});

$('#new-item').on("click", function() {
	while (postPool.length) {
		showRecentPost(postPool.shift());
	}
	
	$('#new-item-alert').hide();
	postCnt = 0;
});

$('#old-item').on("click", function() {
	getMorePost($(".card-item").last().data('id'));
});