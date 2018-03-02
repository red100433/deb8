<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/login.css">
<title>deb8 회원가입</title>
</head>
<body>
	<!-- 로그인 화면 왼쪽에 정렬될 문자들 -->
	<div class="login-view left">
		<div class="centered">
			<h3>이곳에선 무엇도 정의하지 않습니다</h3>
			<h3>다양한 생각을 둘러보고</h3>
			<h3>당신의 생각을 자유롭게 나누세요</h3>
		</div>
	</div>

	<!-- 로그인 푬 -->
	<div class="login-view right">
		<div class="centered">
			<p><strong>deb8 가입하기</strong></p>
			<small>아이디로 사용할 이메일을 입력해주세요</small>
			<form id="register-form" method="post">
			    <input type="text" placeholder="Enter your email" id="email" name="email" required>
			    <input type="password" placeholder="Enter Password" id="passwd" name="passwd" required>
			    <input type="password" placeholder="Enter Password Again" id="passwdConfirm" required>
			    <small><font id="error" color="red"></font></small>
			</form>
			<button id="register_btn">가입하기</button>
		</div>
	</div>
	
	<div id="emailModal" class="modal">
		<div class="modal-content">
			<span class="close">&times;</span>
			<p id="email-message">
				입력하신 이메일로 인증메일을 보냈습니다.<br>
				1시간 내에 확인해서 가입을 완료해주세요.<br>
				이 화면은 꺼도 좋습니다.
			</p>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
	<script src="/js/login.js"></script>
</body>
</html>