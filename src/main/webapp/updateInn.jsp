<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/InnReserve/InnServlet" method="post">
		<table border="1" align="center">
			<tr>
				<th>宿名</th>
				<td><input type="text" name="name" value="${name }"></td>
			</tr>
			<tr>
				<th>場所</th>
				<td><input type="text" name="address" value="${address }"></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><input type="text" name="tel" value="${tel }"></td>
			</tr>
			<tr>
				<th>値段</th>
				<td><input type="text" name="price" value="${price }"></td>
			</tr>
		</table>
		<button>変更</button>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="id" value="${inn_id}">
</body>
</html>