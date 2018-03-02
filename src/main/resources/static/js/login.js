var modal = $('.modal');

if ($('#register-message').text().length > 0) {
	modal.show();
}

$('#register_btn').on('click', function() {
	var email = $('#email').val();
	var passwd = $('#passwd').val();
	var passwdConfirm = $('#passwdConfirm').val();

	if (validateEmail(email) == false) {
		$('#error').text('이메일 형식에 맞지 않습니다');
		return;
	}
	
	if (passwd != passwdConfirm) {
		$('#error').text('패스워드가 일치하지 않습니다');
		return;
	}
	
	if (passwd.length > 15 || passwd.length < 5 ) {
		$('#error').text('비밀번호는 5자 이상 15자 이하입니다');
		return;
	}

	var body = {email : email, passwd : passwd};
	$('#error').text('작성해주신 이메일로 메일을 보내는 중입니다. \n잠시 기다려주세요');
	
	sendAjax('POST', '/sendmail', body)
		.done(function(result){
			if(result) {
				modal.show();
				$('#error').text('');
			} else {
				$('#error').text('이미 가입된 이메일 입니다');
			}
		}).fail(function(result) {
			$('#error').text(result.responseText);
		});
});
	
$('.close').on('click', function() {
	modal.hide();
});

$(window).on('click', function(event) {
	if (event.target == modal[0]) {
		modal.hide();
	}
});

function validateEmail(email) {
	var re = /^(([^<>()\[\]\\.,;:\s@']+(\.[^<>()\[\]\\.,;:\s@']+)*)|('.+'))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(String(email).toLowerCase());
}

function sendAjax(method, url, body) {
	return $.ajax({
		url : url,
		type : method,
		data: body,
		dataType : 'json'
	});
}