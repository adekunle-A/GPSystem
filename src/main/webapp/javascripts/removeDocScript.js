$(document).ready(function() { 
	//when search doctor button is clicked
 $('#removeDoctorbtn').on( "click", function(e) {
	 e.preventDefault();
     var docid = $("#username").val();
     var role = $("#role").find(":selected").text();
     alert(docid);
     $.ajax({
         url:'removeDoctor',
         data:{docid:docid,role:role},
         type:'POST',
         success:function(data){ 
         	  $('.status').html(data);
         },error:function(){
           alert('error');
         }
      });
 });
});