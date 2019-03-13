$(document).ready(function() {
	listDevice(); //function1
	
	 $("#modaldetails").on("hidden.bs.modal", function(){
		    $(".detail-table").html("");		    
		    $(".Step1").click();
		});
	
	
	//function1
	function listDevice(){
		 $(".Step1").click();
		$.ajax({
			url : '/listAllDevice',
			type : 'get',
			success : function(value) {
				console.log(value);
				 $.each(value, function (key, device) {
					 $(".device-table").append("<tr><td class =\"deviceId\" hidden=\"\" >"+device.id+"</td>" 
					 		+"<td><div class=\"custom-control custom-checkbox\">"
 +"<input type=\"checkbox\" name=\"device_id\" class=\"custom-control-input checkSingle\" required>"
 +"<label class=\"custom-control-label\" for=\"device_id\"></label></div></td>"
 +"<td><div class=\"device_icon icon  s-36 mr-3 mt-1 float-left\"><span class=\"circle\"></span></div>"
 +"<div><div class=\"pt-2\"><strong class=\"deviceName\">"+device.name+"</strong></div></div></td>"
 +"<td class=\"quantity\">"+device.quantity+"</td>"
 +"<td class=\"price (USD)\">"+device.price+"</td>"
 +"<td><a class=\"\" href=\"modaldetails\"data-toggle=\"modal\" data-target=\"#modaldetails\">"
 +"<span class=\"badge badge-primary viewDetail\">Details</span></a></td></tr>");	
					 convertToIconDevice(device.catalog);
	                    })
			},
			error : function(err) {
				console.log(err);
			
			}
		})
	}

	//refurn devicedetails by device
	$(".device-table").on('click','.viewDetail',function(e) {	
		var $row = $(this).closest("tr");
		var deviceId = $row.find(".deviceId").text();
		 $(".detail-table").html("");
		callApiGetDetail(deviceId);
	})
	
	$(".detail-table").on('click','.editDetail',function(e) {	
		
		var $row = $(this).closest("tr");
		var iddevice = $row.find(".idDevice").text();
		var detailId = $row.find(".detailId").text();
		var productId = $row.find(".productId").text();
		var status = $row.find(".status").text();
		var deviceName = $row.find(".deviceName").text();
		var cataloName = $row.find(".catalogName").text();
		convertToIconDetail(cataloName);
		console.log(cataloName);
		$('#deviceName').val(deviceName);
		$('#productId').val(productId);
		$('#catalogName').val(cataloName);
		$('#id_detaildevice').val(detailId);
		$('#id_device').val(iddevice);
		if(status ==="Working"){ 
$('#SwitchStatusWorking').prop("checked", true); $('#SwitchStatusError').prop("checked", false); $('#SwitchStatusNotUse').prop("checked", false);}
		else if(status ==="Error"){
$('#SwitchStatusWorking').prop("checked", false); $('#SwitchStatusError').prop("checked", true); $('#SwitchStatusNotUse').prop("checked", false);}
		else if(status ==="Not Use"){ 
$('#SwitchStatusWorking').prop("checked", false); $('#SwitchStatusError').prop("checked", false); $('#SwitchStatusNotUse').prop("checked", true);}

	})
	
// $('#sumitUpdate1').click(function() {
//	   event.preventDefault();
//////	   event.addEventListener('click', swapper, false);
//	 var DetailInfo = $('#edit-detail-form').serializeJSON({parseBooleans: true});
//	 if(DetailInfo.statusB === "on"){ DetailInfo["status"] = 0;}
//	 else if(DetailInfo.statusN === "on"){DetailInfo["status"] = 1;}
//	 else {DetailInfo["status"] = 2;}
////	 alert(DetailInfo.productId);
////	 alert($('#productId').val());
//		var datajson = JSON.stringify(DetailInfo);
//	
//		$.ajax({
//			url : '/editdevicedetails',
//			  type: 'PUT',
//				contentType : "application/json; charset=utf-8",
//				data : datajson,
//				dataType : 'json',
//			success : function(value) {
//				console.log(value);
//			},
//			error : function(err) {
//				console.log(err);	 
//			}
//		});
//	 
//	 
////	 var jsond["id"] = $('#id_Device').val();
//	 
//	
//
//			    });
	
			 $('#SwitchStatusWorking').change(function() {
				 $('#SwitchStatusError').prop("checked", false);
				 $('#SwitchStatusNotUse').prop("checked", false);     
			    });
	 $('#SwitchStatusError').change(function() {
		 $('#SwitchStatusWorking').prop("checked", false);
		 $('#SwitchStatusNotUse').prop("checked", false);     
	    });	 
	 $('#SwitchStatusNotUse').change(function() {
			 $('#SwitchStatusError').prop("checked", false);
			 $('#SwitchStatusWorking').prop("checked", false);     
		    });
	
	 $('.Step1').click(function() {
		 var iddevice = $("#id_device").val();
			$(".detail-table").html("");
		 callApiGetDetail(iddevice);    
	    });
	 
	function convertToIconDevice(catalog){
		 if(catalog === "Laptop"){$('.device_icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Keyboard"){$('.device_icon').addClass('icon-keyboard');}
		 else if(catalog === "Monitor"){$('.device_icon').addClass('icon-desktop_mac');}	
		 else if(catalog === "Desktop Mac"){$('.device_icon').addClass('icon-desktop_mac');}
		 else if(catalog === "UPS"){$('.device_icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Mouse"){$('.device_icon').addClass('icon-mouse');}
		 else {$('.device_icon').addClass('icon-laptop_mac');}
	}
	function convertToIconDetail(catalog){
		 $('.detail-icon').removeClass('icon-laptop_mac');
		 $('.detail-icon').removeClass('icon-keyboard');
		  $('.detail-icon').removeClass('icon-desktop_mac');
		   $('.detail-icon').removeClass('icon-mouse');
		 if(catalog === "Laptop"){
//			 $('#detail-icon').addClass('icon-laptop_mac');
			 $('.detail-icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Keyboard"){
			 $('.detail-icon').addClass('icon-keyboard');}
		 else if(catalog === "Monitor"){$('.detail-icon').addClass('icon-desktop_mac');}	
		 else if(catalog === "Desktop Mac"){$('.detail-icon').addClass('icon-desktop_mac');}
		 else if(catalog === "UPS"){$('.detail-icon').addClass('icon-laptop_mac');}
		 else if(catalog === "Mouse"){$('.detail-icon').addClass('icon-mouse');}
		 else {$('.detail-icon').addClass('icon-laptop_mac');}
	}

	function callApiGetDetail(deviceId){
	
		var json = {};
		json["id"] = deviceId;
		var jsondevice = JSON.stringify(json);
		$.ajax({
			url : '/Getdevicedetails',
			  type: 'POST',
				contentType : "application/json; charset=utf-8",
				data : jsondevice,
				dataType : 'json',
			success : function(value) {
//				alert(value);
				console.log(JSON.stringify(value));
				 var working = 0;
				 var notuse = 0;
				 var error = 0;
				 $.each(value, function (key, detail) {
					
//					 var datetime =new Date(detail.updatedate).Format("dd/MM/yyyy:hh:mm:ss");
						var datetime = moment(detail.updatedate).format('DD-MM-YYYY , HH:mm:ss');
						$(".detail-table").append("<tr class=\""+key+"\"></tr>");	
						$("."+key+"").append("<td class =\"detailId\" hidden=\"\" >"+detail.id+"</td>"
							 +"<td><div class=\"custom-control custom-checkbox\">"
				+"<input type=\"checkbox\" class=\"custom-control-input checkSingle\" required>"
				+"<label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td>"
			+"<td><div class=\"detail-icon icon  s-36 mr-3 mt-1 float-left\"><span class=\"circle\"></span></div>"
			+"<div><div><strong class=\"productId\">"+detail.productid+"</strong></div></div></td><td>"+datetime+"</td>");
					 
			 if(detail.status === 1){
				 working = working +1;
				 $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-success\">Working</span></td>");
			 } else if(detail.status === 2){
				error = error +1
				 $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-danger \">Error</span></td>");			
			 } else { 
				 notuse = notuse +1;
				 $("."+key+"").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-dark \">Not Use</span></td>");
			 }
			 $("."+key+"").append("<td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a>"
						+"<a class=\"\" href=\"#step-22\"><i class=\"editDetail icon-pencil\"></i></a></td><td class=\"deviceName\" hidden=\"\">"+detail.devicename+"</td><td class=\"catalogName\" hidden=\"\">"+detail.catalogname+"</td><td class=\"idDevice\" hidden=\"\">"+json.id+"</td></tr>");			 
			convertToIconDetail(detail.catalogname);
		
				 });
				 $("#workinglb").text(working);
				 $("#notuselb").text(notuse);
				 $("#errorlb").text(error);
			},
			error : function(err) {
				console.log(err);	 
			}
		})
	}
});