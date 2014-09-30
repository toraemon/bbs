<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>掲示板システム</title>
		<link rel="stylesheet" href="css/main.css">
		<script type="text/javascript" src="js/commentCheck.js"></script>
	</head>
	<body>
		<header>
			<jsp:include page="/WEB-INF/jsp/header.jsp"/> <!-- ヘッダーの読み込み -->
		</header><br>
		<jsp:include page="/WEB-INF/jsp/noscript.jsp"/>
		<section>
			<div id="information">
				<h2>アカウント情報</h2>
				<div id="account">
					アカウント:<span class="name">${loginAccount.name}</span>さま&nbsp;&nbsp;
					管理者権限:
					<c:choose>
						<c:when test="${loginAccount.flag==1}">
							<span class="blue">有り</span>
						</c:when>
						<c:otherwise>
							<span class="red">無し</span>
						</c:otherwise>
					</c:choose>
				</div>
				<h2>メニュー</h2>
				<div>
					<a href="/bbs/ContributionServlet">新規投稿</a>&nbsp;&nbsp;
					<c:if test="${loginAccount.flag==1}">
						<a href="/bbs/ManageServlet">アカウント編集</a>&nbsp;&nbsp;
					</c:if>
					<a href="/bbs/LogoutServlet?mode=logout">ログアウト</a>
				</div>
			</div>
			<c:if test="${not empty ErrorMsg}">
				<p id="caution">${ErrorMsg}</p>
			</c:if>
			<c:if test="${contributionList==null}">
				<h3>現在投稿がありません。</h3>
			</c:if>
			<div id="bbs">
				<c:forEach var="contribution" items="${contributionList}">
					<table>
						<tr>
							<th class="left" id="title+${contribution.title}">件名:${contribution.title}</th>
						</tr>
						<tr>
							<td class="left"><pre>${contribution.text}</pre></td>
						</tr>
						<tr>
							<th class="right">投稿者:${contribution.name}&nbsp;&nbsp;&nbsp;投稿日:${contribution.date}</th>
						</tr>
							<tr>
								<td>
									<div id="commentTitle">
										&lt;&lt;コメント欄&gt;&gt;
									</div>
								</td>
							</tr>
								<c:forEach var="comment" items="${commentList}">
									<c:if test="${contribution.id==comment.conId}">	
										<tr>
											<td style="text-align:left;"><pre>${comment.comment}</pre></td>
										</tr>
										<tr>
											<td style="text-align:right;">${comment.name}&nbsp;&nbsp;&nbsp;${comment.date}</td>
										</tr>
									</c:if>
								</c:forEach>
							<tr>
							<td>
								<form action="/bbs/CommentServlet" onsubmit="return checkFormat(this)" method="post">
									<input type="hidden" name="userId" value="${loginAccount.id}">
									<input type="hidden" name="conId" value="${contribution.id}">
									<textarea class="commentTextField" name="comment"></textarea>
									<input type="submit" value="コメント">
								</form>
							</td>
						</tr>			
					</table>
				</c:forEach><br>
				<a href="#bbs">上へ</a>
			</div>
		</section>
	</body>
</html>