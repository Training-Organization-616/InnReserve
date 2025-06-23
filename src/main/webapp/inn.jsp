<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="text-center"><jsp:include page="/menu.jsp" /></div>
	<c:choose>
		<c:when test="${Customer.getId() eq 1 }">
			<div class="text-center"><jsp:include page="/managermenu.jsp" /></div>
		</c:when>
		<c:otherwise>
			<div class="text-center">
				<jsp:include page="/Customer_Menu.jsp" /></div>
			<%-- 検索 --%>
			<div class="text-center background-box border m-1 bg-white">
				<form action="/InnReserve/InnServlet" method="get">
					<span>宿名<input type="text" name="name" placeholder="?宿名:"
						maxlength="50"></span> <span>場所<input type="text"
						name="address" placeholder="?場所:" maxlength="50"></span> <span>金額<input
						type="text" name="min_price" maxlength="10">円 <span>～</span>
						<span><input type="text" name="max_price" maxlength="10">円</span>
						<button class="button">検索</button> <input type="hidden"
						name="action" value="search">
				</form>
			</div>
		</c:otherwise>
	</c:choose>
	<h1 class="text-left">|宿一覧</h1>
	<ul>
		<div class="flex">
			<c:forEach items="${inns }" var="inn">
				<li class="flex relative ml-1 mr-4">
					<div class="background border">
						<a
							href="/InnReserve/ReserveServlet?inn_id=${inn.id }&action=goinn" />
						<%-- <a href="/"> --%>
						<img src="${inn.picture }" width="300" height="150" class="headline">
<!--						<img src="./hotel.png" width="300" height="150">-->
						<div>
							<span class="block truncate font-bold text-xl text-color-bk">${inn.name }</span>
						</div>
						<div class="text-sm text-gray-700">
							<span class="block truncate">${inn.address }</span>
						</div>
						<div class="text-right">
							<span class="block"> <span
								class="inline-block text-color-bk">1名 (税込)</span> <span
								class="inline-block text-color-bk"> <span
									class="block truncate text-2xl font-bold text-color-bk">${inn.min_price }円
										<span class="text-color-bk">～</span>
								</span>
							</span>
							</span>
						</div>
					</div>
				</li>
			</c:forEach>
		</div>
	</ul>
</body>
</html>