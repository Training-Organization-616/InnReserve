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
<link href="${pageContext.request.contextPath}/menu.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/inn.css" rel="stylesheet">
</head>
<body>
	<style>
body {
	text-align: center;
}
</style>
	<jsp:include page="/menu.jsp" />
	<c:choose>
		<c:when test="${Customer.getId() eq 1 }">
			<p><jsp:include page="/managermenu.jsp" /></p>
		</c:when>
		<c:otherwise>
			<p>
				<jsp:include page="/Customer_Menu.jsp" /></p>
		</c:otherwise>
	</c:choose>

	<h1 class="text-left">|${inn.getName()}</h1>
	<img src="${inn.picture }" width="300" height="150">
	<div class="main">
<div class="b-fit-size m-1">
<ul style="list-style: none;">
<li>
		<table border="1" align="center" class="w-max">
			<tr>
				<th>住所</th>
				<td>${inn.getAddress() }</td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td>${inn.getTel() }</td>
			</tr>
		</table>
	</li>
		</ul>
</div>
		<h1 class="text-left">|プラン一覧</h1
	<div>
		<ul>
			<c:forEach items="${plans}" var="plan">
				<li class="block m-1">
					<div class="background border-2">
					<a href="/InnReserve/ReserveServlet?plan_id=${plan.id }&action=goreserve"/>
					<div class="text-color-bk font-bold t-size text-left">${plan.title }</div>
					<table class="plan-table  no-border">
						<thead>
							<tr>
								<th class="color-wh text-left b-top"><span class="text-color-bk">|部屋タイプ・詳細</span></th>
								<th class="color-wh b-top text-left"><span class="text-color-bk">1名(税込)</span></th>
								<th class="color-wh b-top text-left"><span class="text-color-bk">合計(税込)/${plan.max_people}名</span></th>
							</tr>
						</thead>
						<tbody>
							<tr class="no-border">
								<td class="text-color-bk color-wh no-border text-left">${plan.detail }</td>
								<td class="text-color-bk color-wh no-border text-left"><span>&yen</span>${plan.price }～</td>
								<td class="text-color-bk color-wh no-border text-left">
								<c:choose>
										<c:when test="${plan.max_people >= 2}">
											<span>&yen</span>${plan.price * plan.max_people}～
										</c:when>
											<c:otherwise>
											<span>&yen</span>${plan.price}～
											</c:otherwise>
								</c:choose></td>
							</tr>
						</tbody>
					</table>					
	
	</div>
	</li>
	</c:forEach>
	</ul>
	</div>
</body>
</html>