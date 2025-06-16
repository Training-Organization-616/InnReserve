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
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>場所</th>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><input type="text" name="tel"></td>
			</tr>
			<tr>
				<th>値段</th>
				<td><input type="text" name="price"></td>
			</tr>
		</table>
		<button>登録</button>
		<input type="hidden" name="action" value="add">
	</form>
</body>
</html>