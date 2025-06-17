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

	<jsp:include page="/menu.jsp" />
	<c:choose>
		<c:when test="${Customer.getId() eq 1 }">
	<p><jsp:include page="/managermenu.jsp" /></p>
		</c:when>
		<c:otherwise>
		<p>	<jsp:include page="/Customer_Menu.jsp" /></p>
		</c:otherwise>
	</c:choose>


<span class="centor">会員情報変更</span><br>

${Update_massage}

<form action="/InnReserve/CustomerServlet" method="post">
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="id" value="${id}">
	<input type="text" name="name" value="${name}"placeholder="名前" maxlength="20" class="text_box">
	<input type="text" name="tel" value="${tel}" placeholder="電話番号" maxlength="20" class="text_box">
	<input type="text" name="email" value="${email}" placeholder="メールアドレス" maxlength="50" class="text_box">
	<input type="text" name="password" value="${password}" placeholder="パスワード" maxlength="20" class="text_box">
	<input type="text" name="check_password" placeholder="パスワード(確認)" maxlength="20" class="text_box">
		<dialog>
		<p>この変更内容でよろしいですか?</p>
		<button>変更</button>
	</form>
	<button type="button" id="close">キャンセル</button>
	</dialog>
	<button id="show">変更</button>

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