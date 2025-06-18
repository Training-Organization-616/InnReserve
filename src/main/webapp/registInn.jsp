<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<%-- エラーメッセージを表示する --%>
	<p>
		<c:if test="${not empty message }">
${message }
</c:if>
	</p>
	<form action="/InnReserve/InnServlet" method="post">
		<table border="1" align="center">
			<tr>
				<th>宿名</th>
				<td><input type="text" name="name" maxlength="50"></td>
			</tr>
			<tr>
				<th>場所</th>
				<td><input type="text" name="address" maxlength="50"></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><input type="text" name="tel"
					placeholder="ハイフンを含めて入力してください。" maxlength="20"></td>
			</tr>
			<tr>
				<th>値段</th>
				<td>￥<input type="text" name="price"></td>
			</tr>
		</table>
		<button type="button" class="show" id="button">登録</button>
		<dialog>
		<p>この登録内容でよろしいですか?</p>
		<button>登録</button>
		<input type="hidden" name="action" value="add">
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