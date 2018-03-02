<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/post.css">
<title>deb8 생각 광장</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/asset/header.jsp" />
	
	<div class="main-container">
		<div class="welcome-message">
			<h2>deb8 에서 최근 가장 인기있는 생각들을 골라봤습니다</h2>
			<p>나와 비슷한 생각이 있는지 찾아보세요!</p>
			<p>마음에 드는 생각이 있다면 하트 버튼을 눌러 호감을 표시해보세요.</p>
			<p>공감하거나 다르게 생각하는 부분이 있다면 직접 대화에 참여해 deb8을 구성해보세요</p>
		</div>
		
		<div class="list-container">
			<div id="list-result">
				<jsp:include page="/WEB-INF/jsp/asset/postList.jsp" />
			</div>
		</div>
	</div>
	
    <script src="/js/main.js"></script>
</body>
</html>