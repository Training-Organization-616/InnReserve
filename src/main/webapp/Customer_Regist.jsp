<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	width: 100%;
	font-size: 10px;
	
}
</style>



<span class="centor">新規会員登録</span>

${Regist_message}

<form action="/InnReserve/CustomerServlet" method="post">
	<input type="text" name="name" placeholder="名前" class="text_box">
	<input type="text" name="tel" placeholder="電話番号" class="text_box">
	<input type="text" name="email" placeholder="メールアドレス" class="text_box">
	<input type="text" name="password" placeholder="パスワード" class="text_box">
	<input type="text" name="check_password" placeholder="パスワード(確認)" class="text_box">
	
	<input type="hidden" name="action" value="add">
	
	<button>登録</button>
</form>

</body>
</html>