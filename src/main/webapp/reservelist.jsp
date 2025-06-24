<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="la.bean.ReserveBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% List<ReserveBean> reserves = (List<ReserveBean>)request.getAttribute("reserves"); %>
<% int count =reserves.size(); %>
<% int cnt=0; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/inn.css"
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
	<h1 class="text-left">|予約一覧</h1>
	<table border="1" align="center" class="fixed-table">
		<tr>
			<th>NO</th>

			<th>宿名</th>
			<th>プラン名</th>
			<c:if test="${Customer.getId() eq 1 }">
				<th>予約者名</th>
			</c:if>
			<th>日数</th>
			<th>人数</th>
			<th>チェックイン日</th>
			<th>合計金額</th>
			<c:if test="${Customer.getId() eq 1 }">
			<th>変更</th>
			</c:if>
			<th>キャンセル</th>
		</tr>
		<c:forEach items="${reserves }" var="reserve">
			<c:forEach items="${inns }" var="inn">
				<c:forEach items="${plans }" var="plan">
				<c:forEach items="${customers }" var="customer">
					<c:if test="${customer.getId() eq reserve.customer_id }">

						<c:if test="${inn.getId() eq reserve.inn_id }">
							<c:if test="${plan.getId() eq reserve.plan_id }">
							<c:choose>
								<c:when test="${reserve.cancel_flag eq true }">
									<tr class="delete_item">
								</c:when>
								<c:otherwise>
									<tr>
								</c:otherwise>
							</c:choose>
							<% cnt++; %>
							<td class="fixed-b-1"><%=cnt %></td>

							<td>${inn.name }</td>
							<td>${plan.title }</td>
							<c:if test="${Customer.getId() eq 1 }">
								<td>${customer.name }</td>
							</c:if>


							<td class="fixed-b-1">${reserve.stay_days }</td>
							<td class="fixed-b-1">${reserve.people }</td>
							<td class="fixed-b-3">${reserve.first_day }</td>
							<td class="fixed-b-2">￥${reserve.total_price }</td>
							<c:if test="${Customer.getId() eq 1 }">
							<form
								action="/InnReserve/ReserveServlet?reserve_id=${reserve.id }&inn_id=${inn.getId()}"
								method="post">
								<td class="fixed-b-1"><button class="b">変更</button></td> <input type="hidden" name="action"
									value="edit">
							</form>
							</c:if>
						<c:choose>
						<c:when test="${ reserve.cancel_flag eq true}">
							<form
								action="/InnReserve/ReserveServlet?reserve_id=${reserve.id }&inn_id=${inn.getId()}"
								method="post">
								<td class="fixed-b-2"><button type="button" class="show">削除</button></td>
								<dialog>
								<p>本当に削除しますか?</p>
								<p>※二度と復旧はできません</p>
								<button>削除</button>
								<input type="hidden" name="action" value="truedelete">
							</form>
							<button type="button" class="close">キャンセル</button>
							</dialog>
							</c:when>
						<c:otherwise>
							<form
								action="/InnReserve/ReserveServlet?reserve_id=${reserve.id }&inn_id=${inn.getId()}"
								method="post">
								<td class="fixed-b-2"><button type="button" class="show fixed-b-2">キャンセル</button></td>
								<dialog>
								<p>本当にキャンセルしますか?</p>
								<button>OK</button>
								<input type="hidden" name="action" value="delete">
							</form>
							<button type="button" class="close">キャンセル</button>
							</dialog>
						</c:otherwise>
						</c:choose>
							</c:if>
						</c:if>
					</c:if>
					</c:forEach>
				</c:forEach>
			</c:forEach>
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