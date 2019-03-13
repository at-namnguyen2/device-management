
$(document).ready(function() {
	Date.prototype.Format = function customDate(fmt){
		  var o = {
                  "M+": this.getMonth() + 1,                 // MM
                  "d+": this.getDate(),                    // dd
                  "h+": this.getHours(),                   // hh
                  "m+": this.getMinutes(),                 // mm
                  "s+": this.getSeconds(),                 // ss
                  "q+": Math.floor((this.getMonth() + 3) / 3), // qq
                  "S": this.getMilliseconds()             // S
              };
              if (/(y+)/.test(fmt))
                  fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
              for (var k in o)
                  if (new RegExp("(" + k + ")").test(fmt))
                      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
              return fmt;
	}
	
	$('#change-pass').click(function() {

		$('#v-pills-timeline-tab').click();
	})
		$.ajax({
			url : '/userapi/myprofile',
			type : 'get',
			success : function(value) {
				console.log(value);
	            var birthday=new Date(value.dateOfBirth).Format("dd/MM/yyyy");
				sessionStorage.setItem("fullname", value.name);
				$('#id').val(value.id);
				$('.fullname').text(value.name);
				$('.fullname').val(value.name);
				$('.email').text(value.email);
				$('.email').val(value.email);
				$('.phone').text(value.phone);
				$('.phone').val(value.phone);
				$('.team').text(value.team);
				$('.team').val(value.team);
				$('.address').text(value.address);
				$('.address').val(value.address);
				$('.dateOfBirth').text(birthday);
				$('.dateOfBirth').val(value.dateOfBirth);
				if(value.avatar == null || value.avatar == ""){
					$('.data-avatar').attr('src','/images/0.png');
				} else {	
					$('.data-avatar').attr('src',value.avatar); 
				$('.data-avatar-edit').attr('src',value.avatar); 
				
				}
			
				// dz-started
				if (value.gender === true) {
					$(".genderM").prop("checked", true);
				} else {			
					$('.genderF').prop('checked',true);
				}
			},
			error : function(err) {
				console.log(err);
//				alert(JSON.stringify(err));
			}
		})
		
				$.ajax({
			url : '/userapi/sacontact',
			type : 'get',
			success : function(value) {
				console.log(value[0]);
				$('.saname1').text(value[0].name);
				$('.saphone1').text(value[0].phone);
				 $.each(value, function (key, contact) {
	                    var adminphone = contact.phone; // Change here
	                    console.log(adminphone);
	                    if(!key==0){    
	                    $(".SaOther").append("<ul class=\"list-group list-group-flush\"><li class=\"list-group-item\"><div class=\"image mr-3  float-left\"><img class=\"user_avatar\" src=\"/assets/img/dummy/u1.png\" alt=\"User Image\"></div><h6 class=\"p-t-10\">"+contact.name+"</h6><span> "+contact.phone+"</span></li></ul>");
	                    } 
	                    })
			},
			error : function(err) {
				console.log(err);
//				alert(JSON.stringify(err));
			}
		})
		
						$.ajax({
			url : '/empdevapi/getuserdevdere',
			type : 'get',
			success : function(value) {
				console.log(value);
				console.log(value);
				var data = value.data;
//				for (var i = 0; i < data.length; i++) {
//					
//				}
				 $.each(data, function (key, device) {
					 var datetime =new Date(device.updatedate).Format("dd/MM/yyyy:hh:mm:ss");
					 console.log("test"+device.updatedate);
					 $(".tabledevice").append("<tr><td hidden=\"\" class=\"catalogname\">"+device.catalogname+"</td><td class=\"devicename\"><div class=\"avatar avatar-md mr-3 mt-1 float-left\"><img height=\"48\" width=\"48\" class=\"text-green\" th:src=\"@{/assets/fonts/icons/svg/cenz.svg}\"/></div><div><div><strong>"+device.devicename+"</strong></div></div></td><td  class=\"productid\">"+device.productid+"</td><td class=\"datetime\">"+datetime+"</td><td class=\"price\">"+device.price+"</td><td><a href=\"modalRequest\" data-toggle=\"modal\" data-target=\"#modalRequest\" class=\"returndv\"><span class=\"badge badge-danger \">Return</span></a></td></tr>");	                
					 console.log("test2"+device);
	                
	                    })
			},
			error : function(err) {
				console.log(err);
//				alert(JSON.stringify(err));
			}
		})
		
		// edit profile
	$('.saveprofile').click(function() {
		var profile = $('#form-profile').serializeArray();	
//		alert("hihi");
		formData = new FormData(1);
		files = [];
		json = {};
		var filedata = false;
		  $.each(profile, function(i, field){
			  
			 if(field.name === "file"){
				 if(field.value !== ""){
					 filedata = true;
					
						var ImageURL = field.value;
						// Split the base64 string in data and contentType
						var block = ImageURL.split(";");
						// Get the content type of the image
						var contentType = block[0].split(":")[1];// In this
																	// case
																	// "image/gif"
						// get the real base64 content of the file
						var realData = block[1].split(",")[1];// In this case
																// "R0lGODlhPQBEAPeoAJosM...."

						// Convert it to a blob to upload
						var blob = b64toBlob(realData, contentType);

						// Create a FormData and append the file with "image" as
						// parameter name
						var formDataToUpload = new FormData();
						formData.append("file", blob);
						console.log("hihi"+blob.name);
				 }
			 } else if(field.name === "genderM"){
				 json["gender"] = true;
			 }  else if(field.name === "genderF"){
				 json["gender"] = false;
			 }  else {
				 json[field.name] = field.value;
			 }
		    });
	

			console.log("hihi"+formData);
			var editdata = JSON.stringify(json);
			formData.append("userdto", editdata);
			console.log(editdata);
			if(filedata === true){
			$.ajax({
				url : '/userapi/editmyprofile',
				  type: 'POST',
					data: formData,
				  processData: false,
				  contentType: false,
				success : function(value) {
				},
				error : function(err) {
					console.log(err);
//					alert(JSON.stringify(err));
				}
			})
			} else {
				$.ajax({
					url : '/userapi/editmyprofile2',
					  type: 'POST',
						data: formData,
					  processData: false,
					  contentType: false,
					success : function(value) {
					},
					error : function(err) {
						console.log(err);
//						alert(JSON.stringify(err));
					}
				})
			}
			$('.toast-save').click();
			
	})
	
					$.ajax({
			url : '/empdevapi/countdevice',
			type : 'get',
			success : function(value) {
				console.log(value.working);
				  var dateupdate=new Date(value.lastUpdate).Format("dd/MM/yyyy:hh:mm:ss");
				$('.receiveddv').text(value.quantity);
				$('.workingdv').text(value.working);
				$('.lastupdate').text(dateupdate);
			},
			error : function(err) {
				console.log(err);
//				alert(JSON.stringify(err));
			}
		})
	
		var catalogname = "";
		var devicename = "";
		var productid = "";
		//  New Device Allocation Request
	$('#newrequest').click(function() {
		 $("#RequestLabel").text("New Device Allocation Request");
	})
	
	$(".table-device").on('click','.returndv',function(e) { 
		 $("#RequestLabel").text("Device Return Request");
		    var $row = $(this).closest("tr");    // Find the row
		    catalogname = $row.find(".catalogname").text(); // Find the text
		    devicename = $row.find(".devicename").text(); 
		    productid = $row.find(".productid").text(); 
		    console.log("hihi"+catalogname);
		    $(".catalog-body").attr('hidden','');
		    
})

	$('.sendRequest').click(function() {
		var request = $('#form-request').serializeArray();	
		json = {};
		var content = catalogname+" - "+devicename+" - "+productid;

		 
		  json["id"] = $('#id').val();
		  json["fullname"] = $('.fullname').val();
		  json["email"] = $('.email').val();
		  json["team"] = $('.team').val();	  
	
		  var srt1 =  $("#RequestLabel").html();
		  var type = "Allocation";
		  if(srt1.indexOf(type) != -1){
			  json["type"] = type;
			  $.each(request, function(i, field){			  	
				 content = content.concat(field.name +": "+field.value+ " - ");	
			    });
		  } else {
			  json["type"] = "Return";
			  content = content.concat(" - content: "+$("#content").val());
		  }
		  
		  json["content"] = content;
			var requestdata = JSON.stringify(json);
			console.log("content: "+requestdata);
			 sendrequest(requestdata);
			 $('#content').val("");

			 $('#modalRequest').modal('toggle');
			 
// var jsonStringProFile = JSON.stringify(proFileForm);
// console.log(jsonStringProFile);
//			$.ajax({
//				url : '/userapi/editmyprofile',
//				  type: 'POST',
//					contentType : "application/json; charset=utf-8",
//					data : requestdata,
//					dataType : 'json',
//				success : function(value) {
//				},
//				error : function(err) {
//					console.log(err);
//					alert(JSON.stringify(err));
//				}
//			})
	})
	
	myrequest();
	function myrequest(){
		$.ajax({
			url : '/myrequest',
			type : 'get',
			success : function(value) {
				var count = 0;
				console.log(value);
				 $.each(value, function (key, request) {
					 var datetime =new Date(request.updateDate).Format("dd/MM/yyyy:hh:mm:ss");
					 console.log(datetime);
					 var block = request.content.split("content:");
					 console.log(block[1]);
					 if(request.status === "Pending"){
						 if(request.type === "Update Info"){
							 $(".tablerequest").append("<tr><td class=\"typerequest\">"+request.type+"</td><td><a href=\"#\">Update Info</a></td><td>"+datetime+"</td><td><span class=\"statusrequest badge badge-danger\">"+request.status+"</span></td></tr>");	
//							 disableedit();
							 $('.warning-edit').removeAttr('hidden',"");
					 } else {
						 $(".tablerequest").append("<tr><td class=\"typerequest\">"+request.type+"</td><td><a href=\"#\">"+block[1]+"</a></td><td>"+datetime+"</td><td><span class=\"statusrequest badge badge-danger\">"+request.status+"</span></td></tr>");	

					 }
					 }
					 else if(request.status === "Approved"){
						 count = count +1;
						 $('.count-content').append("<a href=\"#\"><i class=\"icon icon-data_usage text-success\"></i>" + block[1]+"</a>");
						 if(request.type === "Update Info"){
							 $(".tablerequest").append("<tr><td class=\"typerequest\">"+request.type+"</td><td><a href=\"#\">updated</a></td><td>"+datetime+"</td><td><span class=\"statusrequest badge badge-success\">"+request.status+"</span></td></tr>");	
					 } else {
						 $(".tablerequest").append("<tr><td class=\"typerequest\">"+request.type+"</td><td><a href=\"#\">"+block[1]+"</a></td><td>"+datetime+"</td><td><span class=\"statusrequest badge badge-success\">"+request.status+"</span></td></tr>");	
					 }
					 }
					 else if(request.status === "Cancel"){
						 count = count +1;
						 $('.count-content').append("<a href=\"#\"><i class=\"icon icon-data_usage text-success\"></i>" + block[1]+"</a>");
						 $(".tablerequest").append("<tr><td class=\"typerequest\">"+request.type+"</td><td><a href=\"#\">"+block[1]+"</a></td><td>"+datetime+"</td><td><span class=\"statusrequest badge badge-dark\">"+request.status+"</span></td></tr>");	
					 }
					 else if (request.type === "Reply"){
						 count = count +1;
						 $('.count-content').append("<a href=\"#\"><i class=\"icon icon-data_usage text-success\"></i>" + block[1]+"</a>");
						 $(".tablerequest").append("<tr><td class=\"typerequest\">"+request.type+"</td><td><a href=\"#\">"+block[1]+"</a></td><td>"+datetime+"</td><td><span class=\"statusrequest badge badge-dark\">"+request.status+"</span></td></tr>");	
				 }
					 if(request.type === "Return"){
						 $('.tablerequest').find('td[class=typerequest]').addClass('text-secondary');
//						
					 }
					 else if(request.type === "Allocation"){
						 $('.tablerequest').find('td[class=typerequest]').addClass('text-primary');	
					 }
					 else if(request.type === "Update Info"){
							 $('.tablerequest').find('td[class=typerequest]').addClass('text-warning');	
					 }
			
					  else if (request.type === "Reply"){
							 $('.tablerequest').find('td[class=typerequest]').addClass('text-warning');				
					 }
					 $('.count-notify').text(count);
					 $('.count-header').text("You have "+count+" notifications");

	                    })
			},
			error : function(err) {
				console.log(err);
				
			}
		})
	}
	
	// function send data request 
	function sendrequest(requestdata){
			$.ajax({
			url : '/createrequest',
			  type: 'POST',
				contentType : "application/json; charset=utf-8",
				data : requestdata,
				dataType : 'json',
			success : function(value) {
			},
			error : function(err) {
				console.log(err);
//				alert(JSON.stringify(err));
			}
		})
		
		}
	
	$('#v-pills-request-tab').click(function(){
		 $('.tablerequest').html("");
		 myrequest();
	})
	
	function disableedit(){
		$(".fullname").prop("disabled", true);
		$(".team").prop("disabled", true);
		$(".dateOfBirth").prop("disabled", true);
		$(".genderM").prop("disabled", true);
		$(".genderF").prop("disabled", true);
		$("#fileUpload").attr("hidden", "");
		$(".email").prop("disabled", true);
		$(".phone").prop("disabled", true);
		$(".address").prop("disabled", true);
		$(".saveprofile").attr("hidden", "");
		
	}
	
		// function convert data uri to image
		function b64toBlob(b64Data, contentType, sliceSize) {
	        contentType = contentType || '';
	        sliceSize = sliceSize || 512;

	        var byteCharacters = atob(b64Data);
	        var byteArrays = [];

	        for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
	            var slice = byteCharacters.slice(offset, offset + sliceSize);

	            var byteNumbers = new Array(slice.length);
	            for (var i = 0; i < slice.length; i++) {
	                byteNumbers[i] = slice.charCodeAt(i);
	            }

	            var byteArray = new Uint8Array(byteNumbers);

	            byteArrays.push(byteArray);
	        }

	      var blob = new Blob(byteArrays, {type: contentType});
	      return blob;
	}
		
	});