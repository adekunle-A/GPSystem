 $(document).ready(function() {  
	 $('#checkpHistoryform').on('submit', function(e) {
 		e.preventDefault();
        var patientid = $("#patientid").val();
	    $.ajax({
	        url:'checkPatientHistory',
	        data:{patientid:patientid},
	        type:'POST',
	        success:function(data){ 
	     
	        	$('.getcheckhistoryrow').html(data);
	        },error:function(){
	          alert('error');
	        }
	     });
	});
}); 