<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="la.bean.InnBean"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
InnBean inn = (InnBean) request.getAttribute("inn");
%>
<%
int inn_id = inn.getId();
int price = inn.getPrice();
%>
<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
<% String today = sdf.format(new Date()); %>
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
				<p>${days_msg}</p><th>宿泊日数</th>
				<td>
					<input type="text" name="days">日
				</td>
			</tr>
			<tr>
				<p>${people_msg}</p><th>宿泊人数</th>
				<td>
					<input type="text" name="people">人
				</td>
			</tr>

			<tr>
				<p>${check_in_msg}</p><th>チェックイン日</th>
				<td>
				<div id="app"><input type="date" name="check_in" value="<%= today %>" min="<%= today %>"></div></td>
			</tr>		
		</table>
		<button>予約</button>
		<input type="hidden" name="action" value="reserve">
	</form>
</body>
</html>