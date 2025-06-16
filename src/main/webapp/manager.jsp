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
<%-- メニューのリンク --%>
<jsp:include page="/managermenu.jsp" />

<%-- [menu=1]宿を選択 --%>
<c:if test="${menu ==1}">
<%-- 一覧表の作成 --%>
			<table border="1" align="center">
				<tr>
					<th>NO</th>
					<th>宿名</th>
					<th>場所</th>
					<th>電話番号</th>
					<th>値段</th>
					<th>変更</th>
					<th>削除</th>
				</tr>
				<%-- リストを名前[items]として取得 --%>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name }</td>
						<td>${item.address }</td>
						<td>${item.tel }</td>
						<td>${item.price }</td>
						<%-- 変更ボタン --%>
						<td>
							<form action="/InnReserve/InnServlet" method="get">
								<button class="button">変更</button>
								<input type="hidden" name="action" value="edit"> 
								<input type="hidden" name="userId" value="2"><%-- 管理者[userId:2] --%>
								<input type="hidden" name="Id" value="${item.id }">
							</form>
						</td>
						<%-- 削除ボタン --%>
						<td>
							<form action="/InnReserve/InnServlet" method="post">
								<input type="hidden" name="action" value="delete">
								 <input type="hidden" name="userId" value="2"><%-- 管理者[userId:2] --%>
								 <input type="hidden" name="Id" value="${item.id}">
								 <%-- 削除(ダイアログ付き) --%>
								 <input type="submit" onclick="return DeleteCheck();" value="削除">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
</c:if>

<%-- [menu=2]予約を選択 --%>
<c:if test="${menu ==2}">
<%-- 一覧表の作成 --%>
			<table border="1" align="center">
				<tr>
					<th>予約</th>
					<th>会員</th>
					<th>宿</th>
					<th>人数</th>
					<th>日数</th>
					<th>チェックイン日</th>
					<th>合計金額</th>
					<th>変更</th>
					<th>削除</th>
				</tr>
				<%-- リストを名前[items]として取得 --%>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.customer_id}</td>
						<td>${item.inn_id }</td>
						<td>${item.people }</td>
						<td>${item.stay_days }</td>
						<td>${item.first_day }</td>
						<td>${item.total_price }</td>
						<%-- 変更ボタン --%>
						<td>
							<form action="/InnReserve/ReserveServlet" method="get">
								<button>変更</button>
								<input type="hidden" name="action" value="edit"> 
								<input type="hidden" name="userId" value="2"><%-- 管理者[userId:2] --%>
								<input type="hidden" name="Id" value="${item.id }">
							</form>
						</td>
						<%-- 削除ボタン --%>
						<td>
							<form action="/InnReserve/ReserveServlet" method="get">
								<input type="hidden" name="action" value="delete">
								 <input type="hidden" name="userId" value="2"><%-- 管理者[userId:2] --%>
								 <input type="hidden" name="Id" value="${item.id}">
								 <%-- 削除(ダイアログ付き) --%>
								 <input type="submit" onclick="return DeleteCheck();" value="削除">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
</c:if>

<%-- [menu=3]ユーザーを選択 --%>
<c:if test="${menu ==3}">
<%-- 一覧表の作成 --%>
			<table border="1" align="center">
				<tr>
					<th>NO</th>
					<th>名前</th>
					<th>電話番号</th>
					<th>メールアドレス</th>
					<th>パスワード</th>
					<th>変更</th>
					<th>削除</th>
				</tr>
				<%-- リストを名前[items]として取得 --%>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name }</td>
						<td>${item.tel }</td>
						<td>${item.email }</td>
						<td>${item.password }</td>
						<%-- 変更ボタン --%>
						<td>
							<form action="/InnReserve/InnServlet" method="get">
								<button class="button">変更</button>
								<input type="hidden" name="action" value="edit"> 
								<input type="hidden" name="userId" value="2"><%-- 管理者[userId:2] --%>
								<input type="hidden" name="Id" value="${item.id }">
							</form>
						</td>
						<%-- 削除ボタン --%>
						<td>
							<form action="/InnReserve/InnServlet" method="post">
								<input type="hidden" name="action" value="delete">
								 <input type="hidden" name="userId" value="2"><%-- 管理者[userId:2] --%>
								 <input type="hidden" name="Id" value="${item.id}">
								 <%-- 削除(ダイアログ付き) --%>
								 <input type="submit" onclick="return DeleteCheck();" value="削除">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
</c:if>


<%-- 削除ダイアログ処理 --%>
	<script type="text/javascript">
		function DeleteCheck() {
			if (confirm("削除します。よろしいですか？")) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>
</html>