// 初始化页面事件
$(function(){
	
});


// 用户注册 START //
function UserCreateForm(){
	this.validate = false;
	this.check = function(){
		if(!UserCreateForm.checkNickName()
				||!UserCreateForm.checkPassword()
				||!UserCreateForm.checkPasswordConfirm()){
			this.validate = false;
			return;
		}
		this.validate = true;
	}
}
UserCreateForm.checkNickName = function(){
	if($("#nickName").val().length < 4) {
		$("#nickName-text").html("<font color=red>昵称不能少于4个字符</font>");
		return false;
	}else {
		$("#nickName-text").html("<font color=green>正确</font>");
	}
	return true;
}
UserCreateForm.checkPassword = function(){
	if($("#password").val().length < 8) {
		$("#password-text").html("<font color=red>密码至少8个字符</font>");
		return false;
	}else {
		$("#password-text").html("<font color=green>正确</font>");
	}
	return true;
}
UserCreateForm.checkPasswordConfirm = function(){
	if($("#password").val() != $("#passwordConfirm").val()) {
		$("#passwordConfirm-text").html("<font color=red>两次输入的密码不相同</font>");
		return false;
	}else if($("#passwordConfirm").val().length < 8) {
		$("#passwordConfirm-text").html("<font color=red>密码至少8个字符</font>");
		return false;
	}else {
		$("#passwordConfirm-text").html("<font color=green>正确</font>");
	}
	return true;
}
// Ajax验证邮箱是否已存在，如果需要可以在Ajax返回后提交表单
function checkEmail(submit){
	if(!FormatValidtor.isEmailAddress($("#email").val())){
		$("#email-text").html("<font color=red>邮箱格式不正确</font>");
	}else{
		$.post(ctx+"Users/checkEmail",{email:$("#email").val()},function(result){
			if(result.valid){
				$("#email-text").html("<font color=green>邮箱可用</font>");
				if(submit){
					$("#user-register-form").submit();
					console.log("form submit");
				}
			}else{
				$("#email-text").html("<font color=red>该邮箱已被占用</font>");
			}
		});
	}
}

function createUser(){
	var userForm = new UserCreateForm();
	// 防止频繁提交表单
	$("#submitBtn").attr("disabled","disabled");
	setTimeout(function(){
		$("#submitBtn").removeAttr("disabled");	
	}, 2000);
	// 绑定input验证事件
	$("input").blur(function(){
		userForm.check();
	});
	userForm.check();
	if(userForm.validate){
		checkEmail(true);
	}else {
		console.log("form didn't submit");
	}
}
//用户注册 END //





