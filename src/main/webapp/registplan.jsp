<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
body{
	text-align: center;
}
</style>
	<%-- メニューのリンク --%>
	<p id="menu"><jsp:include page="/menu.jsp" /></p>
	<h1>新規プラン登録</h1>
	<%-- エラーメッセージを表示する --%>
	<p>
		<c:if test="${not empty message }">
${message }
</c:if>
	</p>
	<form action="/InnReserve/PlanServlet" method="post">
		<table border="1" align="center">
			<tr>
				<th>プラン名</th>
				<td><input type="text" name="title" maxlength="50" size="30" placeholder="50文字以下"></td>
			</tr>
			<tr>
				<th>最大人数</th>
				<td><input type="text" name="max_people" maxlength="50" size="30" placeholder="50文字以下"></td>
			</tr>
			<tr>
				<th>金額</th>
				<td><input type="text" name="price" maxlength="20" size="30"></td>
			</tr>
			<tr>
				<th>詳細</th>
				<td><input type="textarea" name="detail" size="30"></td>
			</tr>
		</table>
		<button type="button" class="show" id="button">登録</button>
		<dialog>
		<p>この登録内容でよろしいですか?</p>
		<button>登録</button>
		<input type="hidden" name="action" value="add">
		<button type="button" class="close">キャンセル</button>
		</dialog>
		<input type="hidden" name="inn_id" value="${inn.id }">
	</form>
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