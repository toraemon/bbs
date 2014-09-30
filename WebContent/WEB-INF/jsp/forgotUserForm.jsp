<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<title>掲示板</title>
		<link rel="stylesheet" href="css/forgot.css">
		<script type="text/javascript">
			function checkFormat(obj){
				var pass = obj.pass.value.replace(/\s+/g, "");
				var passCheck = obj.passCheck.value.replace(/\s+/g, "");
				var msg = "";
				if(!pass.match("[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}")){
					msg += "パスワードは全半角文字で6から255文字で入力してください。\n";
				}
				if(pass!==passCheck){
					msg += "パスワードが一致しません。\n";
				}
				if(msg.length===0){
					return true;
				}else{
					alert(msg);
					return false;
				}
			}
		</script>
	</head>
	<body>
		<header>
			<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		</header>
		<h3>パスワードの変更</h3>
		<article>
			<p>パスワードを入力してください。</p>
			<p>アカウントIDを忘れた方はお手数ですが、<a href="html/contact.html">お問い合わせ</a>から問い合わせ下さい。</p>
			<c:if test="${not empty ErrorMsg}">
				<p id="caution"><c:out value="${ErrorMsg}"/></p>
			</c:if>
			<form action="ForgotServlet" onsubmit="return checkFormat(this)" method="post">
				<table>
					<tr><th>アカウントID</th><td><input type="text" name="accountId" size="40" placeholder="アカウントIDを入力してください。"></td></tr>
				</table>
				<table>
					<tr><th>パスワード</th><td><input type="password" name="pass" size="40" placeholder="全半角文字で6～255文字で入力してください。"></td></tr>
					<tr><th>確認用パスワード</th><td><input type="password" name="passCheck" size="40" placeholder="確認用パスワードを入力してください。"></td></tr>
				</table>
				<input type="submit" value="送信">
			</form>
		</article>
	</body>
</html>