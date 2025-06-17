<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- メニューのリンク --%>
<%-- action:list --%>
<%-- userId:2(管理者) --%>
<%-- menu:1(宿)2(予約)3(ユーザー) --%>
<a href="/InnReserve/InnServlet?action=list&userId=2&menu=1" >宿</a>
<a href="/InnReserve/ReserveServlet?action=reservelist" >予約</a>
<a href="/InnReserve/CustomerServlet?action=list" >ユーザー</a>
<a href="/InnReserve/InnServlet?action=regist" >新規宿登録</a>
</body>
</html>