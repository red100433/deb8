<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<ul id="card-list">
		<!-- 새로운 글 등록됐을 때 보이는 카드 -->
		<li id="new-item-alert" style="display:none">
			<div class="card new font-gray">
				<a id="new-item" class="center"></a>
			</div>
		</li>
		
		<li class="card-item" style="display:none">
			<div class="card">
			  <div class="container">
			  	<div class="item-header">
			  		<small><strong><a class="user-name"></a></strong></small>
			  		<small class="post-time font-gray"></small>
			  		<div class="more-box" style="display: none">
				  		<button class="edit btn"><i class="material-icons font-gray" style="font-size: 15px">edit</i></button>
				  		<button class="remove btn"><i class="material-icons font-gray" style="font-size: 15px">clear</i></button>
			  		</div>
			  	</div>
			  	<div class="item-main">
			  		<p class="item-content"></p>
			  	</div>
			    <div class="item-footer font-gray">
			    	<small><a class="item-topic"></a></small>
			    	<div class="heart-box">
				  		<button class="heart btn">
					  		<i class="material-icons heart-icon" style="font-size: 15px">favorite_border</i>
					  		<small class="item-hearts"></small>
					  	</button>
			  		</div>
			    </div>
			  </div>
			</div>
		</li>
		
		<!-- 더 보기 -->
		<li id="show-item-alert">
			<div class="card new font-gray">
				<a id="old-item" class="center">더 보기</a>
			</div>
		</li>
	</ul>