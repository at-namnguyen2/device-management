$(document).ready(function() {
	$(".title-top").text("Request");
	getQuantityDevice();
	listRequestToday();
	listDeviceToday();
	
	

	
	
	
	
	function listRequestToday(){
		$.ajax({
			url : '/listrequesttoday',         
            dataType: "json",
			type : 'get',
			success : function(value) {
				console.log(value);
				 $.each(value, function (key, request) {
					 var datetime = moment(request.updatedate).format('DD-MM-YYYY , HH:mm:ss');
					 var block = request.content.split("content:");
					 console.log(block[1]);
					 var dayago = $.timeago(request.updatedate);
					 console.log("hihi"+datetime)
					 console.log("hihi"+dayago);
					 	if(request.type == "Allocation"){
					$(".request-body").append("<div class=\"media p-3 b-b\">" +
	        "<div  class=\"mr-3 mt-1 w-45px\"><img class=\"avatar avatar-lg \" src=\""+request.avatar+"\" alt=\"user\"></div>" +
			"<div class=\"media-body\">" +
	        "<span class=\"badge r-3 badge-primary pt-1 pb-1 float-right trigger\" data-tooltip-id=\"3\" data-html=\"true\" title=\""+block[1]+"\">Allocation</span>" +
					"<h6 class=\"mt-0\">"+request.fullname+"</h6>" +
					""+dayago+"</div></div>");
					 	} else if(request.type == "Return"){
					 $(".request-body").append("<div class=\"media p-3 b-b\">" +
			"<div  class=\"mr-3 mt-1 w-45px\"><img class=\"avatar avatar-lg \" src=\""+request.avatar+"\" alt=\"user\"></div>" +
			"<div class=\"media-body\">" +
			"<span class=\"badge r-3 badge-danger pt-1 pb-1 float-right trigger\" data-tooltip-id=\"3\" data-html=\"true\" title=\""+block[1]+"\">Return</span>" +
			"<h6 class=\"mt-0\">"+request.fullname+"</h6>" +
			""+dayago+"</div></div>");
					 	} else {
					$(".request-body").append("<div class=\"media p-3 b-b\">" +
			"<div  class=\"mr-3 mt-1 w-45px\"><img class=\"avatar avatar-lg \" src=\""+request.avatar+"\" alt=\"user\"></div>" +
			"<div class=\"media-body\">" +
			"<span class=\"badge r-3 badge-danger pt-1 pb-1 float-right trigger\" data-tooltip-id=\"3\" data-html=\"true\" title=\""+block[1]+"\">Update Info</span>" +
			"<h6 class=\"mt-0\">"+request.fullname+"</h6>" +
			""+dayago+"</div></div>");
					 	}	   	
	                    })
			},
			error : function(err) {
				console.log(err);
			}
		})
	}
	
	function listDeviceToday(){
		$.ajax({
			url : '/listDevicesAllocationReturnToday',         
            dataType: "json",
			type : 'get',
			success : function(value) {
				console.log(value);
				 $.each(value, function (key, ed) {
					 var updatedate = null;
					 var type = null;
					 if(ed.dateReturn != null){
						 updatedate = ed.dateReturn;
						 type = "Return";
					 } else {
						 updatedate = ed.dateDeliverReceive;
						 type = "Allocation";
					 }
					 var dayago = $.timeago(updatedate);
					 console.log("hihi"+dayago);
					$(".device-body").append("<tr class=\"no-b\">" +
		"<td class=\"w-10\">" +
		"<div class=\"device_icon icon s-36  mt-1 float-left "+ed.iconCatalog+"\">" +
		"<span class=\"circle\"></span></div></td>" +
		"<td><h6 >"+ed.deviceName+"</h6> <small class=\"text-muted\">"+ed.productId+"</small></td>" +
		"<td><span class=\"badge badge-primary\">"+type+"</span></td>" +
		"<td><span> <i class=\"icon icon-data_usage\"></i>"+dayago+"</span><br></td>" +
		"<td><a class=\"btn-fab btn-fab-sm btn-primary text-white\" title=\""+ed.email+"\"> " +
		"<i class=\"icon-eye\"></i></a></td></tr>");	   	
	                    })
			},
			error : function(err) {
				console.log(err);
			
			}
		})
	}

	function getQuantityDevice(){
		$.ajax({
			url : '/getquantitydevice',         
            dataType: "json",
			type : 'get',
			success : function(value) {
				console.log(value);
				var length = value.length -1 ;
				$.each(value, function (key, data) {
					 $(".name"+key+"").text(data.catalogName);
					 $(".icon"+key+"").addClass(data.catalogIcon);
					 $(".total"+key+"").text(data.total);
					 $(".working"+key+"").text(data.woking);
					 $(".notuse"+key+"").text(data.notUse);
					 $(".error"+key+"").text(data.error);
				 })
				  $("#dv5").click();
				  $("#overview").text(value[length].catalogName);
			},
			error : function(err) {
				console.log(err);
			
			}
		})
	}
	$(".dvCatalog").click(function(event) {
		$(".lightsliderbar").find(".dvCatalog").removeClass("bg-primary");
			  $(this).addClass("bg-primary");
			  var t = $(this).find(".total").text();
			  var w = $(this).find(".working").text();
			  var n = $(this).find(".notuse").text();
			  var e = $(this).find(".error").text();

			  $(".totalDetail").text(t);
			  $(".workingDetail").text(w);
			  $(".notuseDetail").text(n);
			  $(".errorDetail").text(e);
			  $("#donut-chart").html("");
			  donutChart(t, w, n, e);
			    });
	
	
	function donutChart(t, w, n, e) {
		if(t != 0){
			  var wPercent = (w*100)/t;
			  var nPercent = (n*100)/t;
			  var ePercent = (e*100)/t;
			  wPercent = Math.round(parseFloat(wPercent) * 100)/100;
			  nPercent = Math.round(parseFloat(nPercent) * 100)/100; 
			  ePercent = Math.round(parseFloat(ePercent) * 100)/100;
			  window.donutChart = Morris.Donut({
			  element: 'donut-chart',
			  data: [
			    {label: "Woking", value: wPercent},
			    {label: "Error", value: ePercent},
			    {label: "Not Use", value: nPercent}
			  ],
			  resize: true,
			  redraw: true
			});
		}	 
		}
})