<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.InnBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- メニューのリンク --%>
	<jsp:include page="/menu.jsp" />
	<%-- 管理者メニューのリンク --%>
	<p><jsp:include page="/managermenu.jsp" /></p>
	<%-- エラーメッセージを表示する --%>
	<p><c:if test="${not empty message }">
${message }
</c:if></p>
	<form action="/InnReserve/InnServlet" method="post">
		<table border="1" align="center">
			<tr>
				<th>宿名</th>
				<td><input type="text" name="name" value="${name }" maxlength="50"></td>
			</tr>
			<tr>
				<th>場所</th>
				<td><input type="text" name="address" value="${address }" maxlength="50"></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><input type="text" name="tel" value="${tel }" placeholder="ハイフンを含めて入力してください。" maxlength="20"></td>
			</tr>
			<tr>
				<th>値段</th>
				<td>￥<input type="text" name="price" value="${price }"></td>
			</tr>
		</table>
		<form action="/InnReserve/InnServlet" method="post">
			<input type="hidden" name="id" value="${inn_id}">
			<input type="hidden" name="action" value="update">
			<%-- 変更(ダイアログ付き) --%>
			<button type="button" class="show">変更</button>
			<dialog>
			<p>この変更内容でよろしいですか?</p>
			<button>変更</button>
			<input type="hidden" name="action" value="update">
			<button type="button" class="close">キャンセル</button>
			</dialog>
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