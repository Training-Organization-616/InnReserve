<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="la.bean.PlanBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% List<PlanBean> plans = (List<PlanBean>)request.getAttribute("plans"); %>
<% int count =plans.size(); %>
<% int cnt=0; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css"
	rel="stylesheet">
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
	<h1>プラン一覧</h1>
	<h2>${inn.name }</h2>
	<table border="1" align="center">
		<tr>
			<th>NO</th>
			<th>プラン名</th>
			<th>最大人数</th>
			<th>プラン金額</th>
			<th>詳細</th>
		<c:choose>
			<c:when test="${Customer.getId() eq 1 }">
				<th>変更</th>
				<th>削除</th>
			</c:when>
			<c:otherwise>
			<th>予約</th>
			</c:otherwise>
		</c:choose>
			
		</tr>
		<c:forEach items="${plans }" var="plan">
					<c:if test="${inn.id eq plan.inn_id }">

							<c:choose>
								<c:when test="${plan.delete_flag eq true }">
									<tr class="delete_item">
								</c:when>
								<c:otherwise>
									<tr>
								</c:otherwise>
							</c:choose>
							<% cnt++; %>
							<td><%=cnt %></td>

							<td>${plan.title }</td>
							<td>${plan.max_people }</td>
							<td>${plan.price }</td>
							<td>${plan.detail }</td>
							<c:choose>
							<c:when test="${Customer.getId() eq 1 }">
							<form
								action="/InnReserve/PlanServlet?plan_id=${plan.id }&inn_id=${inn.id}"
								method="post">
								<td><button>変更</button></td> <input type="hidden" name="action"
									value="edit">
							</form>
							<form
								action="/InnReserve/PlanServlet?plan_id=${plan.id }&inn_id=${inn.id}"
								method="post">
								<td><button type="button" class="show">削除</button></td>
								<dialog>
								<p>本当に削除しますか?</p>
								<button>削除</button>
								<input type="hidden" name="action" value="delete">
							</form>
							<button type="button" class="close">キャンセル</button>
							</dialog>
							</c:when>
							<c:otherwise>
							<form action="/Innreserve/ReserveServlet?plan_id=${plan.id }" method="post">
							<td><button>予約</button></td>
							<input type="hidden" name="action" value="goreserve">
							</form>
							</c:otherwise>
							</c:choose>
						</c:if>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		var dialog = document.querySelectorAll('dialog');
		var btn_show = document.getElementsByClassName('show');
		var btn_close = document.getElementsByClassName('close');
	
		for (let i = 0; i < <%=count%>; i++) {
			btn_show[i].addEventListener('click', function() {
				dialog[i].showModal();
			}, false);
		}
		for (let i = 0; i < <%=count%>
		; i++) {
			btn_close[i].addEventListener('click', function() {
				dialog[i].close();
			}, false);
		}
	</script>

</body>
</html>