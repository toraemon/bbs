<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat" %>
<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String today = sdf.format(date);
	request.setAttribute("today", today);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<title>新規投稿</title>
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/contributionCheck.js"></script>
	<link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<link rel="stylesheet" media="all" type="text/css" href="css/jquery-ui-timepicker-addon.css" />
	
	<script src="js/jquery.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
	<script src="js/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript">
		function entryChange(){
			radio = document.getElementsByName('type')
			if(radio[0].checked) {
			//フォーム
			document.getElementById('default').style.display = "";
			document.getElementById('manual').style.display = "none";
			}else if(radio[1].checked) {
			//フォーム
			document.getElementById('default').style.display = "none";
			document.getElementById('manual').style.display = "";
			}
		}
		//オンロードさせ、リロード時に選択を保持
		window.onload = entryChange;
	</script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/> <!-- ヘッダーの読み込み -->
	</header>
	<section>
		<jsp:include page="/WEB-INF/jsp/noscript.jsp"/>
		<p id="caution">
			<c:if test="${not empty ErrorMsg}">
				${ErrorMsg}
			</c:if>
		</p>
		<form name="contribution" action="/bbs/ContributionServlet" onsubmit="return checkFormat(this)" method="post">
			<table>
				<c:choose>
					<c:when test="${not empty contribution}">
						<tr><th>件名</th><td><input type="text" name="title" size="40" maxlength="50" value="${contribution.title}"></td></tr>
						<tr><th>本文</th><td><textarea name="text" rows="5" cols="40" maxlength="1000">${contribution.text}</textarea></td></tr>
					</c:when>
					<c:otherwise>
						<tr><th>件名</th><td><input type="text" name="title" size="40" maxlength="50" placeholder="件名を入力してください。">
						<tr><th>本文</th><td><textarea name="text" rows="5" cols="40" maxlength="1000" placeholder="本文を入力してください。"></textarea></td></tr>		
					</c:otherwise>
				</c:choose>
				<tr>
					<th rowspan="2">公開日</th>
					<td>
						<label><input type="radio" name="type" value="present" onclick="entryChange();" checked="checked"/><c:set var="val" value="現在時刻を用いる"/>現在時刻を用いる</label>
						<label><input type="radio" name="type" value="reserve" onclick="entryChange();"/>投稿日を指定</label>
					</td>
				</tr>
				<tr id="default">
					<td>
						${today}
						<input type="hidden" name="date" value="${today}">
					</td>
				</tr>
				<tr id="manual">
					<td>
						<input type="text" id="dtp" size="30" name="date" placeholder="過去には投稿できません。"/>
					</td>
				</tr>
			</table>
			<input type="hidden" name="userId" value="${loginAccount.id}"><br>
			<input type="submit" value="投稿">
		</form>
	</section>
	<hr>
	<a href="/bbs/RenewalServlet">メインページへ</a><br>
	<script type="text/javascript">
		$(function(){
			var op = {
				closeText: '閉じる',
				currentText: '現在日時',
				timeOnlyTitle: '日時を選択',
				timeText: '時間',
				hourText: '時',
				minuteText: '分',
				//secondText: '秒',
				//millisecText: 'ミリ秒',
				//microsecText: 'マイクロ秒',
				timezoneText: 'タイムゾーン',
				prevText: '&#x3c;前',
				nextText: '次&#x3e;',
				monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
				monthNamesShort: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
				dayNames: ['日曜日','月曜日','火曜日','水曜日','木曜日','金曜日','土曜日'],
				dayNamesShort: ['日','月','火','水','木','金','土'],
				dayNamesMin: ['日','月','火','水','木','金','土'],
				weekHeader: '週',
				dateFormat: 'yy-mm-dd',
				firstDay: 0,
				isRTL: false,
				showMonthAfterYear: true,
				yearSuffix: '年',
				minDate: ''
				};
			$('#dtp').datetimepicker( op );
		});
	</script>
</body>
</html>