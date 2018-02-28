$(document).ready(function() {   
       	 //when send message is clicked
       	 $("#btnReplyUs").on('click',function(e){ 
       		e.preventDefault();
       	    //send the patient information to the server side when the book button is clicked and get response
         	var message = $('#message').val();
         	var subject = $('#subject').find(":selected").val();
         	var name =$('#name').val();
         	var email =$('#email').val();
       		 $.ajax({
       		        url:'replyMessage',
       		        data:{email:email,name:name,message:message,subject:subject},
       		        type:'POST',
       		        success:function(data){
       		        	 alert(data);
       		        	location.reload(true);
       		        },
       		        error:function(){
       		          alert('error');
       		        }
       		   }); 
       	 }); 
});