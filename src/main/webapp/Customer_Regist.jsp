<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
</head>
<body>

<style>
div{
	text-align: center;
	height: 500px auto;
	width: 500px auto;
	
	
}
.centor{
	text-align: center;
}
.text_box{
	width:95%;
	size: 50px;
	
}

button{
	display:inline-block;
}
</style>

	<jsp:include page="/menu.jsp" />
<br>

<h1 style="text-align: center;">新規会員登録</h1>

${Regist_message}

<form action="/InnReserve/CustomerServlet" method="post">
<table border="1" align="center">
	<tr><th>名前</th>
		<td style="width: 300px;"><input type="text" name="name" placeholder="名前：20文字以下" maxlength="20" class="text_box"></td></tr>
	<tr><th>電話番号</th>
		<td><input type="text" name="tel" placeholder="電話番号" maxlength="20" class="text_box"></td></tr>
	<tr><th>アドレス</th>
		<td><input type="text" name="email" placeholder="メールアドレス：５０文字以下" maxlength="50" class="text_box"></td></tr>
	<tr><th>パスワード</th>
		<td><input type="text" name="password" placeholder="パスワード：２０文字以下"maxlength="20" class="text_box"></td></tr>
	<tr><th>パスワード確認</th>
		<td><input type="text" name="check_password" placeholder="パスワード(確認)" maxlength="20" class="text_box"></td></tr>
	
</table>	
	<input type="hidden" name="action" value="add">
	
		<dialog>
		<p>この内容でよろしいですか?</p>
		<button>登録</button>
	</form>
	<button type="button" id="close">キャンセル</button>
	</dialog>
	<table align="center">
	<tr><td><button id="show" class="centor">登録</button></td></tr>
	</table>

	<script type="text/javascript">
		var dialog = document.querySelector('dialog');
		var btn_show = document.getElementById('show');
		var btn_close = document.getElementById('close');
		btn_show.addEventListener('click', function() {
			dialog.showModal();
		}, false);
		btn_close.addEventListener('click', function() {
			dialog.close();
		}, false);
	</script>
</body>
</html>