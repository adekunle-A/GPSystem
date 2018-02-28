$(document).ready(function() {   
    $('#getinfo').tokenfield();
    $( "#searchbtn" ).on( "click", function(e) {
    	e.preventDefault();
    	   var searchinfo = $("#getinfo").val();
    	   var getselected = $('#searchby').find(":selected").val();
    $.ajax({
        url:'diagnosePatient',
        data:{getselected:getselected,searchinfo:searchinfo},
        type:'POST',
        success:function(data){
        	try
        	{
        	 	var response=jQuery.parseJSON(data);
            	var row ="";
    	        	if(response.results.bindings.length > 0){
    	        	    for(var i=0; response.results.bindings.length > i; i++){
    		        	    	var disease = response.results.bindings[i].Diseases.value.substring(response.results.bindings[i].Diseases.value.indexOf('#') +1);
    		        	    	var symptoms = response.results.bindings[i].Symptoms.value.substring(response.results.bindings[i].Symptoms.value.indexOf('#') +1);
    		        	    	var location = response.results.bindings[i].Partof.value.substring(response.results.bindings[i].Partof.value.indexOf('#') +1);
    		        	    var comment = response.results.bindings[i].description.value.substring(response.results.bindings[i].description.value.indexOf('#') +1);
    		        	    	row+='<tr><td>'+disease+'</td>';
    		        	    	row+='<td>'+symptoms+'</td>';
    		        	    	row+='<td>'+location +'</td>';
    		        	    	row+='<td>'+comment +'</td></tr>';
    	        		}
    	        	    $('.displa').html(row);
    	        }else{
    	        		$(".msg").html("No result Found");
    	        }
        	}
        	catch(e)
        	{
        		$(".displa").html("No result Found");
        	}
        },error:function(){
          alert('error');
        }
     });
    });
});