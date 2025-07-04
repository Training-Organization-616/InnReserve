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
<link href="${pageContext.request.contextPath}/inn.css" rel="stylesheet">
</head>
<body>
<style>
body{
	text-align: center;
}

input{
	font-size: 20px;
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
	<h1 class="text-left">|ポイント利用</h1>
	
	<form action="/InnReserve/ReserveServlet">
	<input type="hidden" name="action" value="comfirm">
	<input type="hidden" name="inn_id" value="${inn_id}">
	<input type="hidden" name="plan_id" value="${plan_id}">
	<input type="hidden" name="total_price" value="${total_price}">
	<input type="hidden" name="first_day" value="${first_day}">
	<input type="hidden" name="finally_day" value="${finally_day}">
	<input type="hidden" name="people" value="${people}">
	<input type="hidden" name="stay_days" value="${stay_days}">
	
	
	<table align="center">
		<tr><th>金額(税込み)</th><td>${total_price}円</td></tr>
		<tr><th>利用有無</th>
			<td>
			<input type="radio" name="usePoint" value="yes">利用する
			<input type="radio" name="usePoint" value="no" checked>利用しない		
			</td></tr>
		<tr><th>利用ポイント</th>
		<p>${point_message}</p>
		<c:choose>
		<c:when test="${total_price ge Customer.getPoint() }">
			<td><input type="number" name="how_usePoint" value="0" min="0" max="${Customer.getPoint()}" >ポイント/${Customer.getPoint()}ポイント</td></tr>
		</c:when>
		<c:otherwise>
			<td><input type="number" name="how_usePoint" value="0" min="0" max="${total_price}" >ポイント/${Customer.getPoint()}ポイント</td></tr>
		</c:otherwise>
			</c:choose>
	</table>
	
	<button style="width: 170px">予約はこちら</button>
	</form>
</body>
</html>