<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/post.css">
<link rel="stylesheet" type="text/css" href="/css/search.css">
<title>deb8 검색 : ${keyword}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/asset/header.jsp" />
	
	<!-- 검색 결과 헤더 -->
	<div class="search-header">
		<div class="search-keyword">
			<h2>${keyword}</h2>
		</div>
		<div class="tab">
			  <button id="post" class="tablinks">생각</button>
			  <button id="topic" class="tablinks">주제</button>
		</div>
	</div>
	
	<div class="post-container">
		<!-- 검색된 생각 카드 -->
		<div id="search-result">
			<div class="tabcontent">
			  	<jsp:include page="/WEB-INF/jsp/asset/postList.jsp" />
			</div>
		</div>
	</div>
	
    <script src="/js/search.js"></script>
</body>
</html>