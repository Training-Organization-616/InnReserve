<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
h1{
	font-size: 50px;
}

body{
	font-size: 25px;
}
</style>

<div style="background-color: orange; color: black;  margin-top: -30px;">
		<c:choose>
		 <c:when test="${Customer.getId() eq 1 }">
	<h1>
<!--	<a href="/InnReserve/InnServlet?action=list">宿予約システム</a>-->
	<img  src="title.png" width="600px" height = "150px"onclick="location.href='/InnReserve/InnServlet?action=list';" style="margin-bottom: -60px;">
	</h1>
		</c:when>
		<c:otherwise>
	<h1>
	<img  src="title.png" width="600px" height = "150px"onclick="location.href='/InnReserve/ReserveServlet?action=list';" style="margin-bottom: -60px;">
<!--	<a href="/InnReserve/ReserveServlet?action=list">宿予約システム</a>-->
	</h1>
		</c:otherwise>
			</c:choose>
	<p align="right" style="margin-top: -30px;">
		<c:if test="${not empty Customer}">
こんにちは、${Customer.name}さん<br>
利用可能ポイント : <span style="font-weight:700;">${Customer.point }pt</span><br>
		<c:choose>
		 <c:when test="${Customer.getId() eq 1 }">
			<a href="/InnReserve/CustomerServlet?action=edit&id=${Customer.getId()}" class="link" >管理者情報の変更</a>|
		</c:when>
		<c:otherwise>
			<a href="/InnReserve/CustomerServlet?action=edit&id=${Customer.getId()}" class="link" >会員情報の変更</a>|
		</c:otherwise>
			</c:choose>
			<a href="/InnReserve/CustomerServlet?action=logout" class="link" >ログアウト</a>
		</c:if>
		<c:if test="${empty Customer}">
			<a href="/InnReserve/CustomerServlet?action=gologin" class="link" >ログイン</a> 
</c:if>
</p>
</div>