<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<h1><a href="/InnReserve/ReserveServlet?action=list">宿予約システム</a></h1>
	<p align="right">
		<c:if test="${not empty Customer}">
こんにちは、${Customer.name}さん<br>
		<c:choose>
		 <c:when test="${Customer.getId() eq 1 }">
			<a href="/InnReserve/CustomerServlet?action=edit&id=${Customer.getId()}">管理者情報の変更</a>|
		</c:when>
		<c:otherwise>
			<a href="/InnReserve/CustomerServlet?action=edit&id=${Customer.getId()}">会員情報の変更</a>|
		</c:otherwise>
			</c:choose>
			<a href="/InnReserve/CustomerServlet?action=logout">ログアウト</a>
		</c:if>
		<c:if test="${empty Customer}">
			<a href="/InnReserve/CustomerServlet?action=gologin">ログイン</a> 
</c:if>
</p>