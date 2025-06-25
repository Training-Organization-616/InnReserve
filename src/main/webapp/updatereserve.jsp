<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.ReserveBean"%>
<%@ page import="la.bean.InnBean"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
ReserveBean reserve = (ReserveBean)request.getAttribute("reserve");
%>
<%
int reserve_id = reserve.getId();
%>
<%
InnBean inn = (InnBean) request.getAttribute("inn");
%>
<%
int inn_id = inn.getId();
%>
<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
<% String today = sdf.format(new Date()); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/inn.css" rel="stylesheet">
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
	<h1 class="text-left">|${inn.getName() }</h1>
	<form action="/InnReserve/ReserveServlet?reserve_id=<%=reserve_id%>&price=<%=reserve.getTotal_price() %>&inn_id=<%=inn_id %>"
		method="post">
		<table border="1" align="center">
			<tr>
				<th>宿泊日数</th>
				<td>
					<p>${days_msg}</p><input type="text" name="days" cal="2" class="w-9" value="${reserve.getStay_days() }">
				</td>
			</tr>
			<tr>
				<th>宿泊人数</th>
				<td>
					<p>${people_msg}</p>
					<select name="people" class="w-9-2 f-size">
					<c:forEach begin="1" end="${reserve.getPeople()}" varStatus="num">
						<option value="${num.count}">${num.count}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>チェックイン日</th>
				<td><p>${check_in_msg}</p><div id="app"><input type="date" name="check_in" class="w-9" value="<%= today %>" min="<%= today %>"></div></td>
			</tr>
		</table>
		<dialog>
		<p>この変更内容でよろしいですか?</p>
		<button class="b">変更</button>
	</form>
		<input type="hidden" name="action" value="update">
	<button type="button" id="close">キャンセル</button>
	</dialog>
	<button id="show">変更</button>

	<script type="text/javascript">
		const dialog = document.querySelector('dialog');
		// ダイアログを開く
		document.getElementById('show').addEventListener('click', function() {
		  dialog.showModal();
		});
	 
	// ダイアログを閉じる
		document.getElementById('close').addEventListener('click', function() {
		  dialog.close();
		});

		</script>
</body>
</html>