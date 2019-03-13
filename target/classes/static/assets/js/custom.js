$(document).ready(function() {
	alert("thong bao2");
	$("#btnlogin").click(function() {

		var name = $("#name").val();	
		var lastname = $("#lastname").val();
		var email = $("#email").val();
		var password = $("#password").val();

		var JSONObject = {
			"name" : name,
			"lastname" : lastname,
			"email" : email,
			"password" : password
		};
		var jsondata = JSON.stringify(JSONObject);
		console.log(jsondata);
		$.ajax({
			contentType : "application/json",
			url : "/registration",
			type : "POST",
			dataType : "json",
			data : jsondata,
			success : function(value) {

			},
			error : function(err) {
				console.log(err);
				alert(JSON.stringify(err));
			}
		})
	});
})