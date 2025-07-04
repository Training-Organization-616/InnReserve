<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</style>
	<%-- メニューのリンク --%>
	<jsp:include page="/menu.jsp" />
	<%-- 管理者メニューのリンク --%>
	<p><jsp:include page="/managermenu.jsp" /></p>
	<h1 class="text-left">|プラン変更</h1>
	<%-- エラーメッセージを表示する --%>
	<p><c:if test="${not empty message }">
${message }
</c:if></p>
<div class="main">
	<form action="/InnReserve/PlanServlet" method="post">
		<table border="1" align="center">
			<tr>
				<th>プラン名</th>
				<td class="fixed-b-5"><input type="text" name="title" class="w-9" value="${title }" maxlength="50"></td>
			</tr>
			<tr>
				<th>最大人数</th>
				<td class="fixed-b-5"><input type="number" name="max_people" class="w-9" value="${max_people }" maxlength="50" min="0"></td>
			</tr>
			<tr>
				<th>金額</th>
				<td class="fixed-b-5"><input type="number" name="price" class="w-9" value="${price }" maxlength="20" min="0"></td>
			</tr>
			<tr>
				<th>詳細</th>
				<td class="fixed-b-5"><input type="text" name="detail" class="w-9" value="${detail }">
				</td>
			</tr>
		</table>
		<form action="/InnReserve/InnServlet" method="post">
			<input type="hidden" name="inn_id" value="${inn_id}">
			<input type="hidden" name="plan_id" value="${plan_id}">
			<input type="hidden" name="original_title" value="${title}">
			<input type="hidden" name="original_max_people" value="${max_people}">
			<input type="hidden" name="original_price" value="${price}">
			<input type="hidden" name="original_detail" value="${detail}">
			<input type="hidden" name="action" value="update">
			<%-- 変更(ダイアログ付き) --%>
			<button type="button" class="show">変更</button>
			<dialog>
			<p>この変更内容でよろしいですか?</p>
			<button class="b">変更</button>
			<input type="hidden" name="action" value="update">
			<button type="button" class="close">キャンセル</button>
			</dialog>
		</form>
		</div>
		<%-- 変更ダイアログ処理 --%>
		<script type="text/javascript">
			var dialog = document.querySelectorAll('dialog');
			var btn_show = document.getElementsByClassName('show');
			var btn_close = document.getElementsByClassName('close');

			for (let i = 0; i < 1; i++) {
				btn_show[i].addEventListener('click', function() {
					dialog[i].showModal();
				}, false);
			}
			for (let i = 0; i < 1; i++) {
				btn_close[i].addEventListener('click', function() {
					dialog[i].close();
				}, false);
			}
		</script>
</body>
</html>