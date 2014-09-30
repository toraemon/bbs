<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<title>時間まで選択できるDatepickerプラグインTimepicker。を日本語化</title>

<link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />

<script src="../js/jquery.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
<script src="../js/jquery-ui-timepicker-addon.js"></script>

<style type="text/css">
body
{
	font-size: 12px;
}
</style>

<script type="text/javascript">
<!--

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
		dateFormat: 'yy-mm-d',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: '年',
		minDate: ''
	};
		
$('#dtp').datetimepicker( op );
});
// -->
</script>
</head>
<body>
	<input type="text" id="dtp" size="30">
	<script type="text/javascript">
	/*var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth();
	var date = today.getDate()+1;
	var hour = today.getHours();
	var minute = today.getMinutes();*/
	/* $(function () {  
		
		var today = new Date();
		alert(today);
		$("#dtp").datepicker({ minDate: today});  
		$("#dtp").datepicker();  
		}); */
		/*var today = new Date();
		$("#dtp").datepicker({
		    //dateFormat:	"yy-mm-dd H:m",
		   // maxDate: "+1m",
		    //minDate: "-1d"
		});*/
	</script>
</body>
</html>