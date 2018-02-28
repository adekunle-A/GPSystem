$(document).ready(function() {   
    $( "#updatepresbtn" ).on( "click", function(e) {
    		e.preventDefault();
        var bookingid = $("#patientid").val();
        var docid = $("#docid").val();
        var appdate = $("#appdate").val();
        var status = $('#status').find(":selected").text();
    $.ajax({
        url:'updatePrescriptionStatus',
        data:{docid:docid,bookingid:bookingid,appdate:appdate,status:status},
        type:'POST',
        success:function(data){ 
          	 alert(data);
        	  $('.status').html(data);
        },error:function(){
          alert('error');
        }
     });
    });
});