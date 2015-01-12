<html>
<head>

</head>
<body>

<h2>Actors</h2>
<button id="btnActor1">Load Actor 1</button>
<button id="btnActor2">Load Actor 2</button>

<p><div name="actorData" id="actorData"></div></p>
<h2>Phases</h2>
Phase 1

<h2>Plugin</h2>
Plugin 1

<script src="/SeaChangePlatform/resources/javascript/jquery-1.9.1.js"></script>
<script src="/SeaChangePlatform/resources/javascript/seachangeplatform.js"></script>
<script>
$(document).ready(function(){
	
		function getActor(actorId, divId) {
		
		$.ajax({
			url: "/SeaChangePlatform/xml/scauthtoken/1/actor/" + actorId,
			cache: false,
			dataType: "xml",
			success:  function(xml){
				$(xml).find("actor").each(function() {
					var actorName = $(this).find("actorName").text();
					$('#' + divId).html(actorName);
					alert(actorName);
				});
			} 
		});
			
	}
	
	$("#btnActor1").click(function() { getActor(1, "actorData"); });
	$("#btnActor2").click(function() { warnme(); });
	
});
</script>
</body>
</html>