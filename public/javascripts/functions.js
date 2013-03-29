// 格式化工具类
var FormatValidtor = new Function();
FormatValidtor.isEmailAddress = function(address){
	arr = address.match(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);
	if(arr!=null){
		return true;
	}
	return false;
}