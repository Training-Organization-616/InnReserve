<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<h1>宿予約システム</h1>
	<p align="right">
		<c:if test="${not empty customer}">
こんにちは、${user.name}さん<br>
			<a href="/InnReserve/CustomerServlet?action=edit">会員情報の変更</a>|
			<a href="/InnReserve/CustomerServlet?action=logout">ログアウト</a>
		</c:if>
		<c:if test="${empty customer}">
			<a href="/InnReserve/CustomerServlet">ログイン</a> |
</c:if>
