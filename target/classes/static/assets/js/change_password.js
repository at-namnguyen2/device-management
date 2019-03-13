$(document).ready(function() {
 console.log("test-change-password");
 
//to do change password
	$('#checkPassword').click(function() {
		console.log("tu viet van");
		var URL = "/my-profile/change-password";
		var passwordForm = $('#demo-form2').serializeJSON();
		console.log(passwordForm);
		var jsonStringPassword = JSON.stringify(passwordForm);
		console.log(jsonStringPassword);
		$.ajax({
			url : URL,
			type : 'PUT',
			contentType : "application/json; charset=utf-8",
			data : jsonStringPassword,
			dataType : 'json',
			complete : function(res) {
				if (res.status === 200 || res.status ===201){
					$('.msg-error').fadeIn(1000);
					$('.msg-error').addClass('btn-success');
					$('.msg-error').text('Update Password Success!');
					setTimeout(function() {
						$('.msg-error').fadeOut(1000);
					}, 8000);
				}
				if (res.status===409 || res.status===400){
					$('.msg-error').fadeIn(1000);
					$('.msg-error').addClass('btn-danger');
					$('.msg-error').text(res.responseText);
					setTimeout(function() {
						$('.msg-error').fadeOut(1000);
					}, 8000);
				}
			}
		});
	})
 
});