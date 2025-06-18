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
<style>
body{
	text-align: center;
}
</style>
	<jsp:include page="/menu.jsp" />
	<c:choose>
		<c:when test="${Customer.getId() eq 1 }">
	<p><jsp:include page="/managermenu.jsp" /></p>
		</c:when>
		<c:otherwise>
		<p>	<jsp:include page="/Customer_Menu.jsp" /></p>
		</c:otherwise>
	</c:choose>
	<h1>宿一覧</h1>
	<table border="1" align="center">
		<tr>
			<th>NO</th>
			<th>宿名</th>
			<th>場所</th>
			<th>電話番号</th>
			<th>値段</th>
			<th>詳細</th>
		</tr>
		<c:forEach items="${inns }" var="inn">
			<tr>
				<td>${inn.id }</td>
				<td>${inn.name }</td>
				<td>${inn.address }</td>
				<td>${inn.tel }</td>
				<td>￥${inn.price }</td>
				<form action="/InnReserve/ReserveServlet?inn_id=${inn.id }"
					method="post">
					<td><button>詳細</button></td> <input
						type="hidden" name="action" value="goinn">
				</form>
			</tr>
		</c:forEach>
	</table>
</body>
</html>