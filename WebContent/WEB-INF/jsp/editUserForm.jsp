<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>掲示板システム</title>
		<link rel="stylesheet" href="css/style.css">
		<script type="text/javascript" src="js/editUser.js"></script>
	</head>
	<body>
		<header>
			<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		</header>
		<jsp:include page="/WEB-INF/jsp/noscript.jsp"/>
		<section>
		<c:if test="${not empty ErrorMsg}">
			<p id="caution">${ErrorMsg}</p>
		</c:if>
		<form action="/bbs/EditServlet?action=editDone" onsubmit="return checkFormat(this)" method="post">
			<table>
				<c:choose>
					<c:when test="${empty editAccount}">
						<tr><th>アカウントID</th><td><input type="text" name="accountId" size="40" value="${account.accountId}"></td></tr>
						<tr><th>パスワード</th><td><input type="password" name="pass" size="40" placeholder="未入力の場合はパスワード変更は行いません。"></td></tr>
						<tr><th>確認用パスワード</th><td><input type="password" name="passCheck" size="40" placeholder="確認用パスワードを入力してください。"></td></tr>
						<tr><th>アカウント名</th><td><input type="text" name="name" size="40" value="${account.name}"></td></tr>
					</c:when>
					<c:otherwise>
						<tr><th>アカウントID</th><td><input type="text" name="accountId" size="40" value="${editAccount.accountId}"></td></tr>
						<tr><th>パスワード</th><td><input type="password" name="pass" size="40" value="${editAccount.pass}"></td></tr>
						<tr><th>確認用パスワード</th><td><input type="password" name="passCheck" size="40" value="${editAccount.passCheck}"></td></tr>
						<tr><th>アカウント名</th><td><input type="text" name="name" size="40" value="${editAccount.name}"></td></tr>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${!(loginAccount.id==account.id)}">	
						<tr>
							<th>管理者権限</th>
							<td>
								<c:choose>
									<c:when test="${account.flag == 1}">
										<label><input type="radio" name="flag" value="1" checked>有り</label>
										<label><input type="radio" name="flag" value="0">無し</label>
									</c:when>
									<c:otherwise>
										<label><input type="radio" name="flag" value="1">有り</label>
										<label><input type="radio" name="flag" value="0" checked>無し</label>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>アカウント状態</th>
							<td>
								<c:choose>
									<c:when test="${account.alive == 1}">
										<label><input type="radio" name="alive" value="1" checked>有効</label>
										<label><input type="radio" name="alive" value="0">無効</label>
									</c:when>
									<c:otherwise>
										<label><input type="radio" name="alive" value="1">有効</label>
										<label><input type="radio" name="alive" value="0" checked>無効</label>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="flag" value="1">
						<input type="hidden" name="alive" value="1">
					</c:otherwise>
				</c:choose>
			</table><br>
			<input type="hidden" name="id" value="${account.id}">
			<input type="submit" value="変更" onclick="return changeConfirm()">
			<c:if test="${!(loginAccount.id==account.id)}">
				<a href="/bbs/DeleteServlet">
					<input type="button" value="削除" onclick="return deleteConfirm()">
				</a>
			</c:if>
		</form>
		<hr>
		<a href="/bbs/RenewalServlet">メインページへ</a>
		</section>
	</body>
</html>