<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<div class="header">
		<div class="navbar">
			<a href="/home"><i class="material-icons middle font-gray">home</i> 내 페이지</a>
			<a href="/main/topic"><i class="material-icons middle font-gray">spa</i> 주제 광장</a>
			<a href="/main/post"><i class="material-icons middle font-gray">toys</i> 생각 광장</a>
			<a href="/settings"><i class="material-icons middle font-gray">more_vert</i></a>
			
			<div class="search">
				<form id="search-form" action="/search">
					<input class="search-input font-gray" type="text" name="keyword" placeholder="검색하기"> 
					<button class="btn" type="submit"><i class="material-icons font-gray middle">search</i></button>
				</form>
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.2.1/moment.min.js"></script>
<script src="/js/common.js"></script>
