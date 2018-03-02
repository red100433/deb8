<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/post.css">
<title>deb8 주제 광장</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/asset/header.jsp" />
	
	<div class="main-container">
		<div class="welcome-message">
			<h2>deb8 에서 최근 가장 인기있는 주제를 골라봤습니다</h2>
			<p>관심이 있는 주제를 눌러 대화에 참여해보세요.</p>
			<p>특별히 이야기하고 싶은 주제가 있으면 아래에서 등록하고 이야기를 나눠보세요.</p>
			<p>더 많은 사람과 이야기하고 싶다면 등록한 주제의 url을 복사해 친구들에게 전달해 함께해보세요!</p>
		</div>
		
		<div class="list-container">
			<div id="list-result">
				<div class="title-box">
					<input id="title-input" placeholder="어떤 이야기를 나누고 싶나요?">
					<button id="title_btn">등록</button>
				</div>
				<jsp:include page="/WEB-INF/jsp/asset/postList.jsp" />
			</div>
		</div>
	</div>
	
    <script src="/js/main.js"></script>
</body>
</html>