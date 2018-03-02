<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/setting.css">
<title>deb8 회원 정보 설정</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/asset/header.jsp" />
	
	<div class="main-container">
		<!-- 목록 -->
		<div class="settings-container">
			<div class="tab">
				<button class="tablinks" id="user_btn">회원 정보</button>
				<button class="tablinks" id="topic_btn">내가 쓴 주제 관리</button>
				<button class="tablinks" id="passwd_btn">비밀번호 수정</button>
				<button class="tablinks" id="logout_btn">로그아웃</button>
				<button class="tablinks" id="delete_btn">회원탈퇴</button>
			</div>
			
			<!-- 회원정보 수정 -->
			<div class="tabcontent" id="user-detail-modify">
				<table class="user-detail">
					<tr>
						<td>이름</td>
						<td class="font-gray" id="name"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td class="font-gray" id="email"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td class="font-gray" id="address"></td>
					</tr>
					<tr>
						<td>소개</td>
						<td><textarea id="bio"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><button id="user_edit_btn">수정</button></td>
					</tr>
				</table>
			</div>
			
			<!-- 내가 작성한 주제 관리 -->
			<div class="tabcontent" id="topic-modify">
				<div id="topic-box">
					<jsp:include page="/WEB-INF/jsp/asset/topicList.jsp" />
				</div>
			</div>
			
			<!-- 비밀번호 수정 -->
			<div class="tabcontent" id="passwd-modify">
				<div id="passwd-box">
					<table class="user-detail">
					<tr>
						<td>현재 비밀번호</td>
						<td><input class="passwd-form" id="current-passwd" type="password"></td>
					</tr>
					<tr>
						<td>새 비밀번호</td>
						<td><input class="passwd-form" id="new-passwd" type="password"></td>
					</tr>
					<tr>
						<td>새 비밀번호 확인</td>
						<td><input class="passwd-form" id="passwd-confirm" type="password"></td>
					</tr>
					<tr>
						<td colspan="2"><button id="passwd_edit_btn">수정</button></td>
					</tr>
				</table>
				</div>
			</div>
			
		</div>
	</div>
	
	<script src="/js/setting.js"></script>
</body>
</html>