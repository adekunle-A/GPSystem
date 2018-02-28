//$(document).ready(function(e) {   
//	var uid = $('#uid').val();
//    //send the patient information to the server side when the book button is clicked and get response
//     $.ajax({
//        url:'PatientHelp',
//        data:{username:uid},
//        type:'POST',
//        success:function(data){
//        	var splitdata = data.split('/');
//        	var name =splitdata[0];
//        	var email = 	 splitdata[1];
//        	$('#name').val(name);
//        	$('#email').val(email);
//       	 //when send message is clicked
//       $("#btnContactUs").on('click',function(e){ 
//        	e.preventDefault();
//    		var message = $('#message').val();
//        var subject = $('#subject').find(":selected").val();
//	     if(!$('#message').val()){
//	        	alert("Please check your entries");
//	        }else{
//		      	$.ajax({
//			    	    url: "PatientSendMessage", 
//			    	    method: "POST",
//			    	    data: {username:uid,email:email,name:name,message:message,subject:subject},
//			    	    success:function(data){
//			    	    	location.reload(true);
//					      	alert(data);
//			    	    },
//			            error:function(){
//			              alert('error');
//			         }
//			   	});
//	        }
//        	});
//        },
//        error:function(){
//          alert('error');
//        }
//     }); 
//});