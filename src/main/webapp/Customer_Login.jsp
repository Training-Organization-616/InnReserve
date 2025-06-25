<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/login.css" rel="stylesheet">
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
c-brack{
 color:brack;
}
</style>

	<jsp:include page="/menu.jsp" />

${Login_Unkown}
${Login_message}

<body>
  <div class="login">
    <div class="login-screen">
      <div class="app-title">
        <h1>ログイン</h1>
      </div>

      <div class="login-form">
        <div class="control-group">
        <form action="/InnReserve/CustomerServlet" method="post">
        <input type="text" class="login-field" name="email" placeholder="メールアドレス" id="login-name">
        <label class="login-field-icon fui-user" for="login-name"></label>
        </div>

        <div class="control-group">
        <input type="password" class="login-field" name="password" placeholder="パスワード" id="login-pass">
        <label class="login-field-icon fui-lock" for="login-pass"></label>
        </div>
        <button class="bu btn btn-primary btn-large btn-block login-link">ログイン</button>
		<input type="hidden" name="action" value="login">
        </form>
		<span style="color:brack;"><a class="login-link" href="/InnReserve/CustomerServlet?action=regist">新規登録</a></span>
      </div>
    </div>
  </div>
</body>

</body>
</html>