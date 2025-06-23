<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.InnBean"%>
<%
InnBean inn = (InnBean) request.getAttribute("inn");
%>
<%
int inn_id = inn.getId();
%>
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
	
	<h1>${inn.getName()}</h1>
    <img src="${inn.picture }" width="300" height="150">
	<div class="main">
	
	<table border="1" align="center">
		<tr>
			<th>住所</th>
			<td>${inn.getAddress() }</td>
		</tr>
		<tr>	
			<th>電話番号</th>
			<td>${inn.getTel() }</td>
		</tr>
	</table>
	
	<h1>プラン一覧</h1>
  	<table border="1" align="center">
		<tr>
			<th>NO</th>
			<th>プラン名</th>
			<th>最大人数</th>
			<th>金額</th>
			<th>プラン説明</th>
			<th>予約</th>
		</tr>
		<c:forEach items="${plans}" var="plan">
			<tr><form action="/InnReserve/ReserveServlet" method="post">
				<td>${plan.id}</td>
				<td>${plan.title}</td>
				<td>${plan.max_people}</td>
				<td>${plan.price}</td>
				<td>${plan.detail}</td>
				
				<input type="hidden" name="plan_id" value="${plan.id}">
				<input type="hidden" name="max_people" value="${plan.max_people}">
				<input type="hidden" name="price" value="${plan.price}">
				
				<td><button>予約へ</button></td> 
				<input type="hidden" name="inn_id" value="${inn.id}">
				<input type="hidden" name="action" value="goreserve">
				</form>
			</tr>
		</c:forEach>
	</table>
		</div>
</body>
</html>