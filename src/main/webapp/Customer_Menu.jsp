<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
</head>
<body>
<%-- メニューのリンク --%>
<%-- action:list --%>
<%-- CustomerId=sessionのCustomerId--%>
<%-- menu:1(宿)2(予約)3(ユーザー) --%>

<div class="menu">
<a href="/InnReserve/ReserveServlet?action=list">宿一覧</a>
<span  style="margin-right: 50px;"></span>

<c:if test="${Customer ne null }">
<a href="/InnReserve/ReserveServlet?action=reservelist" >予約一覧</a>
</c:if>
</div>
</body>
</html>