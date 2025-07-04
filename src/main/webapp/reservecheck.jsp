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

h3{
	text-align: reft;
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
	
	
	
	<h2>以下の内容で予約いたします。</h2>
	<h1 class="text-left">|会員情報</h1>

	<table align="center" class="t-w">
	<tr><th class="fixed-b-3">名前</th><td class="text-left">${Customer.getName()}</td></tr>
	<tr><th class="fixed-b-3">電話番号</th><td class="text-left">${Customer.getTel()}</td></tr>
	
	</table>

	<h1 class="text-left">|宿・プラン・料金</h1>
	

	<table align="center" class="t-w">
	<tr><th class="fixed-b-3">宿名</th><td class="text-left">${inn.name}</td></tr>
	<tr><th class="fixed-b-3">場所</th><td class="text-left">${inn.address}</td></tr>
	<tr><th class="fixed-b-3">電話番号</th><td class="text-left">${inn.tel}</td></tr>
	<tr><th class="fixed-b-3">プラン</th><td class="text-left">${plan.title}</td></tr>
	<tr><th class="fixed-b-3">人数</th><td class="text-left">${people }人</td>
	<tr><th class="fixed-b-3">日時</th><td class="text-left">${first_day} ～ ${finally_day}(${stay_days }日)</td></tr>
	<tr><th class="fixed-b-3">金額(税込)</th><td class="text-left">${total_price}円(${how_usepoint}ポイント利用)</td></tr>
	</table>

	
	<form action="/InnReserve/ReserveServlet">
		<input type="hidden" name="action" value="reservecomp"> 
		<input type="hidden" name="plan_id" value="${plan.id }">
		<input type="hidden" name="original_price" value="${original_price }">
		<input type="hidden" name="customer_id" value="${Customer.getId() }">
		<input type="hidden" name="people" value="${people }">
		<input type="hidden" name="stay_days" value="${stay_days }">
		<input type="hidden" name="first_day" value="${first_day }">
		<input type="hidden" name="how_usepoint" value="${how_usepoint }">

		<button class="confirm">予約確定！</button>
	</form>
	
</body>
</html>