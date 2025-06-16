<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="la.bean.InnBean"%>
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
	<form action="/InnReserve/ReserveServlet?inn_id=<%=inn_id%>&price=<%=price %>"
		method="post">
		<table border="1">
			<tr>
				<th>宿泊日数</th>
				<td>
					<input type="text" name="days" cal="2">日
				</td>
			</tr>
			<tr>
				<th>宿泊人数</th>
				<td>
					<input type="text" name="people" cal="2">人
				</td>
			</tr>
			<tr>
				<th>チェックイン日</th>
				<td><input type="date" name="check_in"></td>
			</tr>
		</table>
		<button>予約</button>
		<input type="hidden" name="action" value="reserve">
	</form>
</body>
</html>