<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>掲示板システム</title>
		<link rel="stylesheet" href="css/index.css">
		<script type="text/javascript" src="js/index.js"></script>
	</head>
	<body>
		<header>
			<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		</header>
		<section>
			<h1>
				<img src="images/logo.png" alt="">
			</h1>
			<h2>掲示板へようこそ!!</h2>
			<p class="ledText"><span>Come On and Join Us!! 業界の輪を広げよう!!</span></p>
			<p>
				<span id="caution">　
				　　<c:if test="${not empty ErrorMsg}">
						<c:out value="${ErrorMsg}"/>
					</c:if>
				</span>
				<span id="caution2">
					<c:if test="${not empty Msg}">
						<c:out value="${Msg}"/>
					</c:if>
				</span>
			</p>
			<form action="/bbs/LoginServlet" onsubmit="return checkFormat(this)" method="post">
				<table>
					<tr>
						<th class="pic"><img src="images/account.png" alt="アカウントの写真" width="30" height="30"></th>
						<td id="accountId"><input type="text" name="accountId" placeholder="アカウントID" required></td>
					</tr>
					<tr>
						<th class="pic"><img src="images/key.png" alt="パスワードの写真" width="30" height="30"></th>
						<td id="password"><input type="password" name="pass" placeholder="パスワード" required></td>
					</tr>
				</table>
				<input id="loginBtn" type="submit" value="ログイン"><br>
				<a href="/bbs/ForgotServlet">※パスワードを忘れた方はこちら。</a>
			</form>
		</section>
		<footer>
			<small>Copyright &copy;わったい菜, all rights reserved.</small>
		</footer>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>
</html>