<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="la.bean.InnBean"%>
<%@ page import="la.bean.CustomerBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
List<InnBean> inns = (List<InnBean>) request.getAttribute("items");
%>
<%
int count=0;
if(inns!=null){
	count = inns.size();
}
%>
<%--<%
List<ReserveBean> reserves = (List<ReserveBean>) request.getAttribute("reserves");
%>
<%
int count2 = reserves.size();
%>--%>

<%
List<CustomerBean> customers = (List<CustomerBean>) request.getAttribute("Customers_list");
%>
<%
int count3=0;
if(customers!=null){
	count3 = customers.size();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/menu.css" rel="stylesheet">
</head>
<body>
<style>
body{
	text-align: center;
}
</style>
	<%-- メニューのリンク --%>
	<jsp:include page="/menu.jsp" />
	<%-- 管理者メニューのリンク --%>
	<p><jsp:include page="/managermenu.jsp" /></p>
	<%-- [menu=1]宿を選択 --%>
	<c:if test="${menu ==1}">
	<h1>宿一覧</h1>
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
				<c:choose>
					<c:when test="${item.delete_flag}">
						<tr class="delete_item">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>
					<td>${item.id}</td>
					<td>${item.name }</td>
					<td>${item.address }</td>
					<td>${item.tel }</td>
					<td>￥${item.price }</td>
					<%-- 変更ボタン --%>
					<td>
						<form action="/InnReserve/InnServlet" method="get">
							<button class="button">変更</button>
							<input type="hidden" name="action" value="edit"> <input
								type="hidden" name="userId" value="2">
							<%-- 管理者[userId:2] --%>
							<input type="hidden" name="id" value="${item.id }"> <input
								type="hidden" name="name" value="${item.name }"> <input
								type="hidden" name="address" value="${item.address }"> <input
								type="hidden" name="tel" value="${item.tel }"> <input
								type="hidden" name="price" value="${item.price }">
						</form>
					</td>
					<td>
						<%-- 削除ボタン --%>
						<form action="/InnReserve/InnServlet" method="post">
							<input type="hidden" name="action" value="delete"> <input
								type="hidden" name="userId" value="2">
							<%-- 管理者[userId:2] --%>
							<input type="hidden" name="id" value="${item.id}">
							<%-- 削除(ダイアログ付き) --%>
							<button type="button" class="show">削除</button>
							<dialog>
							<p>本当に削除しますか?</p>
							<button>削除</button>
							<input type="hidden" name="action" value="delete">
							<button type="button" class="close">キャンセル</button>
							</dialog>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<%-- [menu=3]ユーザーを選択 --%>
	<c:if test="${menu ==3}">
	<h1>ユーザ一覧</h1>
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
			<c:forEach items="${Customers_list}" var="item">
				<c:choose>
					<c:when test="${ item.delete_flag}">
						<tr class="delete_item">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
					</c:choose>
						<td>${item.id}</td>
					<td>${item.name }</td>
					<td>${item.tel }</td>
					<td>${item.email }</td>
					<td>${item.password }</td>
					<%-- 変更ボタン --%>
					<td>
						<form action="/InnReserve/CustomerServlet" method="get">
							<button class="button">変更</button>
							<input type="hidden" name="action" value="edit"> <input
								type="hidden" name="userId" value="2">
							<%-- 管理者[userId:2] --%>
							<input type="hidden" name="id" value="${item.id }">
						</form>
					</td>
					<%-- 削除ボタン --%>
					<td>
						<form action="/InnReserve/CustomerServlet" method="post">
							<input type="hidden" name="userId" value="2">
							<%-- 管理者[userId:2] --%>
							<input type="hidden" name="id" value="${item.id}">
							<%-- 削除(ダイアログ付き) --%>
							<button type="button" class="show">削除</button>
							<dialog id="dialog"> 
							<p>本当に削除しますか?</p>
							<button>削除</button>
							<input type="hidden" name="action" value="delete">
							<button type="button" class="close">キャンセル</button>
							</dialog>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<% if(count!=0){%>
	<script type="text/javascript">
		var dialog = document.querySelectorAll('dialog');
		var btn_show = document.getElementsByClassName('show');
		var btn_close = document.getElementsByClassName('close');

		for (let i = 0; i <
	<%=count%>
		; i++) {
			btn_show[i].addEventListener('click', function() {
				dialog[i].showModal();
			}, false);
		}
		for (let i = 0; i <
	<%=count%>
		; i++) {
			btn_close[i].addEventListener('click', function() {
				dialog[i].close();
			}, false);
		}
	</script>
	<% } %>
			<% if(count3!=0){%>
		<script type="text/javascript">
		var dialog = document.querySelectorAll('dialog');
		var btn_show = document.getElementsByClassName('show');
		var btn_close = document.getElementsByClassName('close');

		for (let i = 0; i <
	<%=count3%>
		; i++) {
			btn_show[i].addEventListener('click', function() {
				dialog[i].showModal();
			}, false);
		}
		for (let i = 0; i <
	<%=count3%>
		; i++) {
			btn_close[i].addEventListener('click', function() {
				dialog[i].close();
			}, false);
		}
	</script>
	<%} %>
</body>
</html>