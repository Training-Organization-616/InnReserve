<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/inn.css" rel="stylesheet">
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

body{
	text-align: center;
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


<h1 class="text-left">|会員情報変更</h1>

${Update_massage}

<form action="/InnReserve/CustomerServlet" method="post">
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="${id}">
<table border="1" align="center">
	<tr><th>名前</th>
		<td style="width: 300px;"><input type="text" name="name" value="${name}"placeholder="名前" maxlength="20" class="text_box"></td>
	</tr>
	<tr><th>電話番号</th>
		<td><input type="text" name="tel" value="${tel}" placeholder="電話番号" maxlength="20" class="text_box"></td>
	</tr>
	<tr><th>アドレス</th>
		<td><input type="text" name="email" value="${email}" placeholder="メールアドレス" maxlength="50" class="text_box"></td>
	</tr>
	<c:choose>
	<c:when test="${Customer.getId() eq 1}">
	<tr><th>ポイント</th>
		<td><input type="number" name="point" value="${point}" maxlength="50" class="text_box"></td>
	</tr>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="point" value="${point }">
	</c:otherwise>
	</c:choose>
	<c:choose>
	<c:when test="${Customer.getId() ne 1 || id eq 1}">
	<tr><th>パスワード</th>
		<td><input type="password" name="password" value="${original_password}" placeholder="パスワード" maxlength="20" class="text_box"></td>
	</tr>
	<tr><th>パスワード確認</th>
		<td><input type="password" name="check_password" placeholder="パスワード(確認)" maxlength="20" class="text_box"></td>
	</tr>	
	</c:when>
	<c:otherwise>
	<input type="hidden" name="password" value="${original_password}">
	<input type="hidden" name="check_password" value="${original_password}">
	</c:otherwise>
	</c:choose>
	<input type="hidden" name="original_email" value="${original_email }">
	<input type="hidden" name="original_password" value="${original_password }">
</table>
		<dialog id="dialog1">
		<p>この変更内容でよろしいですか?</p>
		<button>変更</button>
	</form>
	<button type="button" class="close">キャンセル</button>
	</dialog>
<table align="center" class="twobutton" width="300px">
	<tr><td class="twobutton">
	<button class="show" data-dialog="dialog1">変更</button>
	</td>
	<td class="twobutton">
		<form action="/InnReserve/CustomerServlet" method="post">
		<input type="hidden" name="action" value="delete">
		<input type="hidden" name="id" value="${id}">

		<dialog id="dialog2">
		<p>本当に削除してもよろしいですか?</p>
		<button>削除</button>
		</form>
	<button type="button" class="close">キャンセル</button>
		</dialog>
	<button class="show"  data-dialog="dialog2" >削除</button> 
	</td></tr>	
</table>

	<script type="text/javascript">
		var dialogs = document.querySelectorAll('dialog');
		const open = document.querySelectorAll('.show');
		open.forEach(button => {
		  button.addEventListener('click', () => {
		    const dialogId = button.getAttribute('data-dialog');
		    const dialog = document.getElementById(dialogId);
		    dialog.showModal();
		    dialog.classList.add('show');
		  });
		});
		const close = document.querySelectorAll('.close');
		close.forEach(button => {
		  button.addEventListener('click', () => {
		  const dialog = button.closest('dialog');
		  dialog.classList.remove('show');
		  setTimeout(() => dialog.close(), 0);
		  });
		});

<!--		var btn_show = document.getElementById('show');-->
<!--		var btn_close = document.getElementById('close');-->
<!--		btn_show.addEventListener('click', function() {-->
<!--			dialog.showModal();-->
<!--		}, false);-->
<!--		btn_close.addEventListener('click', function() {-->
<!--			dialog.close();-->
<!--		}, false);-->
		
<!--		var dialog2 = document.querySelector('dialog2');-->
<!--		var btn_show2 = document.getElementById('show2');-->
<!--		var btn_close2 = document.getElementById('close2');-->
<!--		btn_show2.addEventListener('click', function() {-->
<!--			dialog2.showModal();-->
<!--		}, false);-->
<!--		btn_close2.addEventListener('click', function() {-->
<!--			dialog2.close();-->
<!--		}, false);-->
	</script>		

</body>
</html>