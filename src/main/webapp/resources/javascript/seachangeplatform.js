
 
 	function warnme() {
		alert("warn");
	}
	// a test
	function getActor(actorId, divId) {
		
		$.ajax({
			url: "/SeaChangePlatform/xml/scauthtoken/1/actor/" + actorId + "/optMod/1",
			cache: false,
			dataType: "xml",
			success:  function(xml){
				$(xml).find("actor").each(function() {
					var actorName = $(this).find("actorName").text();
					//$('#' + divId).html(actorName);
					alert(actorName);
				});
			} 
		});
			
	}

