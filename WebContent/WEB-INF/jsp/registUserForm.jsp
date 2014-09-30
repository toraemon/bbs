<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>掲示板システム(登録情報画面)</title>
		<link rel="stylesheet" href="css/style.css">
		<script type="text/javascript" src="js/registUser.js"></script>
	</head>
	<body>
		<header>
			<jsp:include page="/WEB-INF/jsp/header.jsp"/> <!-- ヘッダーの読み込み -->
		</header>
		<jsp:include page="/WEB-INF/jsp/noscript.jsp"/>
		<section>
		<c:if test="${not empty ErrorMsg}">
			<p id="caution">${ErrorMsg}</p>
		</c:if>
		<form action="/bbs/RegistServlet" onsubmit="return checkFormat(this)" method="post">
			<table>
				<c:choose>
					<c:when test="${empty registAccount}">
						<tr><th>アカウントID</th><td><input type="text" size="40" name="accountId" placeholder="半角英数字で6文字以上20文字以下"></td></tr>
						<tr><th>パスワード</th><td><input type="password" size="40" name="pass" placeholder="全半角文字で6文字以上255文字以下"></td></tr>
						<tr><th>確認用パスワード</th><td><input type="password" size="40" name="passCheck" placeholder="確認としてパスワードを入力してください"></td></tr>
						<tr><th>アカウント名</th><td><input type="text" size="40" name="name" placeholder="1文字以上10文字以下" required></td></tr>
					</c:when>
					<c:otherwise>
						<tr><th>アカウントID</th><td><input type="text" size="40" name="accountId" value="${registAccount.accountId}"></td></tr>
						<tr><th>パスワード</th><td><input type="password" size="40" name="pass" value="${registAccount.pass}"></td></tr>
						<tr><th>確認用パスワード</th><td><input type="password" size="40" name="passCheck" value="${registAccount.passCheck}"></td></tr>
						<tr><th>アカウント名</th><td><input type="text" size="40" name="name" value="${registAccount.name}" required></td></tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<th>管理者権限</th>
						<td>
							<label><input type="radio" name="flag" value="1" required>有り</label>
							<label><input type="radio" name="flag" value="0" required checked>無し</label>
						</td>
				</tr>
				<tr>
					<th>アカウント状態</th>
						<td>
							<label><input type="radio" name="alive" value="1" required checked>有効</label>
							<label><input type="radio" name="alive" value="0" required>無効</label>
						</td>
				</tr>
			</table><br>
			<input type="submit" value="登録">
		</form>
		<hr>
		<a href="/bbs/RenewalServlet">メインページへ</a>
		</section>
	</body>
</html>