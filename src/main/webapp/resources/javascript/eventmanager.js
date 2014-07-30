
 
 	function getSimEvent() {

	  	$.get('${pageContext.request.contextPath}/playing/getEvents/1', 
		
			function(myFunction){
	  			alert('here');
			});
	}


 	function startTimers()
 	{
 		getSimEvent();
 		setTimeout("startTimers()", 600000);
 	}
 	
 	$( document ).ready(function() {
 		startTimers();
 	});