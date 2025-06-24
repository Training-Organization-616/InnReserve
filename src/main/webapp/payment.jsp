<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.InnBean"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
     

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
	<h1>ポイント利用</h1>
	
	<form action="/InnReserve/ReserveServlet">
	<input type="hidden" name="action" value="comfirm">
	<input type="hidden" name="inn_id" value="${inn_id}">
	<input type="hidden" name="plan_id" value="${plan_id}">
	<input type="hidden" name="total_price" value="${total_price}">
	<input type="hidden" name="first_day" value="${first_day}">
	<input type="hidden" name="finally_day" value="${finally_day}">
	<input type="hidden" name="people" value="${people}">
	<input type="hidden" name="stay_days" value="${stay_days}">
	
	<table>
		<tr><th>金額(税込み)</th><td>${total_price}</td></tr>
		<tr><th>利用有無</th>
			<td>
			<input type="radio" name="usePoint" value="yes">利用する
			<input type="radio" name="usePoint" value="no" checked>利用しない		
			</td></tr>
		<tr><th>利用ポイント</th>
			<td><input type="number" name="how_usePoint" velue="0" min="0" max="${cus_point}" >ポイント/${Customer.getPoint() }ポイント</td></tr>
	</table>
	
	<button>予約はこちらから</button>
	</form>
</body>
</html>