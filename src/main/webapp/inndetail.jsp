<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.InnBean"%>
<%
InnBean inn = (InnBean) request.getAttribute("inn");
%>
<%
int inn_id = inn.getId();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
</head>
<body>
	<jsp:include page="/menu.jsp" />
	<c:choose>
		<c:when test="${Customer.getId() eq 1 }">
	<p><jsp:include page="/managermenu.jsp" /></p>
		</c:when>
		<c:otherwise>
		<p>	<jsp:include page="/Customer_Menu.jsp" /></p>
		</c:otherwise>
	</c:choose>
	<h1>${inn.getName() }</h1>
	<table border="1"  align="center">
		<tr>
			<th>場所</th>
			<td>${inn.getAddress() }</td>
		</tr>
		<tr>
			<th>電話番号</th>
			<td>${inn.getTel() }</td>
		</tr>
		<tr>
			<th>値段</th>
			<td>￥${inn.getPrice() }</td>
		</tr>
	</table>
		<form action="/InnReserve/ReserveServlet?inn_id=${inn.id }"
		method="post">
		<button>予約</button>
		<input type="hidden" name="action" value="goreserve">
		
</body>
</html>