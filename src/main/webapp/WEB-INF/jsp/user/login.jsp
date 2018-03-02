<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/login.css">
<title>deb8 로그인</title>
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
			<p>
				<small><font color="red">${errorMessage}</font></small>
			</p>
			<small>아직 회원이 아니신가요? <a href="/register">가입하기</a></small>
			<form method="post">
				<input type="text" placeholder="Enter your email" name="username" required> 
				<input type="password" placeholder="Enter Password" name="password" required>
				<button type="submit">로그인</button>
			</form>
		</div>
	</div>

	<!-- 회원가입 완료 modal -->
	<div id="registerModal" class="modal">
		<div class="modal-content">
			<span class="close">&times;</span>
			<p id="register-message">${registerMessage}</p>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
	<script src="/js/login.js"></script>
</body>
</html>