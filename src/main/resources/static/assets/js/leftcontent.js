	$.ajax({
		url : '/userapi/myprofile',
		type : 'get',
		success : function(value) {
			console.log(value);
			sessionStorage.setItem("fullname", value.name);			
			$('.fullname').text(value.name);
			$('.email').text(value.email);
			if(value.avatar == null || value.avatar == ""){
				$('.data-avatar').attr('src','/images/0.png');
			} else {	
				$('.data-avatar').attr('src',value.avatar); 			
			}
		},
		error : function(err) {
			console.log(err);
//			alert(JSON.stringify(err));
		}
	})
	