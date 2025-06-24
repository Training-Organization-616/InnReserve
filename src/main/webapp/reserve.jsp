<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.InnBean"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
InnBean inn = (InnBean) request.getAttribute("inn");
%>

<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
<% String today = sdf.format(new Date()); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css" rel="stylesheet">
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
	<h1>${inn.name}の予約：プラン${plan.id}:${plan.title}</h1>
	
	<div class="main">
	<form action="/InnReserve/ReserveServlet" method="post">
	<input type="hidden" name="inn_id" value="${plan.inn_id}">
	<input type="hidden" name="price" value="${plan.price}">
	<input type="hidden" name="plan_id" value="${plan.id}">
	<input type="hidden" name="max_people" value="${plan.max_people}">
	
		<table border="1" align="center">
			<tr>
				<th>宿泊日数</th>
				<td>
					<p>${days_msg}</p><input type="number" name="days">日
				</td>
			</tr>
			<tr>
				<th>宿泊人数</th>
				<td>
					<p>${people_msg}</p>
					<select name="people">
					<c:forEach begin="1" end="${plan.max_people}" varStatus="num">
						<option value="${num.count}">${num.count}</option>
					</c:forEach>
					</select>
					人 / ${plan.max_people}人
				</td>
			</tr>

			<tr>
				<th>チェックイン日</th>
				<td>
				<p>${check_in_msg}</p><div id="app"><input type="date" name="check_in" value="<%= today %>" min="<%= today %>"></div></td>
			</tr>		
		</table>
		<button>予約</button>
		<input type="hidden" name="action" value="reserve">
	</form>
	</div>
</body>
</html>