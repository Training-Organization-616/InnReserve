<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/inn.css"
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
	<h1 class="text-left">|新規宿登録</h1>
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
				<td><input type="text" name="name" maxlength="50" placeholder="50文字以下" class="textbox-size w-9"></td>
			</tr>
			<tr>
				<th>場所</th>
				<td><input type="text" name="address" maxlength="50" placeholder="50文字以下" class="textbox-size w-9"></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><input type="text" name="tel"
					placeholder="ハイフンを含める" maxlength="20" class="textbox-size w-9"></td>
			</tr>
			<tr>
				<th>画像</th>
				<td><input type="file" name="picture" onchange="previewFile(this);"></td>
<img id="preview">
 
  <script>
  function previewFile(hoge){
    var fileData = new FileReader();
    fileData.onload = (function() {
      //id属性が付与されているimgタグのsrc属性に、fileReaderで取得した値の結果を入力することで
      //プレビュー表示している
      document.getElementById('preview').src = fileData.result;
    });
    fileData.readAsDataURL(hoge.files[0]);
  }
  </script>						</tr>
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