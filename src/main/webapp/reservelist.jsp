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
	<h1>予約一覧</h1>
	<table border="1">
		<tr>
			<th>予約</th>
			<th>宿名</th>
			<th>人数</th>
			<th>日数</th>
			<th>チェックイン日</th>
			<th>合計金額</th>
			<th>変更</th>
			<th>削除</th>
		</tr>
		<c:forEach items="${reserves }" var="reserve">
		<c:forEach items="${inns }" var="inn">
			<c:if test="${inn.getId() eq reserve.inn_id }">
			<tr>
				<td>${reserve.id }</td>
				<td>${inn.name }</td>
				

				<td>${reserve.people }</td>
				<td>${reserve.stay_days }</td>
				<td>${reserve.first_day }</td>
				<td>${reserve.total_price }</td>
				<form action="/InnReserve/ReserveServlet?reserve_id=${reserve.id }&inn_id=${inn.getId()}"
					method="post">
					<td><button>変更</button></td> <input
						type="hidden" name="action" value="edit">
				</form>
				<form action="/InnReserve/ReserveServlet?reserve_id=${reserve.id }&"
					method="post">
					<td><button>削除</button></td> <input
						type="hidden" name="action" value="delete">
				</form>
			</c:if>
		</c:forEach>
			</tr>
		</c:forEach>
	</table>
		<a href="/InnReserve/ReserveServlet?action=list">宿一覧</a>
</body>
</html>