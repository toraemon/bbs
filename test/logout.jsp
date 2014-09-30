<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="refresh" content="3; URL=/bbs/index.jsp">
		<title>掲示板システム</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<section>
		<c:if test="${not empty ErrorMsg}">
			<p id="caution">
				${ErrorMsg}
			</p>
		</c:if>
		<c:if test="${not empty LogoutMsg}">
			<p>
				${LogoutMsg}<br>
			</p>
		</c:if>
		<img src="images/neko.jpg" width="100" height="100" alt="猫の写真">
		<p><a href="/bbs/index.jsp">トップページへ</a></p>
	</section>
	</body>
</html>