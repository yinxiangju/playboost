/**
 * @fileoverview User model.
 * @author royguo1988@gmail.com
 */

play.models.User = function() {
	this.nickName = null;
	this.email = null;
	this.password = null;
	this.confirmPassword = null;
	this.userGroup = null;
}

play.models.User.prototype.isValid = function() {
	if(!this.nickName || !this.email || !this.password) {
		return false;
	}
	return true;
}