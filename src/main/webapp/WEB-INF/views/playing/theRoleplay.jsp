<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html>
<!-- InstanceBegin template="/Templates/SeaChangePlatform.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>Playing</title>
<!-- InstanceEndEditable -->

<link href="/SeaChangePlatform/resources/css/SeaChangePlatform.css"
	rel="stylesheet" type="text/css">
<link
	href="/SeaChangePlatform/resources/javascript/jquery-ui-1.10.3.custom/css/start/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" type="text/css">

<script src="/SeaChangePlatform/resources/javascript/jquery-1.9.1.js"></script>

<script
	src="/SeaChangePlatform/resources/javascript/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>

<!-- InstanceBeginEditable name="head" -->



<link href="/SeaChangePlatform/Templates/AuthorTemplate.css"
	rel="stylesheet" type="text/css">
<link href="/SeaChangePlatform/Templates/FacilitatorTemplate.css"
	rel="stylesheet" type="text/css">

<script>
$(document).ready(function() {

	$(function() {
		$("#tabs").tabs();
	});
    
});


</script>
<script>
function myFunction(pluginIndex)
{
	alert("on plugin: " + pluginIndex);
	
	var tabs = $("#tabs");
    $(tabs).tabs();
    $(tabs).tabs( "option", "active", pluginIndex );
    
}
</script>
<!-- InstanceEndEditable -->
</head>
<body>
	<!-- InstanceBeginEditable name="BodyRegion" -->
	<div id="wrapper">

		<div id="header">

			<div class="welcome_msg">Welcome Player!</div>

		</div>
		<hr />

		<div id="tabs">
			<ul>
				<c:forEach var="pluginPointer" items="${pluginPointers}">
					<li><a
						href="${pageContext.request.contextPath}/playing/showPlugin/${pluginPointer.id}"
						onclick="myFunction(${pluginPointer.pluginIndex});return false">
							<c:out value="${pluginPointer.pluginHeading}" />
					</a></li>
				</c:forEach>
			</ul>

		</div>
		<hr />
	</div>



	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd -->
</html>