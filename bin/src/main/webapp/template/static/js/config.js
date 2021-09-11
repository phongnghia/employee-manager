/**
 * 
 */
function check(email) {
	let text = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (text.test(email) == true) {
		return true;
	} else {
		return false;
	}
}