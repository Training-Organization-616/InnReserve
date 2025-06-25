<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css" rel="stylesheet">
</head>
<body>
<%-- メニューのリンク --%>
<%-- action:list --%>
<%-- userId:2(管理者) --%>
<%-- menu:1(宿)2(予約)3(ユーザー) --%>
<div class="menu">
<a href="/InnReserve/InnServlet?action=list&userId=2&menu=1" style="margin-right: 30px;" class="link">宿</a>
<a href="/InnReserve/ReserveServlet?action=reservelist" style="margin-right: 30px;" class="link">予約</a>
<a href="/InnReserve/CustomerServlet?action=list"style="margin-right: 30px;" class="link" >ユーザー</a>
<a href="/InnReserve/InnServlet?action=regist" class="link" >新規宿登録</a>
</div>
</body>
</html>