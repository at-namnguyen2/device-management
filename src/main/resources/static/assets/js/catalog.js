
console.log("fhffgfhfhfhf");


//$(document).ready(function() {
//      console.log("fhfhfhfhf");
	
//		$.ajax({
//			url : '/userapi/myprofile',
//			
//			//url: '/my-profile',
//			type : 'get',
//			success : function(value) {
//				console.log(value);
//	            var birthday=new Date(value.dateOfBirth).Format("dd/MM/yyyy");
//				sessionStorage.setItem("fullname", value.name);
//				$('.fullname').text(value.name);
//				$('.email').text(value.email);
//				$('.phone').text(value.phone);
//				$('.team').text(value.team);
//				$('.address').text(value.address);
//				$('.dateOfBirth').text(birthday);
//				$('.data-avatar').attr('src',"/assets/img/"+value.avatar);  
//			},
//			error : function(err) {
//				console.log(err);
//				alert(JSON.stringify(err));
//			}
//		})
//		
				$.ajax({
			url : '/allCatalog',
			type : 'get',
			success : function(value) {
				console.log(value);
//				$('.saname1').text(value[0].name);
//				$('.saphone1').text(value[0].phone);
				 $.each(value, function (key, contact) {
					 var name = contact.name;
					 console.log("vnvnvnvnvnvnv" + name);
//	                    var adminphone = contact.phone; // Change here
//	                    console.log(adminphone);
	                    //if(!key==0){    
	                   // $(".SaOther").append("<ul class=\"list-group list-group-flush\"><li class=\"list-group-item\"><div class=\"image mr-3  float-left\"><img class=\"user_avatar\" src=\"/assets/img/dummy/u1.png\" alt=\"User Image\"></div><h6 class=\"p-t-10\">"+contact.name+"</h6><span> "+contact.phone+"</span></li></ul>");
                        
	                    $(".tong_tb").append("<tr class=\"a\"><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_1\" required><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td class=\"b\"><div class=\"icon icon-laptop_mac s-24 mr-3 float-left\"><span class=\"   circle\"></span></div><div class=\"thiet_bi\"> " + contact.name +  "</div></td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a><a href=\"#step-22\"><i class=\"icon-pencil\"></i></a></td></tr>");	                    
//$('.thiet_bi').text(contact.name)  ;
	                    })
			},
			error : function(err) {
				console.log(err);
				alert(JSON.stringify(err));
			}
		});
	//});