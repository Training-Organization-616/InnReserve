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



<span class="centor">会員情報変更</span><br>

${Update_massage}

<form action="/InnReserve/CustomerServlet" method="post">
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="id" value="${id}">
	<input type="text" name="name" value="${name}"placeholder="名前" class="text_box">
	<input type="text" name="tel" value="${tel}" placeholder="電話番号" class="text_box">
	<input type="text" name="email" value="${email}" placeholder="メールアドレス" class="text_box">
	<input type="text" name="password" value="${password}" placeholder="パスワード" class="text_box">
	<input type="text" name="check_password" placeholder="パスワード(確認)" class="text_box">
		
	<button>登録</button>
</form>

</body>
</html>