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
<title>宿予約</title>
<link href="${pageContext.request.contextPath}/menu.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/inn.css" rel="stylesheet">
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
	<h1 class="text-left">|宿一覧</h1>
		<%-- 一覧表の作成 --%>
		<table border="1" align="center" class="fixed-table">
			<tr>
				<th>NO</th>
				<th>宿名</th>
				<th>場所</th>
				<th>電話番号</th>
				<th>最小金額</th>
				<th>プラン一覧</th>
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
					<td class="fixed-b-1">${item.id}</td>
					<td>${item.name }</td>
					<td>${item.address }</td>
					<td class="fixed-b-3">${item.tel }</td>
					<td class="fixed-b-2">￥${item.min_price }</td>
					<td class="fixed-b-2">
						<form action="/InnReserve/PlanServlet" method="get">
						<button class="button fixed-b-2">一覧</button>
						<input type="hidden" name="action" value="list">
						<input type="hidden" name="inn_id" value="${item.id }">
						</form>
					</td>
					<%-- 変更ボタン --%>
					<td class="fixed-b-1">
						<form action="/InnReserve/InnServlet" method="get">
							<button class="button">変更</button>
							<input type="hidden" name="action" value="edit"> <input
								type="hidden" name="userId" value="2">
							<%-- 管理者[userId:2] --%>
							<input type="hidden" name="id" value="${item.id }"> <input
								type="hidden" name="name" value="${item.name }"> <input
								type="hidden" name="address" value="${item.address }"> <input
								type="hidden" name="tel" value="${item.tel }"> <input
								type="hidden" name="picture" value="${item.picture }">
						</form>
					</td>
					<td class="fixed-b-1">
						<%-- 削除ボタン --%>
						<c:choose>
						<c:when test="${item.delete_flag eq true }">
							<form action="/InnReserve/InnServlet" method="post">
							<input type="hidden" name="action" value="truedelete"> <input
								type="hidden" name="userId" value="2">
							<%-- 管理者[userId:2] --%>
							<input type="hidden" name="id" value="${item.id}">
							<%-- 削除(ダイアログ付き) --%>
							<button type="button" class="show">削除</button>
							<dialog>
							<p>本当に削除しますか?</p>
							<p>※二度と復旧はできません</p>
							<button>削除</button>
							<input type="hidden" name="action" value="delete">
							<button type="button" class="close">キャンセル</button>
							</dialog>
						</form>
						</c:when>
						<c:otherwise>
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
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<%-- [menu=3]ユーザーを選択 --%>
	<c:if test="${menu ==3}">
	<%-- 検索 --%>
	<div class="text-center background-box border m-1 bg-white">
			<form action="/InnReserve/CustomerServlet" method="get">
					<c:choose>
					<c:when test="${not empty Name }">
						<input type="text" name="name" placeholder="?名前:" maxlength="50" class="textbox-size" value="${Name}">
					</c:when>
					<c:otherwise>
						<input type="text" name="name" placeholder="?名前:" maxlength="50" class="textbox-size">
					</c:otherwise>
					</c:choose>	
					<c:choose>
					<c:when test="${not empty Email }">
						<input type="text" name="email" placeholder="?メールアドレス:" maxlength="50" class="textbox-size" value="${Email }">
					</c:when>
					<c:otherwise>
						<input type="text" name="email" placeholder="?メールアドレス:" maxlength="50" class="textbox-size">
					</c:otherwise>
					</c:choose>	
			<div><button class="b">検索</button>
			<input type="hidden" name="action" value="search"></div>
		</form>
		</div>
	<h1 class="text-left">|ユーザ一覧</h1>
		<%-- 一覧表の作成 --%>
		<table border="1" align="center" class="fixed-table">
			<tr>
				<th>NO</th>
				<th>名前</th>
				<th>電話番号</th>
				<th>メールアドレス</th>
				<th>ポイント</th>
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
						<td class="fixed-b-1">${item.id}</td>
					<td>${item.name }</td>
					<td class="fixed-b-3">${item.tel }</td>
					<td>${item.email }</td>
					<td>${item.point }pt</td>
					<%-- 変更ボタン --%>
					<td class="fixed-b-1">
						<form action="/InnReserve/CustomerServlet" method="get">
							<button class="button">変更</button>
							<input type="hidden" name="action" value="edit"> <input
								type="hidden" name="userId" value="2">
							<%-- 管理者[userId:2] --%>
							<input type="hidden" name="id" value="${item.id }">
						</form>
					</td>
					<%-- 削除ボタン --%>
					<td class="fixed-b-1">
					<c:choose>
						<c:when test="${item.delete_flag eq true }">
						<form action="/InnReserve/CustomerServlet" method="post">
							<input type="hidden" name="userId" value="2">
							<%-- 管理者[userId:2] --%>
							<input type="hidden" name="id" value="${item.id}">
							<%-- 削除(ダイアログ付き) --%>
							<button type="button" class="show">削除</button>
							<dialog id="dialog"> 
							<p>本当に削除しますか?</p>
							<p>※二度と復旧はできません</p>
							<button>削除</button>
							<input type="hidden" name="action" value="truedelete">
							<button type="button" class="close">キャンセル</button>
							</dialog>
						</form>
						</c:when>
						<c:otherwise>
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
						</c:otherwise>
						</c:choose>
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