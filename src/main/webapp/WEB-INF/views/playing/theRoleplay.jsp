<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />

<title>Playing</title>

<link href="/SeaChangePlatform/resources/css/SeaChangePlatform.css"
	rel="stylesheet" type="text/css">
<link
	href="/SeaChangePlatform/resources/javascript/jquery-ui-1.10.3.custom/css/start/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" type="text/css">

</head>
<body>
	
	<script src="/SeaChangePlatform/resources/javascript/jquery-1.9.1.js"></script>
	<script
		src="/SeaChangePlatform/resources/javascript/seachangeplatform.js"></script>
	<script
		src="/SeaChangePlatform/resources/javascript/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script
		src="/SeaChangePlatform/resources/javascript/eventmanager.js"></script>
		
		<script src="/SeaChangePlatform/resources/js/bootstrap.min.js"></script>


   <div width="100%" border="0" >  
			<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">     
				<c:forEach var="pluginPointer" items="${pluginPointers}">
					<li><a href="#pane_${pluginPointer.id}" id="tabpane_${pluginPointer.id}" data-toggle="tab">
					<c:out value="${pluginPointer.pluginHeading}" /></a>
					</li>
				</c:forEach>
			</ul>

			<div id="my-tab-content" class="tab-content">
				<c:set var="activeTab" value=" active" />

				<c:forEach var="pluginPointer" items="${pluginPointers}">

					<div class="tab-pane<c:out value='${activeTab}'/>"
						id="pane_${pluginPointer.id}" style="border: 0;">

						<iframe	src="${pageContext.request.contextPath}/playing/showPlugin/${pluginPointer.id}"
							width="100%" height="100%" seamless="seamless"> </iframe>
					</div>

					<c:set var="activeTab" value="" />
				</c:forEach>

			</div>
			<!--  End of div tab content -->


	</div> <!--  end of div container -->

	<!--  left in for debugging 6/29  
	<c:forEach var="pluginPointer" items="${pluginPointers}">

		<a href="${pageContext.request.contextPath}/playing/showPlugin/${pluginPointer.id}" >Try this one: ${pageContext.request.contextPath}/playing/showPlugin/${pluginPointer.id}<br /> </a>

	</c:forEach>
	  end of left in for debugging 6/29  -->

<script>

var highestReceivedAlert = 0;

function getAlerts() {
	
	var url = "${pageContext.request.contextPath}/playing/getEventJSON/" + highestReceivedAlert + ".json";
	
	alert(url);
	$.getJSON( url, function( data ) {
		alert(data.alertText);
		highestReceivedAlert = (data.alertId);
	});
}

</script>
<script>
$( document ).ready(function() {

	setInterval(function() {
		getAlerts();
	}, 5000);
	
	
});

</script>
<script>
<script>
jQuery(document).ready(function ($) {
    $('#tabs').tab();
});
</script>
</script>

</body>

</html>