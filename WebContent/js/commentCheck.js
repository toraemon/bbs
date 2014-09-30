function checkFormat(obj){	
	var msg = "";
	if(!((0<obj.comment.value.length)&&(obj.comment.value.length<=500))){
		msg += "コメントは500文字以下で必ず入力してください。\n"
	}
	if(obj.comment.value.match("^\\s")){
		msg += "行頭に空白は禁止されています。";
	}
	if(msg.length==0){
		return true;
	}else{
		alert(msg);
		return false;
	}
}