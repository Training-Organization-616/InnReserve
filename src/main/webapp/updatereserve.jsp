<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="la.bean.ReserveBean"%>
<%@ page import="la.bean.InnBean"%>
<%
ReserveBean reserve = (ReserveBean)request.getAttribute("reserve");
%>
<%
int reserve_id = reserve.getId();
%>
<%
InnBean inn = (InnBean) request.getAttribute("inn");
%>
<%
int inn_id = inn.getId();
int price = inn.getPrice();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
</head>
<body>
<h1>${inn.getName() }</h1>
	<form action="/InnReserve/ReserveServlet?reserve_id=<%=reserve_id%>&price=<%=price %>"
		method="post">
		<table border="1">
			<tr>
				<th>宿泊日数</th>
				<td>
					<input type="text" name="days" cal="2" value="${reserve.getStay_days() }">
				</td>
			</tr>
			<tr>
				<th>宿泊人数</th>
				<td>
					<input type="text" name="people" cal="2"value="${reserve.getPeople() }">
				</td>
			</tr>
			<tr>
				<th>チェックイン日</th>
				<td><input type="date" name="check_in"value="${reserve.getFirst_day() }"></td>
			</tr>
		</table>
		<button>変更</button>
		<input type="hidden" name="action" value="update">
	</form>
</body>
</html>