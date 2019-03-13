$(document).ready(function() {
	listaAllocation("pagination-allo",1,true);
	function listaAllocation(item,pageIndex,query) {
		var json = {};
		var pagingItem = {};
		var querySearch = {};
		if(query){
			querySearch = $('#filters-allocation').serializeJSON();
			console.log("test:"+JSON.stringify(querySearch))
		} else {
			querySearch = $('#filters-return').serializeJSON();
			console.log("test return:"+JSON.stringify(querySearch))
		}
		querySearch['allocationStatus'] = query;
		pagingItem['pageSize'] = 5;
		if(pageIndex) pagingItem['pageIndex'] = pageIndex;
		json['Query'] = querySearch;
		json['PagingItem'] = pagingItem;
		console.log("test22:"+JSON.stringify(json));
		$.ajax({
			url : '/list-device-allocation',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(json),
			dataType : 'json',
			type : 'post',
			success : function(value) {
				console.log(value);		
				if(item == "pagination-allo"){
					var pagin = "pagination-allo";
					var page = value.totalPage;		
					$("#tbAllo").html("");
					pagination(item,page,query,listaAllocation);
					$.each(value.data,function(key, all) {
						TableAllocation(key,all);
					})
				}
				if(item == "pagination-return"){
					var pagin = "pagination-allo";
					var page = value.totalPage;	
					console.log("page"+page);
					$("#table-history").html("");
					pagination(item,page,query,listaAllocation);
					$.each(value.data,function(key, all) {
						TableHistory(key,all);
					})
				}
			},
			error : function(err) {
				console.log(err);
			}
		})
	}
	
		function TableAllocation(key,value){
			$("#tbAllo").append("<tr><td><div class=\"custom-control custom-checkbox\">"
					+ "<input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_1\" required>"
					+ "<label class=\"custom-control-label\" for=\"user_id_1\"></label>\</div></td>"
					+ "<td><a href=\"panel-page-profile.html\" class=\"avatar avatar-lg\">"
					+ "<img class=\"avatar1\" src=\""+ value.avatar+ "\" alt=\"\"></a></td>"
					+ "<td><h6>"+ value.employeeName + "</h6>" 
					+ "<small class=\"text-muted\">"+ value.email + "</small></td>"
					+ "<td>"+ value.team + "</td>"
					+ "<td>"+ value.deviceName + "</td>" 
					+ "<td>"+ value.productId + "</td>" 
					+"<td>" + value.dateDeliverReceive +"</td>"
					+ "<td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a>"
					+ "<a href=\"#step-22\"><i class=\"font-weight-bold text-danger\">return</i></a></td></tr>");
		}
	
	function TableHistory(key,value){
		$("#table-history").append("<tr><td><div class=\"custom-control custom-checkbox\">"
				+ "<input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"user_id_2\" required>"
				+ "<label class=\"custom-control-label\" for=\"user_id_2\"></label>\</div></td>"
				+ "<td><a href=\"panel-page-profile.html\" class=\"avatar avatar-lg\">"
				+ "<img class=\"avatar1\" src=\""+ value.avatar+ "\" alt=\"\"></a></td>"
				+ "<td><h6>"+ value.employeeName+ "</h6><small class=\"text-muted\">"+ value.email+ "</small></td>"
				+ "<td>"+ value.team + "</td><td>"+ value.deviceName + "</td>" 
				+ "<td>"+ value.productId + "</td>" 
				+ "<td>"+ value.dateReturn + "</td>"
				+ "</td><td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a>" 
				+ "<a href=\"#modaladddevicesdr\"><i class=\"icon-pencil mr-3\"></i></a> " 
				+"<a href=\"#step-22\"><i class=\"icon-delete\"></i></a></td></tr>");
	}
	$('#devices-history').click(function() {
		var item = "pagination-return";
		var allocationStatus = false;
		var pageIndex = 1;
		listaAllocation(item,pageIndex,allocationStatus);
	});
	
	$('#v-pills-all-tab').click(function() {
		var item = "pagination-allo";
		var allocationStatus = true;
		var pageIndex = 1;
		listaAllocation(item,pageIndex,allocationStatus);
	});
	
	 $('#filter-employeeName').typeahead({
		  source: function (query, result) {
			  var item = "pagination-allo";
				$('#pagination-allo').empty();
				listaAllocation (item,1,true);
         }
     });
	  $('#filter-team').typeahead({
         source: function (query, result) {
			  var item = "pagination-allo";
				$('#pagination-allo').empty();
				listaAllocation (item,1,true);
         }
     });
	  $('#filter-deviceName').typeahead({
         source: function (query, result) {
				$('#pagination-allo').empty();
			  var item = "pagination-allo";
			  listaAllocation (item,1,true);
         }
     });
	  $('#filter-productId').typeahead({
         source: function (query, result) {
				$('#pagination-allo').empty();
			  var item = "pagination-allo";
			  listaAllocation (item,1,true);
         }
     });
	  $('#filter-dateAllocation').typeahead({
	         source: function (query, result) {
					$('#pagination-allo').empty();
				  var item = "pagination-allo";
				  listaAllocation (item,1,true);
	         }
	     });
	  $('#filter-employeeName-his').typeahead({
         source: function (query, result) {
				$('#pagination-return').empty();
			  var item = "pagination-return";
			  listaAllocation (item,1,false);
         }
     });
	  $('#filter-team-his').typeahead({
         source: function (query, result) {
			  var item = "pagination-return";
				$('#pagination-return').empty();
				listaAllocation (item,1,false);
         }
     });
	  $('#filter-deviceName-his').typeahead({
         source: function (query, result) {
			  var item = "pagination-return";
				$('#pagination-return').empty();
				listaAllocation (item,1,false);
         }
     });
	  $('#filter-productId-his').typeahead({
         source: function (query, result) {
			  var item = "pagination-return";
				$('#pagination-return').empty();
				listaAllocation (item,1,false);
         }
     });
	  $('#filter-dateAllocation-his').typeahead({
	         source: function (query, result) {
				  var item = "pagination-return";
					$('#pagination-return').empty();
					listaAllocation (item,1,false);
	         }
	     });
	// refurn devicedetails by device
	$(".device-table").on('click', '.viewDetail', function(e) {
		var $row = $(this).closest("tr");
		var deviceId = $row.find(".deviceId").text();
		$(".detail-table").html("");
		callApiGetDetail(deviceId);
	})

	$(".detail-table").on('click', '.editDetail', function(e) {
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
		if (status === "Working") {
			$('#SwitchStatusWorking').prop("checked", true);
			$('#SwitchStatusError').prop("checked", false);
			$('#SwitchStatusNotUse').prop("checked", false);
		} else if (status === "Error") {
			$('#SwitchStatusWorking').prop("checked", false);
			$('#SwitchStatusError').prop("checked", true);
			$('#SwitchStatusNotUse').prop("checked", false);
		} else if (status === "Not Use") {
			$('#SwitchStatusWorking').prop("checked", false);
			$('#SwitchStatusError').prop("checked", false);
			$('#SwitchStatusNotUse').prop("checked", true);
		}
	})
	
	

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

	function convertToIconDevice(catalog) {
		if (catalog === "Laptop") {
			$('.device_icon').addClass('icon-laptop_mac');
		} else if (catalog === "Keyboard") {
			$('.device_icon').addClass('icon-keyboard');
		} else if (catalog === "Monitor") {
			$('.device_icon').addClass('icon-desktop_mac');
		} else if (catalog === "Desktop Mac") {
			$('.device_icon').addClass('icon-desktop_mac');
		} else if (catalog === "UPS") {
			$('.device_icon').addClass('icon-laptop_mac');
		} else if (catalog === "Mouse") {
			$('.device_icon').addClass('icon-mouse');
		} else {
			$('.device_icon').addClass('icon-laptop_mac');
		}
	}
	
	function convertToIconDetail(catalog) {
		$('.detail-icon').removeClass('icon-laptop_mac');
		$('.detail-icon').removeClass('icon-keyboard');
		$('.detail-icon').removeClass('icon-desktop_mac');
		$('.detail-icon').removeClass('icon-mouse');
		if (catalog === "Laptop") {
			// $('#detail-icon').addClass('icon-laptop_mac');
			$('.detail-icon').addClass('icon-laptop_mac');
		} else if (catalog === "Keyboard") {
			$('.detail-icon').addClass('icon-keyboard');
		} else if (catalog === "Monitor") {
			$('.detail-icon').addClass('icon-desktop_mac');
		} else if (catalog === "Desktop Mac") {
			$('.detail-icon').addClass('icon-desktop_mac');
		} else if (catalog === "UPS") {
			$('.detail-icon').addClass('icon-laptop_mac');
		} else if (catalog === "Mouse") {
			$('.detail-icon').addClass('icon-mouse');
		} else {
			$('.detail-icon').addClass('icon-laptop_mac');
		}
	}

	function callApiGetDetail(deviceId) {
		var json = {};
		json["id"] = deviceId;
		var jsondevice = JSON.stringify(json);
		$.ajax({
			url : '/Getdevicedetails',
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : jsondevice,
			dataType : 'json',
			success : function(value) {
				console.log(JSON.stringify(value));
				var working = 0;
				var notuse = 0;
				var error = 0;
				$.each(value,function(key, detail) {
					var datetime = moment(detail.updatedate).format('DD-MM-YYYY , HH:mm:ss');
					$(".detail-table").append("<tr class=\""+ key+ "\"></tr>");
					$("." + key + "").append("<td class =\"detailId\" hidden=\"\" >"+ detail.id+ "</td>"
					+ "<td><div class=\"custom-control custom-checkbox\">"
					+ "<input type=\"checkbox\" class=\"custom-control-input checkSingle\" required>"
					+ "<label class=\"custom-control-label\" for=\"user_id_1\"></label></div></td>"
					+ "<td><div class=\"detail-icon icon  s-36 mr-3 mt-1 float-left\"><span class=\"circle\"></span></div>"
					+ "<div><div><strong class=\"productId\">"+ detail.productid + "</strong></div></div></td>" 
					+ "<td>"+ datetime + "</td>");
					if (detail.status === 1) {
						working = working + 1;
						$("."+ key + "").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-success\">Working</span></td>");
					} else if (detail.status === 2) {
						error = error + 1
						$("."+ key+ "").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-danger \">Error</span></td>");
					} else {
						notuse = notuse + 1;
						$("."+ key + "").append("<td class=\"status\"><span class=\"badge-status r-3 badge badge-dark \">Not Use</span></td>");
					}
					$("." + key + "").append("<td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a>"
					+ "<a class=\"\" href=\"#step-22\"><i class=\"editDetail icon-pencil\"></i></a></td>" 
					+ "<td class=\"deviceName\" hidden=\"\">"+ detail.devicename +"</td>" 
					+ "<td class=\"catalogName\" hidden=\"\">"+ detail.catalogname + "</td>" 
					+ "<td class=\"idDevice\" hidden=\"\">"+ json.id + "</td></tr>");
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

	$('#auto-search-employee').typeahead({
		source : function(query, result) {
		console.log(query);
		$('.alertEmployee').attr('hidden',"");
		$.ajax({
			url : "/filteremployee",
			data : 'key=' + query,
			dataType : "json",
			type : "POST",
			success : function(data) {
				$('.tablefilter-emp').html("");
				$('#tablefilter-emp').removeAttr('hidden',"");
				console.log("hihi"+ data);
				$.each(data,function(key,emp) {
					$('.tablefilter-emp').append("<tr>"
						+ "<td class=\"idEmp\" hidden=\"\">"+ emp.id+ "</td>"
						+ "<td><div class=\"custom-control custom-checkbox\">"
						+ "<input type=\"checkbox\" class=\"custom-control-input checkSingle-emp\" id=\"tableDefaultCheck"+ key+ "\">"
						+ "<label class=\"custom-control-label\" for=\"tableDefaultCheck"+ key+ "\"></label></div></td>"
						+"<td><div class=\"avatar avatar-md mr-3 mt-1 float-left\">"
						+ "<img class=\"avatar1\" src=\""+ emp.avatar+ "\" alt=\"\"></div>"
						+"<div><div><strong>"+ emp.name + "</strong></div>"
						+ "<small>"+ emp.email +"</small></div></td>"
						+"<td>"+ emp.team+ "</td>"
						+ "<td>"+ emp.phone+ "</td>"
						+ "</tr>");
				})
			},
			error : function(err) {
				$('.tablefilter').html("");
				$('#tablefilter').attr('hidden',"");
				$('.alertdevice').removeAttr('hidden',"");
			}
		});
	}
	});				
					
	$('#auto-search-device').typeahead({
		source : function(query, result) {
			console.log(query);
			$('.alertEmployee').attr('hidden',"");
			$.ajax({
				url : "/filterdetailsnotused",
				data : 'key=' + query,
				dataType : "json",
				type : "POST",
				success : function(data) {
					$('.tablefilter-device').html("");
					$('#tablefilter-device').removeAttr('hidden',"");
					console.log("hihi"+ data);
					$.each(data,function(key,device) {
						var datetime = moment(device.updatedate).format('DD-MM-YYYY , HH:mm:ss');
						console.log("hihi"+ device.iconCatalog);
						$('.tablefilter-device').append("<tr>"
						+ "<td class=\"idDevice\" hidden=\"\">"+ device.id + "</td>"
						+ "<td><div class=\"custom-control custom-checkbox\">"
						+ "<input type=\"checkbox\" class=\"custom-control-input checkSingle-device\" id=\"checkbox-device"+ key + "\">"
						+ "<label class=\"custom-control-label\" for=\"checkbox-device"+ key + "\"></label></div></td>"
						+ "<td><div class=\"device_icon icon s-36 mr-3 mt-1 float-left "+ device.iconCatalog + " \"><span class=\"circle\"></span></div>"
						+ "<div><strong>"+ device.devicename + "</strong></div>"
						+ "<td>" + device.productid + "</td>"
						+ "<td>" + datetime + "</td>"
						+ "</tr>");
					})	
				},
				error : function(err) {
				console.log(err.responseText);
				$('.tablefilter').html("");
				$('#tablefilter').attr('hidden',"");
				$('.alertdevice').removeAttr('hidden',"");
				}
			});
		}
	});

	
	
	$('#auto-search').typeahead({
		source : function(query, result) {
		console.log(query);
		var p = 5;
		$('.alert-allocation-return').attr('hidden',"");
		tableSearch(query, 0);
	}
	});		
	
	function tableSearch(query, page){
		$.ajax({
			url : "/filterdevdere",
			data : {
				page : page,
				filter : query,
				size : 5
			},
			type : "GET",
			success : function(data) {
				$('.tablefilter-Allocation-Return').html("");
				$('#tablefilter-Allocation-Return').removeAttr('hidden',"");
				console.log("hihi"+ JSON.stringify(data));
				$.each(data.content,function(key,ar) {
					var updatedate = null;
					var type = null;
					if(ar.dateReturn === null){
						updatedate = moment(ar.dateDeliverReceive).format('DD-MM-YYYY , HH:mm:ss');
						type = "Allocation";
					} else {
						updatedate = moment(ar.dateReturn).format('DD-MM-YYYY , HH:mm:ss');
						type = "Return";
					}
						$(".tablefilter-Allocation-Return").append("<tr><td><div class=\"custom-control custom-checkbox\">"
						+ "<input type=\"checkbox\" class=\"custom-control-input checkSingle\" id=\"checkbox-search"+ key + "\" required>"
						+ "<label class=\"custom-control-label\" for=\"checkbox-search"+ key + "\"></label>\</div></td>"
						+ "<td><a href=\"panel-page-profile.html\" class=\"avatar avatar-lg\">"
						+ "<img class=\"avatar1\" src=\""+ ar.avatar+ "\" alt=\"\"></a></td>"
						+ "<td><h6>"+ ar.employeeName + "</h6>" 
						+ "<small class=\"text-muted\">"+ ar.email + "</small></td>"
						+ "<td>"+ ar.team + "</td>"
						+ "<td>"+ ar.deviceName + "</td>" 
						+ "<td>"+ ar.productId + "</td>" 
						+"<td>" + updatedate +"</td>"
						+"<td class=\"idrecord\" hidden = \"\">" +ar.id +"</td>"
						+ "<td><a href=\"panel-page-profile.html\"><i class=\"icon-eye mr-3\"></i></a>"
						+ "<a href=\"#step-22\"><i class=\"text-type font-weight-bold  \">"+ type +"</i></a></td></tr>");					
						$('i:contains("Allocation")').addClass('text-primary');
						$('i:contains("Allocation")').closest("tr").find(".checkSingle").prop('disabled', true);
						$('i:contains("Return")').addClass('text-danger');

				})
					
					var totalPages = data.totalPages;
					$('#pagination-allo-return').twbsPagination({
						totalPages : totalPages,
						visiblePages : 3,
						next : 'Next',
						prev : 'Prev',
						onPageClick : function(event, page) {
					
						tableSearch(query, page-1);
						}
					});
			},
			error : function(err) {
				alert();
				$('.tablefilter-Allocation-Return').html("");
				$('#tablefilter-Allocation-Return').attr('hidden',"");
				$('.alert-allocation-return').removeAttr('hidden',"");
			}
		});
	}
	
	
	
	tablefilterEmp();
	function tablefilterEmp() {
		$(".tablefilter-emp").on('click','.checkSingle-emp',function(e) {
			if ($(this).is(":checked")) {
				$('.checkSingle-emp').prop("checked",false);
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});
	}
	
	$(".tablefilter-device").on('click','.checkSingle-device',function(e) {
		if ($(this).is(":checked")) {
			$('.checkSingle-device').prop("checked",false);
			$(this).prop("checked", true);
		} else {
			$(this).prop("checked", false);
		}
	});

	$(".tablefilter-request").on('click','.checkSingle-request',function(e) {
		if ($(this).is(":checked")) {
			$('.checkSingle-request').prop("checked",false);
			$(this).prop("checked", true);
		} else {
			$(this).prop("checked", false);
		}
	});

	var btnFinish = $('<button></button>').text('Finish')
											.addClass('btn btn-info btn-finish')
											.attr('disabled', true)
											.on('click',function() {
											if (!$(this).hasClass('disabled')) {
												var elmForm = $("#myForm");
												if (elmForm) {
													elmForm.validator('validate');
													var elmErr = elmForm.find('.has-error');
												if (elmErr && elmErr.length > 0) {
													alert('Oops we still have error in the form');
													return false;
												} else {
													alert('Great! we are ready to submit form');
													var idEmp = getidEmployee();
													var idDevice = getIdDevice();
													var dateDeliverReceive = moment(dateDeliverReceive).format('YYYY-MM-DDThh:mm:ss.000+0000');
													callApiAddAlocation(idEmp,idDevice,dateDeliverReceive);
													return false;
												}
												}
											}
										});
	var btnCancel = $('<button></button>').text('Cancel')
											.addClass('btn btn-danger').on('click', function() {
												$('#smartwizard').smartWizard("reset");
												$('#myForm').find("input, textarea").val("");
										});

					// Smart Wizard
	$('#smartwizard').smartWizard({
						selected : 0,
						theme : 'dots',
						transitionEffect : 'fade',
						toolbarSettings : {
							toolbarPosition : 'bottom',
							toolbarExtraButtons : [ btnFinish, btnCancel ]
						},
						anchorSettings : {
							markDoneStep : true, // add done css
							markAllPreviousStepsAsDone : true, // When a step  selected by url hash, all
																// previous steps are marked done
							removeDoneStepOnNavigateBack : true, // While navigate back done step
																	// after active step will be cleared
							enableAnchorOnDoneStep : true
						// Enable/Disable the done steps navigation
						}
					});

	$("#smartwizard").on(
						"leaveStep",
						function(e, anchorObject, stepNumber, stepDirection) {
						var elmForm = $("#form-step-"+ stepNumber);
						// stepDirection === 'forward' :- this condition allows to do the form validation
						// only on forward navigation, that makes easy navigation on backwards
						// still do the validation when going next

						if (stepDirection === 'forward' && elmForm) {
							elmForm.validator('validate');
							var elmErr = elmForm.children('.has-error');
							if (elmErr && elmErr.length > 0) {
								// Form validation failed
								return false;
							}
							if (stepNumber === 0) {
								var idEmp = getidEmployee();
								if (typeof idEmp != 'undefined') {
									return true;
								}
							} else if (stepNumber === 1) {
								var idDevice = getIdDevice();
								if (typeof idDevice != 'undefined') {
									return true;
								}
							} else {
								return true;
							}
							$('.toastDevice').click();
							return false;
						}
						return true;
				});

	$("#smartwizard").on("showStep",function(e, anchorObject, stepNumber, stepDirection) {
	// Enable finish button only on last step
		if (stepNumber == 2) {
			var idEmp = getidEmployee();
			callApiGetRequest(idEmp);
		} else if (stepNumber == 3) {
			$('.btn-finish').removeAttr('disabled');
		} else {
		}
	});

	function getidEmployee() {
		var idEmployee;
		$('#tablefilter-emp').find('input[type="checkbox"]:checked').each(function() {
			var $row = $(this).closest("tr");
			idEmployee = $row.find(".idEmp").text();
		});
		return idEmployee;
	}
	
	function getIdDevice() {
		var idDevice;
		$('#tablefilter-device').find('input[type="checkbox"]:checked').each(function() {
			var $row = $(this).closest("tr");
			idDevice = $row.find(".idDevice").text();
		});
		return idDevice;
	}

	function callApiGetRequest(id) {
		json = {};
		json['id'] = id;
		$.ajax({
			url : "/filterrequest",
			data : JSON.stringify(json),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			type : "POST",
			success : function(data) {
				$('.tablefilter-request').html("");
				$('#tablefilter-request').removeAttr('hidden', "");
				console.log(JSON.stringify(data));
				$.each(data,function(key, request) {
				var datetime = moment(request.updateDate).format('DD-MM-YYYY , HH:mm:ss');
				$('.tablefilter-request').append("<tr>"
				+ "<td class=\"idDevice\" hidden=\"\">"+ request.id + "</td>"
				+ "<td><div class=\"custom-control custom-checkbox\">"
				+ "<input type=\"checkbox\" class=\"custom-control-input checkSingle-request\" id=\"checkbox-request"+ key + "\">"
				+ "<label class=\"custom-control-label\" for=\"checkbox-request"+ key + "\"></label></div></td>"
				+ "<td>" + datetime + "</td>"
				+ "<td>" + request.content + "</td>"
				+ "</tr>");
				});
			return false;
			},
			error : function(err) {
			}
		});
	}

	function callApiAddAlocation(idEmp, idDevice, dateDeliverReceive) {
		var json = {};
		json["employeeId"] = idEmp;
		json["detailId"] = idDevice;
		json["dateDeliverReceive"] = dateDeliverReceive;
		alert("data:" + idEmp + "data2" + idDevice + "data3" + dateDeliverReceive);
		var dataadd = JSON.stringify(json);
		alert(dataadd);   
		$.ajax({
			url : '/adddevdere',
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : dataadd,
			dataType : 'json',
			success : function(value) {
				console.log(value);
				$("#customControlValidation1").prop("checked",false);
				return true;
			},
			error : function(err) {
				console.log(err);
			}
		});
	}

	$('.btn-finish').click(function(event) {
		event.preventDefault();
		event.stopPropagation();
	});

	function onFinishCallback() {
		$('#wizard').smartWizard('showMessage','Finish Clicked');
	}

	
	$('.bt-delete').click(function(event){
		deleteHistory();
	})
	
	function deleteHistory(){
		var listId = [];
		if($('input[type="checkbox"]:checked').length===0){
			alert('Ban nen chon it nhat 1 user de xoa');
			return;
		}
		$('.tablefilter-Allocation-Return').find('input[type="checkbox"]:checked').each(function() {
			var $row = $(this).closest("tr");
			var id = $row.find(".idrecord").text();
			listId.push(id);
			
		});
		selected = confirm('Are you really delete !!!');
		if(selected){
			$.ajax({
				url:'/delallocation',
				type:'DELETE',
				contentType : 'application/json; charset=utf-8',
				data: JSON.stringify(listId),
				complete: function(res){
					if(res.status===200){
						$('.toastAllo-Return').click();
						loadTable();
					}else{
//						showMessage('nofitication',res.responseText,false);
					}
				}
			});
		}
	}
});