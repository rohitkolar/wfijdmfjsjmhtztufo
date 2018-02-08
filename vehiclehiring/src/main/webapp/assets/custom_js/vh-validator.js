// Pre-requisite jquery 1.7 or higher version

// Display message
function showMessage(id, alertBox, msg) {
	$(alertBox).text(msg);
	$(id).focus();
}

// Clears message
function clearMessage(alertBox) {
	$(alertBox).text('');
}

// Checks whether field is empty or not
function isEmptyField(id) {
	var text = $(id).val();
	
	if (text == null) {
		return true;
	}
	text = text.trim();

	// Get the length
	var len = text.length;
	// Compare length
	if (len == 0) {
		$(id).val('');
		return true;
	} else {
		return false;
	}
}