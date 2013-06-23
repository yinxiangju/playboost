// 格式化工具类
var FormatValidtor = new Function();
FormatValidtor.isEmailAddress = function(address){
	var arr = address.match(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);
	if(arr!=null){
		return true;
	}
	return false;
}

play.utils.FormatValidator = function(){}

play.utils.FormatValidator.prototype.isEmailAddress = function(address) {
	var arr = address.match(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);
	if(arr!=null){
		return true;
	}
	return false;
}

play.utils.FormatValidator.prototype.fitLength = function(text, min, max) {
	if(!text || text.length > max || text.length < min) {
		return false;
	}
	return true;
}