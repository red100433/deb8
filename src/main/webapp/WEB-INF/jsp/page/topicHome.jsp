<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/post.css">
<title>deb8 주제 : ${topic.title}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/asset/header.jsp" />
	
	<div class="post-container">
		<!-- 주제 정보 -->
		<div class="left-sidebar">
			<div class="sidebar-box">
				<h3>${topic.title}</h3>
			</div>
		</div>
		
		<!-- 주제 글 타임라인 -->
		<div class="timeline">
			<div class="card">
				<div class="text-box">
					<textarea id="post-content" placeholder="어떻게 생각하세요?"></textarea>
					<button id="post_btn">등록</button>
				</div>
			</div>
			<jsp:include page="/WEB-INF/jsp/asset/postList.jsp" />
		</div>
		
		<!-- 참여한 사용자 목록 -->
		<div class="right-sidebar">
			<div class="sidebar-box">
				<h3>이 주제에 참여한 사람</h3>
				<div class="infinite-box">
					<ol class="topics">
						<c:forEach var="user" items="${userList}">
							<li class="user-list"><a href="/${user.code}">${user.name}</a></li>
						</c:forEach>
					</ol>	
				</div>
			</div>
		</div>
	</div>
	
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/post.js"></script>
</body>
</html>