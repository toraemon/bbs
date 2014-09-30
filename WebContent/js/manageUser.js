function confirmStop(obj){
	var ans = confirm(obj.accountId.value+'のアカウントを停止させますか?');
	if(!ans)	return false;
}
function confirmRun(obj){
	var ans = confirm(obj.accountId.value+'のアカウントを復活させますか?');
	if(!ans)	return false;
}