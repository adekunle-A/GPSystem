$(document).ready(function() { 
	//when search doctor button is clicked
 $('#searhDoctorbtn').on( "click", function(e) {
	 e.preventDefault();
     var docid = $("#username").val();
    
     $.ajax({
         url:'ViewDoctor',
         data:{docid:docid},
         type:'POST',
         success:function(data){ 
         	  $('.displaydocinfo').html(data);
         },error:function(){
           alert('error');
         }
      });
 });
});