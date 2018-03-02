<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/post.css">
<title>deb8 유저 홈 : ${user.name}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/asset/header.jsp" />
	
	<div class="post-container">
		<!-- 회원 정보 -->
		<div class="left-sidebar">
			<div class="sidebar-box">
				<h3 class="profile-name">${user.name}</h3>
				
				<jsp:useBean id="dateValue" class="java.util.Date"/>
				<jsp:setProperty name="dateValue" property="time" value="${user.time}"/>
				<small class="profile-reg-date">
					가입일 : <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/>
				</small>
				
				<p class="profile-bio">${user.bio}</p>
			
				<div class="statistic-box">
					<ol>
						<li>좋아요 수 : ${stat.heartCnt}</li>
					</ol>
				</div>
			</div>
		</div>
		
		<!-- 개인의 글 타임라인 -->
		<div class="timeline">
			<div class="card">
				<small class="center">${user.name}님의 생각 <strong>${stat.postCnt}</strong>개</small>
			</div>
			<jsp:include page="/WEB-INF/jsp/asset/postList.jsp" />
		</div>
		
		<!-- 참여한 토픽 목록 -->
		<div class="right-sidebar">
			<div class="sidebar-box">
				<h3>참여한 토픽 ${stat.topicCnt}개</h3>
				<div class="infinite-box">
					<ol class="topics">
						<c:forEach var="topic" items="${topicList}">
							<li class="topic-list"><a href="/topic/${topic.code}">${topic.title}</a></li>
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