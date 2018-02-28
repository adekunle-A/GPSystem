$(document).ready(function() {   
	$('#symptoms').tokenfield();
    $( "#addDiagnosisbtn" ).on( "click", function(e) {
    		e.preventDefault();
        var bookingid = $("#patientid").val();
        var docid = $("#docid").val();
        var appdate = $("#appdate").val();
        var symptoms = $("#symptoms").val();
        var disease = $("#disease").val();
        var treatment = $("#treatment").val();
        var status = $('#status').find(":selected").text();
    $.ajax({
        url:'addDiagnosisInfo',
        data:{docid:docid,bookingid:bookingid,appdate:appdate,symptoms:symptoms,disease:disease,treatment:treatment,status:status},
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