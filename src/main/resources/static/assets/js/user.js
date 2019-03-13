console.log("fhffgfhfhfhf");



$(document).ready(function() {
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
////		
				$.ajax({
			url : '/api/users/get-all-user',
			type : 'get',
			success : function(value) {
				console.log(value);
		   
				
//				$('.saname1').text(value[0].name);
//				$('.saphone1').text(value[0].phone);
				var trang_thai = "";
				 $.each(value, function (key, contact) {
					 var email = contact.email;
					 console.log(email);
					 //var name = contact.name;
					 //console.log("vnvnvnvnvnvnv" + name);
//	                    var adminphone = contact.phone; // Change here
//	                    console.log(adminphone);
	                   // if(!key==0){
	                    	
	                    	//$(".do-user").append("<tr><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" required><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td><div class=\"avatar avatar-md mr-3 mt-1 float-left\"><span class=\"avatar-letter avatar-letter-a  avatar-md circle\"></span></div><div><div><strong>"+contact.employeeName+"</strong></div><small>"+contact.email+"</small></div></td><td>"+contact.team+"</td><td>"+contact.phone+"</td><td><span class=\icon icon-circle s-12  mr-2 text-warning\"></span>"+contact.active+"</td><td><span class=\"r-3 badge badge-success\">"+contact.roleName+"</span></td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a><a href=\"panel-page-profile.html\"><i class=\"icon-pencil\"></i></a></td></tr>");
	                    	 if(contact.active === true && contact.nonLocked === true) {
	                    		 trang_thai = 'Active';
	                    	 } else if(contact.active === true && contact.nonLocked === false) {
	                    		 trang_thai = 'Block';
	                    	 }
	                    	
	                    	//$(".do-user").append("<tr><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" required><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td><div class=\"avatar avatar-md mr-3 mt-1 float-left\"><span class=\"avatar-letter avatar-lg  circle\"><img src=\""+contact.avatar+"\" class=\"user-image avatar-user\" alt=\"User Image\"></span></div><div><div><strong>"+contact.employeeName+"</strong></div><small>"+contact.email+"</small></div></td><td>"+contact.team+"</td><td>"+contact.phone+"</td><td><span class=\icon icon-circle s-12  mr-2 text-warning\"></span>"+trang_thai+"</td><td><span class=\"r-3 badge badge-success\">"+contact.roleName+"</span></td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a><a href=\"panel-page-profile.html\"><i class=\"icon-pencil\"></i></a></td></tr>");
	                    	
	                    	$(".do-user").append("<tr><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle \"><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td><div class=\"avatar avatar-md mr-3 mt-1 float-left\"><span class=\"avatar-letter avatar-lg  circle\"><img src=\""+contact.avatar+"\" class=\"user-image avatar-user\" alt=\"User Image\"></span></div><div><div><strong>"+contact.employeeName+"</strong></div><small>"+contact.email+"</small></div></td><td>"+contact.team+"</td><td>"+contact.phone+"</td><td><span class=\"r-3 badge badge-primary\">"+trang_thai+"</span></td><td><span class=\"r-3 badge badge-success\">"+contact.roleName+"</span></td><td><a href=\"#\" onclick=\"deleteUser("+contact.id+")\"><i class=\"icon-eye mr-3\"></i></a><a href=\"#editProfile\" target=\"#editProfile\" data-toggle=\"modal\" class=\"viewDetail\"><i class=\"icon-pencil\" onclick=\"editInfoProfile("+contact.id+")\"></i></a></td></tr>");
	                    	
	                    	
	                    	//<a href=\"#viewProfile\" target=\"#viewProfile\" data-toggle=\"modal\" class=\"btn btn-success btn-sm mt-3 viewDetail\">View Profile</a>
	                    	
	                    	
	                    	
	                   // }
	                    });
			},
	                   // $(".SaOther").append("<ul class=\"list-group list-group-flush\"><li class=\"list-group-item\"><div class=\"image mr-3  float-left\"><img class=\"user_avatar\" src=\"/assets/img/dummy/u1.png\" alt=\"User Image\"></div><h6 class=\"p-t-10\">"+contact.name+"</h6><span> "+contact.phone+"</span></li></ul>");
                        
	                    //$(".tong_tb").append("<tr class=\"a\"><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_1\" required><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td class=\"b\"><div class=\"icon icon-laptop_mac s-24 mr-3 float-left\"><span class=\"   circle\"></span></div><div class=\"thiet_bi\"> " + contact.name +  "</div></td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a><a href=\"#step-22\"><i class=\"icon-pencil\"></i></a></td></tr>");	                    
//$('.thiet_bi').text(contact.name)  ;
	                   // })
	                   
//			},
			error : function(err) {
				console.log(err);
				alert(JSON.stringify(err));
			}
				 }),
				 
				 

					$.ajax({
				url : '/api/users/get-all-admin',
				type : 'get',
				success : function(value) {
					console.log(value);
			        var gt = '';
			        var a = value.gender;
			        if(a == true) {
			        	gt = 'male';
			        } else {
			        	gt = 'female'
			        }
			        console.log(gt+" ");
			        console.log(value.email);
					 
					var i = 0;
					var a = 'b';
					var id = 1;
//					$('.saname1').text(value[0].name);
//					$('.saphone1').text(value[0].phone);
					 $.each(value, function (key, contact) {
						 var email = contact.email;
						 console.log(email);
						 console.log("////////////" + contact.employeeName);
						 
						 //var name = contact.name;
						 //console.log("vnvnvnvnvnvnv" + name);
//		                    var adminphone = contact.phone; // Change here
//		                    console.log(adminphone);
		                   // if(!key==0){		                    	
		                    	//$(".display-admin").append("<div class=\"col-md-3 my-3\"><div class=\"card no-b\"><div class=\"card-body text-center p-5\"><div class=\"avatar avatar-xl mb-3\"><img  src=\""+contact.avatar+"\" alt=\"User Image\"></div><div><h6 class=\"p-t-10\">"+contact.employeeName+"</h6>"+contact.email+"</div><a href=\"#\" class=\"btn btn-success btn-sm mt-3\">View Profile</a></div></div></div>");
						 //$(".display-admin").append("<div  class=\"viewDetail col-md-3 my-3\"><div class=\"card no-b\"><div class=\"card-body text-center p-5\"><div class=\"avatar avatar-xl mb-3\"><img  src=\""+contact.avatar+"\" alt=\"User Image\"></div><div><h6 class=\"p-t-10\">"+contact.employeeName+"</h6>"+contact.email+"</div><a href=\"#viewProfile\" target=\"#viewProfile\" data-toggle=\"modal\" class=\"btn btn-success btn-sm mt-3 viewDetail\">View Profile</a></div></div></div>");
						 //$('.email2').val('jjj');
						 
						 $(".display-admin").append("<div  class=\"viewDetail col-md-3 my-3\"><div class=\"card no-b\"><div class=\"card-body text-center p-5\"><div class=\"avatar avatar-xl mb-3\"><img  src=\""+contact.avatar+"\" alt=\"User Image\"></div><div><h6 class=\"p-t-10\">"+contact.employeeName+"</h6>"+contact.email+"</div><a href=\"#viewProfile\" target=\"#viewProfile\" data-toggle=\"modal\" id="+contact.id+" onclick=\"viewInfoProfile("+contact.id+")\" class=\"btn btn-success btn-sm mt-3 viewDetail\">View Profile</a><div class=\"info-hide\"><div style=\"display:none;\">"+contact.email+"</div></div></div>");
//						 var check = document.getElementsByClassName(a);
//						 //console.log("hkhkhkhhkhk"+$("div[class="+a+"]").val());
//						 console.log("//////////////"+$('.employeeName1').text());
//						console.log("llll"+ $('.address1').text() +"hjhjhj");
//						
//						// console.log(code);
//						 //var m = $('.address1').val();
						 console.log(contact.email+"zzzzz" + contact.address);
						
//						//console.log($('.'+a).text()+"gggggggg");
//						$('.email2').val($('.'+a).text());
//						a = a + 'c';
					 //console.log("kkk"+check.length);
//						 console.log("nnnnnnnnnn"+ check);					 					
//						 $('.email').val(contact.email);
//						 $('.address').val(contact.address);
//						 $('.phone').val(contact.phone);
//						 $('.name').val(contact.employeeName);
//						 $('.birthday').val(contact.birthday);
						 // }
		                    });	 
					// $('.name').text(value.emloyeeName);
					 //$('.phone').text(value.phone);
					 //$('.birthDay').text(value.birthday);
//					 $('.email').text(value.email);
//					 $('.email').text(value.email);
//					 $('.email').text(value.email);
//					 $('.email').text(value.email);
//					 $('.email').text(value.email);
					 
					 
				},
		                   // $(".SaOther").append("<ul class=\"list-group list-group-flush\"><li class=\"list-group-item\"><div class=\"image mr-3  float-left\"><img class=\"user_avatar\" src=\"/assets/img/dummy/u1.png\" alt=\"User Image\"></div><h6 class=\"p-t-10\">"+contact.name+"</h6><span> "+contact.phone+"</span></li></ul>");
	                        
		                    //$(".tong_tb").append("<tr class=\"a\"><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_1\" required><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td class=\"b\"><div class=\"icon icon-laptop_mac s-24 mr-3 float-left\"><span class=\"   circle\"></span></div><div class=\"thiet_bi\"> " + contact.name +  "</div></td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a><a href=\"#step-22\"><i class=\"icon-pencil\"></i></a></td></tr>");	                    
	//$('.thiet_bi').text(contact.name)  ;
		                   // })
		                   
//				},
				error : function(err) {
					console.log(err);
					alert(JSON.stringify(err));
				}
					 }),
					 $.ajax({
							url : '/api/users/get-all-userNotAdmin',
							type : 'get',
							success : function(value) {
								console.log(value);
						   
								
//								$('.saname1').text(value[0].name);
//								$('.saphone1').text(value[0].phone);
								 $.each(value, function (key, contact) {
									 var email = contact.email;
									 console.log("////////////////"+email);
									 //var name = contact.name;
									 //console.log("vnvnvnvnvnvnv" + name);
//					                    var adminphone = contact.phone; // Change here
//					                    console.log(adminphone);
					                   // if(!key==0){
					                    	console.log(contact.avatar);
					                    	
					                    	$('.display-allUser').append("<div class=\"col-md-3 mb-3\"><div class=\"card no-b p-3\"><div><div class=\"image mr-3 avatar-lg float-left\"><span class=\"avatar-letter avatar-lg  circle\"><img src=\""+contact.avatar+"\" class=\"user-image avatar-user\" alt=\"User Image\"></span></div><div class=\"mt-1\"><div><strong>"+contact.employeeName+"</strong></div><small>"+contact.email+"</small></div></div></div></div>");
					                    	//$('.avatar-user').attr('src', contact.avatar);
					                   // }
					                    });
							},
					                   // $(".SaOther").append("<ul class=\"list-group list-group-flush\"><li class=\"list-group-item\"><div class=\"image mr-3  float-left\"><img class=\"user_avatar\" src=\"/assets/img/dummy/u1.png\" alt=\"User Image\"></div><h6 class=\"p-t-10\">"+contact.name+"</h6><span> "+contact.phone+"</span></li></ul>");
				                        
					                    //$(".tong_tb").append("<tr class=\"a\"><td><div class=\"custom-control custom-checkbox\"><input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_1\" required><label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td><td class=\"b\"><div class=\"icon icon-laptop_mac s-24 mr-3 float-left\"><span class=\"   circle\"></span></div><div class=\"thiet_bi\"> " + contact.name +  "</div></td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a><a href=\"#step-22\"><i class=\"icon-pencil\"></i></a></td></tr>");	                    
				//$('.thiet_bi').text(contact.name)  ;
					                   // })
					                   
//							},
							error : function(err) {
								console.log(err);
								alert(JSON.stringify(err));
							}
								 });
				 				 
});
function viewInfoProfile(m) {
	//alert(m);	
	$.ajax({
		url : '/api/users/getUser/'+m,
		type : 'get',
		success : function(value) {
			$('.email2').val(value.email);
			$('.address2').val(value.address);
			$('.employeeName2').val(value.phone);
			$('.phone2').val(value.phone);
			$('.birthday2').val(value.birthday);
			$('.team').val(value.team);
			 
		},
 
		error : function(err) {
			console.log(err);
			alert(JSON.stringify(err));
		}
			 });
	
	
}

function editInfoProfile(m) {
	//alert(m);	
	$.ajax({
		url : '/api/users/getUser/'+m,
		type : 'get',
		success : function(value) {
			$('.email3').val(value.email);
			$('.address3').val(value.address);
			$('.name3').val(value.employeeName);
			$('.phone3').val(value.phone);
			$('.birthday3').val(value.birthday);
			console.log(value.roleName);
			if(value.gender === true) {
				$('.male').attr('checked','checked');
			} else {
				$('.female').attr('checked','checked');
			}
			if(value.roleName === 'USER') {
				$('.user1').attr('checked','checked');
			}
			if(value.roleName === 'ADMIN') {
				$('.admin1').attr('checked','checked');
			}
			if(value.active === true) {
				$('.active1').attr('checked','checked');
			}
			if(value.active === false) {
				$('.inactive1').attr('checked','checked');
			}
			
			if(value.team === "java") {
				$('.java').attr('selected', true);
			} else
			if(value.team === "Ruby") {
				$('.ruby').attr('selected', true);
			}
			$('.email3').attr('disabled','disabled');
			 
		},
 
		error : function(err) {
			console.log(err);
			alert(JSON.stringify(err));
		}
			 });
}
function deleteUser(idDelete) {
	
	//alert(idDelete);
	var test = confirm("Ban co muon xoa khong???");
	console.log(test);
	if(test === true) {
		
	
	 $.ajax({
			url : '/api/users/deleteUser/'+idDelete,
			type : 'DELETE',
			contentType : "application/json; charset=utf-8",
//   		data : userString,
			dataType : 'json',
			 complete : function(res) {

				console.log(res.status);
				 if (res.status === 200 || res.status ===201){
					$('.msg-error-delete').fadeIn(1000);
					//$('.msg-error-delete').addClass('btn-success');
					$('.msg-error-delete').text('!!!Delete User Success!!!');
					//$('.msg-error-delete').css('background-color', 'green');
					$('.msg-error-delete').css('color', 'red');
					location.reload();
					
					setTimeout(function() {
						$('.msg-error-delete').fadeOut(1000);
					}, 8000);
				 }
			 }
			});
	}
}

$(document).ready(function() {
$('#checkedAll').click(function() {
	alert("delete all");
	var test = confirm("Ban co muon xoa tat ca cac user khong???");
	console.log(test);
	if(test === true) {
		
	
	 $.ajax({
			url : '/api/users/deleteAllSoft',
			type : 'DELETE',
			contentType : "application/json; charset=utf-8",
//   		data : userString,
			dataType : 'json',
			 complete : function(res) {

				console.log(res.status);
				 if (res.status === 200 || res.status ===201){
					location.reload();
				 }
			 }
			});
	}
	
});
});
			
			
			
			
			
//			 complete : function(res) {
//				 console.log("tu viet van11111111111");
//				console.log(res.status);
//				 if (res.status === 200 || res.status ===201){
//					$('.msg-error-update').fadeIn(1000);
//					$('.msg-error-update').addClass('btn-success');
//					$('.msg-error-update').text('!!!Update User Success!!!');
//					$('.msg-error-update').css('background-color', 'green');
//					$('.msg-error-update').css('color', 'white');
//					$('.msg-error-update').css('height', '50px');
//					
//					setTimeout(function() {
//						$('.msg-error').fadeOut(1000);
//					}, 8000);
//				}else if (res.status===409 || res.status===400){
//					console.log("van dep trai");
//					console.log(res.responseText);
//					$('.msg-error-update').fadeIn(1000);
//					$('.msg-error-update').addClass('btn-danger');
//					$('.msg-error-update').text(res.responseText);
//					$('.msg-error-update').css('background-color', '#2979ff')
//					setTimeout(function() {
//						$('.msg-error-update').fadeOut(1000);
//					}, 8000);
//				} 
//			}
//			
//		}); 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


$(document).ready(function() {
//update thong tin
	
	
	 function showValueTeam(){
		  var test = document.getElementById("update-infor").value;
		  console.log(test);
		 return test;
		 }
$('#update-user').click(function() {
	//alert();
	console.log("update infor");
	
	console.log("ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
	var URL = "/api/users/addUser";
	var user = $('.form-user-update').serializeJSON({
		parseBooleans : true,
		parseBooleans : false,
	});
	console.log(user);
	
	var checkbox = document.getElementsByName("gender");
	for (var i = 0; i < checkbox.length; i++){
        if (checkbox[i].checked === true && checkbox[i].value == 'male'){
        	user["gender"] = true;
           break;
        } else if (checkbox[i].checked === true && checkbox[i].value == 'female'){
        	user["gender"] = false;
            break;
         }
    }
	
	
	
	var checkbox1 = document.getElementsByName("role");
	for (var i = 0; i < checkbox1.length; i++){
        if (checkbox1[i].checked === true && checkbox1[i].value == 'user3'){
        	user["roleName"] = 'USER';
           break;
        } else if (checkbox1[i].checked === true && checkbox1[i].value == 'admin3'){
        	user["roleName"] = 'ADMIN';
            break;
         }
    }
	
	
	var checkbox = document.getElementsByName("active");
	for (var i = 0; i < checkbox.length; i++){
        if (checkbox[i].checked === true && checkbox[i].value == 'active'){
        	user["active"] = true;
           break;
        } else if (checkbox[i].checked === true && checkbox[i].value == 'inactive'){
        	user["active"] = false;
            break;
         }
    }
	user["avatar"] = "/images/16.jpg";
	
	var team2 = showValueTeam();
	console.log(team2);
	/* user["team"] = "Java"; */
	if(team2 == '1') {
		user["team"] = 'Java';
	} else if(team2 == '2') {
		user["team"] = 'Ruby';
	} else if(team2 == '3') {
		user["team"] = 'IOS';
		}
	user["nonLocked"] = true;
	user["nonDel"] = true;
	var mail = $('.email3').val();
	console.log(mail+"ddddddddddddddddddddddddddddd");
	user["email"] = mail;
	
//	$('.email3').attr('disabled',false);
	
	
	//var nut_active = document.getElementById("nut-active-update");
//	if(nut_active.checked === true) {
//		user["active"] = true;
//	} else {
//		user["active"] = false;
//	}
//	user["non_del"] = true;
	var userString = JSON.stringify(user);
	console.log("///////////" + userString);
	
	 $.ajax({
			url : "/api/users/editUser",
			type : 'PUT',
			contentType : "application/json; charset=utf-8",
			data : userString,
			dataType : 'json',
//			success : function(value) {
//				console.log(value);
//			}
//			
//			
//			
//			
			 complete : function(res) {
				 console.log("tu viet van11111111111");
				console.log(res.status);
				 if (res.status === 200 || res.status ===201){
					$('.msg-error-update').fadeIn(1000);
					$('.msg-error-update').addClass('btn-success');
					$('.msg-error-update').text('!!!Update User Success!!!');
					$('.msg-error-update').css('background-color', 'green');
					$('.msg-error-update').css('color', 'white');
					$('.msg-error-update').css('height', '50px');
					
					setTimeout(function() {
						$('.msg-error').fadeOut(1000);
					}, 8000);
				}else if (res.status===409 || res.status===400){
					console.log("van dep trai");
					console.log(res.responseText);
					$('.msg-error-update').fadeIn(1000);
					$('.msg-error-update').addClass('btn-danger');
					$('.msg-error-update').text(res.responseText);
					$('.msg-error-update').css('background-color', '#2979ff')
					setTimeout(function() {
						$('.msg-error-update').fadeOut(1000);
					}, 8000);
				} 
			}
//			
//		}); 
});
});
});


