 $(document).ready(function(){
        	   // Toolbar extra buttons
            var btnFinish = $('<button></button>').text('Finish')
                                             .addClass('btn btn-info btn-finish')
                                             .attr('disabled',true)
                                             .on('click', function(){
                                                    if( !$(this).hasClass('disabled')){
                                                        var elmForm = $("#myForm");
                                                        if(elmForm){
                                                            elmForm.validator('validate');
                                                            var elmErr = elmForm.find('.has-error');
                                                            if(elmErr && elmErr.length > 0){
                                                                alert('Oops we still have error in the form');
                                                                return false;
                                                            }else{
                                                                alert('Great! we are ready to submit form');
                                                                var idEmp = getidEmployee();
                                                                var idDevice = getIdDevice();
                                                                var dateDeliverReceive = moment(dateDeliverReceive).format('YYYY-MM-DDThh:mm:ss.000+0000');
                                                            	   	
                                                         	
                                                         		
                                                         		  
                                                         		
                                                                   callApiAddAlocation(idEmp, idDevice, dateDeliverReceive);
                                                                return false;
                                                            }
                                                          
                                                        }
                                                    }
                                                });
            var btnCancel = $('<button></button>').text('Cancel')
                                             .addClass('btn btn-danger')
                                             .on('click', function(){
                                                    $('#smartwizard').smartWizard("reset");
                                                    $('#myForm').find("input, textarea").val("");
                                                });



            // Smart Wizard
            $('#smartwizard').smartWizard({
                    selected: 0,
                    theme: 'dots',
                    transitionEffect:'fade',
                    toolbarSettings: {toolbarPosition: 'bottom',
                                      toolbarExtraButtons: [btnFinish, btnCancel]
                                    },
                    anchorSettings: {
                                markDoneStep: true, // add done css
                                markAllPreviousStepsAsDone: true, // When a step selected by url hash, all previous steps are marked done
                                removeDoneStepOnNavigateBack: true, // While navigate back done step after active step will be cleared
                                enableAnchorOnDoneStep: true // Enable/Disable the done steps navigation
                            }
                 });

            $("#smartwizard").on("leaveStep", function(e, anchorObject, stepNumber, stepDirection) {
                var elmForm = $("#form-step-" + stepNumber);
                // stepDirection === 'forward' :- this condition allows to do the form validation
                // only on forward navigation, that makes easy navigation on backwards still do the validation when going next

                if(stepDirection === 'forward' && elmForm){
                    elmForm.validator('validate');
                    var elmErr = elmForm.children('.has-error');
                    if(elmErr && elmErr.length > 0){
                        // Form validation failed
                        return false;
                    }
                  		
                    if(stepNumber === 0){
                    	 var idEmp = getidEmployee();
                    	 if(typeof idEmp != 'undefined'){
                    		 return true;
                    	 }
                    } else if(stepNumber === 1){
                    	 var idDevice = getIdDevice();
                    	 if(typeof idDevice != 'undefined'){
                    		 return true;
                    	 }                            	
                    }  else {
                    	return true;
                    }
                    $('.toastDevice').click();
                    return false;
                }
                return true;
            });

            $("#smartwizard").on("showStep", function(e, anchorObject, stepNumber, stepDirection) {
                // Enable finish button only on last step
                if(stepNumber == 2){
                	var idEmp = getidEmployee();
                	callApiGetRequest(idEmp);
                } else if(stepNumber == 3){
                    $('.btn-finish').removeAttr('disabled');
                }else{
               
                }
            });	
  
            
            function getidEmployee(){
            	var idEmployee;
      		  $('#tablefilter-emp').find('input[type="checkbox"]:checked').each(function () {
                	var $row = $(this).closest("tr");
                	idEmployee = $row.find(".idEmp").text();
                	
                 });
      		return idEmployee;
      	  }   
            function getIdDevice(){
            	var idDevice;
      		  $('#tablefilter-device').find('input[type="checkbox"]:checked').each(function () {
                	var $row = $(this).closest("tr");
                	idDevice = $row.find(".idDevice").text();
                	
                 });
      		return idDevice;
      	  }
            
            function callApiGetRequest(id){
            	json = {};
            	json['id'] = id;
            	
            	   $.ajax({
                       url: "/filterrequest",
     					data: JSON.stringify(json),           
                       dataType: "json",
               		contentType : "application/json; charset=utf-8",
                       type: "POST",
                       success: function (data) {
                    	   $('.tablefilter-request').html("");
                           $('#tablefilter-request').removeAttr('hidden',"");
                		console.log(JSON.stringify(data));
                		  $.each(data, function (key, request) {
                    		  var datetime = moment(request.updateDate).format('DD-MM-YYYY , HH:mm:ss');
                    		  $('.tablefilter-request').append("<tr>" +
                    				"<td class=\"idDevice\" hidden=\"\">"+request.id+"</td>" +
                    		  		"<td><div class=\"custom-control custom-checkbox\">" +
                    		  		"<input type=\"checkbox\" class=\"custom-control-input checkSingle-request\" id=\"checkbox-request"+key+"\">" +
                    		  		"<label class=\"custom-control-label\" for=\"checkbox-request"+key+"\"></label></div></td>" +
                    		  		"<td>"+datetime+"</td>" +
                    		  		"<td>"+request.content+"</td>" +
                    		  		"</tr>");
         	                    });
                		  return false;
                       },
               	error : function(err) {
        
               
         		}
                   });
            }
            
            function callApiAddAlocation(idEmp, idDevice, dateDeliverReceive){
            	var json = {};              
            	json["employeeId"] = idEmp;
            	json["detailId"] = idDevice;
            	json["dateDeliverReceive"] = dateDeliverReceive;              	
         		alert("data:"+idEmp + "data2" + idDevice +"data3"+ dateDeliverReceive);
         			var dataadd = JSON.stringify(json);
         			 alert(dataadd);   
         			$.ajax({
     					url : '/adddevdere',
     					  type: 'POST',
     						contentType : "application/json; charset=utf-8",
     						data : dataadd,
     						dataType : 'json',
     						success : function(value) {
     						console.log(value);
     						$("#customControlValidation1").prop( "checked", false );
     						
     					},
     					success : function(value) {
     						return true;
     					},
     					error : function(err) {
     						console.log(err);	 
     					}
     				});
            }
        });