<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/header.css">
<div id="title">
	<img src="images/logo.png" alt="">
</div>
<nav>
	<ul>
		<c:if test="${not empty loginAccount}">
			<%	response.sendRedirect("/bbs/LogoutServlet"); %>
		</c:if>
 		<li><a href="/bbs/index.html">トップ</a></li>
 		<li><a href="/bbs/html/news.html">お知らせ</a></li>
		<li><a href="/bbs/html/about.html">わったい菜について</a></li>
		<li><a href="/bbs/html/contact.html">お問い合わせ</a></li>
	</ul>
</nav>