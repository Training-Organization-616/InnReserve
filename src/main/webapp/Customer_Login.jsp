<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css" rel="stylesheet">
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
	
	height: 100%;
	width: 95%;
	font-size: 12px;
	
}
</style>

	<h1><a href="/InnReserve/ReserveServlet?action=list">宿予約システム</a></h1>

<h1 class="centor">ログイン</h1><br>

${Login_Unkown}
${Login_message}

<form action="/InnReserve/CustomerServlet" method="post">
<table  border="1" align="center">
	<tr><th>アドレス</th><td style="width: 200px; "><input type="text" name="email" placeholder="メールアドレス" class="text_box"></td></tr>
	<tr><th>パスワード</th><td><input type="password" name="password" placeholder="パスワード" class="text_box"></td></tr>

</table>

<input type="hidden" name="action" value="login">
	
<div>
<table align="center" width="300px"  style="background-color: darkseagreen;">
<tr><td class="twobutton">
	<button>ログイン</button>
	</form>
	</td>
	<td class="twobutton">
	<form action="/InnReserve/Customer_Regist.jsp" method="get">
	<input type="hidden" name="action" value="regist" >
	<button>新規登録</button>
	</form>
	</td></tr>	
</table>	

</div>




</body>
</html>