<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>掲示板システム</title>
		<link rel="stylesheet" href="css/style.css">
		<script type="text/javascript" src="js/manageUser.js"></script>
	</head>
	<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/> <!-- ヘッダーの読み込み -->
	</header>
	<jsp:include page="/WEB-INF/jsp/noscript.jsp"/>
	<div id="main">
		<!-- <c:if test="${not empty ErrorMsg}">
			<p id="caution"><c:out value="${ErrorMsg}"/></p>
		</c:if> -->
	<section>
		<p><a href="/bbs/RegistServlet">新規アカウント登録</a></p>
		<table>
			<tr class="design2">
				<th>管理者権限</th>
				<th>アカウント状態</th>
				<th>アカウントID</th>
				<th>アカウント名</th>
				<th>状態変更</th>
				<th>アカウント編集</th>
			</tr>
			<c:forEach var="account" items="${accountList}">
				<tr>
					<td>
						<c:choose>
							<c:when test="${account.flag==1}">
								<p class="blue">有り</p>
							</c:when>
							<c:otherwise>
								<p class="red">無し</p>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${account.alive==1}">
								<p class="blue">接続可能</p>
							</c:when>
							<c:otherwise>
								<p class="red">接続不可</p>
							</c:otherwise>
						</c:choose>
					</td>
					<td>${account.accountId}</td>
					<td>${account.name}</td>
					<c:choose>
					<c:when test="${!(loginAccount.id==account.id)}">
					<td>
						<c:choose>
							<c:when test="${account.alive==1}">
								<form action="/bbs/ChangeServlet" onsubmit="return confirmStop(this)" method="post">
									<input type="hidden" name="id" value="${account.id}"> <!-- 追加 -->
									<input type="hidden" name="accountId" value="${account.accountId}">
									<input type="hidden" name="pass" value="${account.pass}">
									<input type="hidden" name="name" value="${account.name}">
									<input type="hidden" name="flag" value="${account.flag}">
									<input type="hidden" name="alive" value="${account.alive}">
									<input type="submit" value="停止">
								</form>
							</c:when>
							<c:otherwise>
								<form action="/bbs/ChangeServlet" onsubmit="return confirmRun(this)" method="post">
									<input type="hidden" name="id" value="${account.id}">
									<input type="hidden" name="accountId" value="${account.accountId}">
									<input type="hidden" name="pass" value="${account.pass}">
									<input type="hidden" name="name" value="${account.name}">
									<input type="hidden" name="flag" value="${account.flag}">
									<input type="hidden" name="alive" value="${account.alive}">
									<input type="submit" value="復活">
								</form>
							</c:otherwise>
						</c:choose>
					</td>
					</c:when>
					<c:otherwise>
						<td>ー</td>
					</c:otherwise>
					</c:choose>
					<td>
						<form action="/bbs/EditServlet?action=edit" method="post">
							<input type="hidden" name="id" value="${account.id}">
							<input type="hidden" name="accountId" value="${account.accountId}">
							<input type="hidden" name="pass" value="${account.pass}">
							<input type="hidden" name="name" value="${account.name}">
							<input type="hidden" name="flag" value="${account.flag}">
							<input type="hidden" name="alive" value="${account.alive}">
							<input type="submit" value="編集">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
		<p><a href="/bbs/RenewalServlet">メインページへ</a></p>
	</section>
	</div>
	</body>
</html>