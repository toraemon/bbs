
function checkFormat(obj){
	// トリミング処理
	obj.accountId.value = obj.accountId.value.replace(/\s+/g, "");
	obj.pass.value=obj.pass.value.replace(/\s+/g, "");
	obj.passCheck.value=obj.passCheck.value.replace(/\s+/g, "");
	obj.name.value=obj.name.value.replace(/\s+/g, "");
	// 入力チェック
	var msg = "";
	if(!obj.accountId.value.match("[a-zA-Z0-9]{6,20}")){
		msg += "登録できるアカウントIDは半角英数字で6文字以上20文字以下です。\n";
	}
	if(!obj.pass.value.match("[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}")){
		msg += "登録できるパスワードは全半角文字で6文字以上255文字以下です。\n";
	}
	var pass = obj.pass.value;
	var passCheck = obj.passCheck.value;
	if(!(passCheck==pass)){
		msg += "パスワードが一致しません。\n";
	}
	if(!((0<obj.name.value.length)&&(obj.name.value.length<=10))){
		msg += "名前は10文字以下で必ず入力してください。\n"
	}
	var i;
	for(i=0;i<obj.flag.length;i++){
		if(obj.flag[i].checked)	break;
	}
	if(obj.flag.length<=i){
		msg += "管理者権限の有無をチェックしてください。\n";
	}
	for(i=0;i<obj.alive.length;i++){
		if(obj.alive[i].checked)	break;
	}
	if(obj.alive.length<=i){
		msg += "ユーザー状態をチェックしてください。\n";
	}
	if(msg.length==0){
		return true;
	}else{
		window.alert(msg);
		return false;
	}
}