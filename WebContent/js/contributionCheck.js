function checkFormat(obj){
    // 入力チェック
    var msg = "";
    if(!(0<obj.title.value.length && obj.title.value.length<=50)){
        msg += "件名は50文字以内で必ず入力してください。\n";
    }
    if(!(0<obj.text.value.length && obj.text.value.length<=1000)){
        msg += "本文は1000文字以内で必ず入力してください。\n";
    }
    
    val = $("input[name='type']:checked").val();
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth()+1;		if(month<10)	month = "0" + month;
    var day = d.getDate();			if(day<10)	day = "0" + day;
    var h = d.getHours();			if(h<10)	h = "0" + h;
    var m = d.getMinutes();			if(m<10) m = "0" + m;
    var now = year+"-"+month+"-"+day+" "+h+":"+m;
     if(val==='present'){
    	 date = now;
    	 alert("present"+date);
     }else{
    	 date = document.contribution.dtp.value;
    	 //alert("実験 date="+date+" now="+now+" date<now="+(date<now));
     }
     if(date<now){
            msg += "過去には投稿できません。\n";
       }
    
    
    if(msg==""){
        return true;
    }else{
        alert(msg);
        return false;
    }
}

/**
 * イベントを登録する（IE8以下が addEventListener() に対応していないためのラッパー関数）
 *
 * @param t 対象ノード
 * @param p イベントタイプ
 * @param l 実行される関数
 */
/*var _addEvent=function(t,p,l){try{t.addEventListener(p,l,false);}catch(e){t.attachEvent("on"+p,function(e){l.call(t,e);});}};

(function(){
  _addEvent(window, "load", function(e) {
    var yearId = "year"; // 年コントロールのID
    var monthId = "month"; // 月コントロールのID
    var dayId = "day"; // 日コントロールのID

    var targetYear = document.getElementById(yearId);
    var targetMonth = document.getElementById(monthId);
    var targetDay = document.getElementById(dayId);

    _addEvent(targetYear, "change", function(e) {
      // 年コントロールを変更したとき
      nonExistDayIsNonDisplayed(this, targetMonth, targetDay);
    });
    _addEvent(targetMonth, "change", function(e) {
      // 月コントロールを変更したとき
      nonExistDayIsNonDisplayed(targetYear, this, targetDay);
    });
  });*/

  /**
   * 存在しない日（2月30日など）の選択肢を非表示にする
   *
   * @param targetYear 年コントロール
   * @param targetMonth 月コントロール
   * @param targetDay 日コントロール
   */
 /* var nonExistDayIsNonDisplayed = function(targetYear, targetMonth, targetDay) {
    var selectedMonthValue = parseInt(targetMonth.getElementsByTagName("option")[targetMonth.selectedIndex].value, 10);
    var targetDayOptions = targetDay.getElementsByTagName("option");

    if (selectedMonthValue === 2) {
      // 2月の場合
      var selectedYearValue = parseInt(targetYear.getElementsByTagName("option")[targetYear.selectedIndex].value, 10)
      var leapYear = isLeapYear(selectedYearValue); // 閏年か

      for (var i = targetDayOptions.length - 1; i >= 0; i--) {
        var targetDayOption = targetDayOptions[i];
        var dayValue = parseInt(targetDayOption.value, 10);
        if (dayValue >= 30 || (dayValue === 29 && !leapYear)) {
          targetDayOption.disabled = true; // 選択不能指定
          if (targetDayOption.selected) {
            // 29日(閏年でない場合のみ)、30日、31日のいずれかが選択されていた場合は、2月の最終日に変更
            if (leapYear) {
              targetDay.value = "29";
            } else {
              targetDay.value = "28";
            }
          }
        } else if (targetDayOption.disabled) {
          // 選択不能指定が成されていたら解除
          targetDayOption.disabled = false;
        } else {
          break;
        }
      }
    } else if (selectedMonthValue === 4 || selectedMonthValue === 6 || selectedMonthValue === 9 || selectedMonthValue === 11) {
      // 月の日数が30日の場合
      for (var i = targetDayOptions.length - 1; i >= 0; i--) {
        var targetDayOption = targetDayOptions[i];
        var dayValue = parseInt(targetDayOption.value, 10);
        if (dayValue >= 31) {
          targetDayOption.disabled = true; // 選択不能指定
          if (targetDayOption.selected) {
            // 31日が選択されていた場合は、各月の最終日に変更
            targetDay.value = "30";
          }
        } else if (targetDayOption.disabled) {
          // 選択不能指定が成されていたら解除
          targetDayOption.disabled = false;
        } else {
          break;
        }
      }
    } else {
      // 月の日数が31日の場合
      for (var i = targetDayOptions.length - 1; i >= 0; i--) {
        var targetDayOption = targetDayOptions[i];
        if (targetDayOption.disabled) {
          // 選択不能指定が成されていたら解除
          targetDayOption.disabled = false;
        } else {
          break;
        }
      }
    }
  };*/

  /**
   * 閏年か
   *
   * @param year 年
   *
   * @return 閏年ならtrue、それ以外の場合はfalse
   */
  /*var isLeapYear = function(year) {
    return new Date(year, 1, 29).getMonth() === 1;
  };
})();*/

/*
//年月に応じて日付を変更する関数setDateForm
function setDateForm(selectYear,selectMonth,selectDay){
    year = document.getElementById(selectYear).value;
    month = parseInt(document.getElementById(selectMonth).value);
    selMonth = document.getElementById(selectMonth);
    selDay = document.getElementById(selectDay);
    // 日付optionの初期化
    if(selDay.options[28] == null){
        selDay.appendChild(document.createElement("option"));
        selDay.options[28].value = "29";
        selDay.options[28].text = "29";
    }
    if(selDay.options[29] == null){
        selDay.appendChild(document.createElement("option"));
        selDay.options[29].value = "30";
        selDay.options[29].text = "30";
    }
    if(selDay.options[30] == null){
        selDay.appendChild(document.createElement("option"));
        selDay.options[30].value = "31";
        selDay.options[30].text = "31";
    }

    // 月に応じて日付optionのノードを削除
    if(month == 2){
        selDay.removeChild(selDay.options[30]);
        selDay.removeChild(selDay.options[29]);
        // 閏年ではない場合
    if(year%4 != 0){
            selDay.removeChild(selDay.options[28]);
        }
    }else if(month == 4 || month == 6 || month == 9 || month == 11){
        selDay.removeChild(selDay.options[30]);
    }
}*/


