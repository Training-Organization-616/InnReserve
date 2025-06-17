<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<style>

body{
	text-align: center;
}

div{
	text-align: center;
	height: 500px auto;
	width: 500px auto;
	
	
}
.centor{
	text-align: center;
}
.text_box{
	width: 100px;
	font-size: 10px;
	
}
</style>

	<h1><a href="/InnReserve/ReserveServlet?action=list">宿予約システム</a></h1>

<span class="centor">ログイン</span><br>

${Login_Unkown}
${Login_message}

<form action="/InnReserve/CustomerServlet" method="post">
	<input type="text" name="email" placeholder="メールアドレス" class="text_box"><br>
	<input type="text" name="password" placeholder="パスワード" class="text_box"><br>
	
	<input type="hidden" name="action" value="login">
	
	<button>ログイン</button>
</form>
<form action="/InnReserve/Customer_Regist.jsp" method="get">
	<input type="hidden" name="action" value="regist" >
	<button>新規登録</button>
</form>
</div>




</body>
</html>