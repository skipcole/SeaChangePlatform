<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html><!-- InstanceBegin template="/Templates/SeaChangePlatform.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>Facilitating Section</title>
<!-- InstanceEndEditable -->

<link href="/SeaChangePlatform/resources/css/SeaChangePlatform.css"
	rel="stylesheet" type="text/css">
<link href="/SeaChangePlatform/javascript/jquery-ui-1.10.3.custom/css/start/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" type="text/css">

<script src="/SeaChangePlatform/resources/javascript/jquery-1.9.1.js"></script>
<script
	src="/SeaChangePlatform/resources/javascript/seachangeplatform.js"></script>
<script
	src="/SeaChangePlatform/javascript/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>

<!-- InstanceBeginEditable name="head" -->



<link href="/SeaChangePlatform/Templates/AuthorTemplate.css" rel="stylesheet" type="text/css">
<link href="/SeaChangePlatform/Templates/FacilitatorTemplate.css" rel="stylesheet" type="text/css">
<!-- InstanceEndEditable -->
</head>
<body>
	<!-- InstanceBeginEditable name="BodyRegion" -->
<div id="wrapper">
 
	<div id="header">
    
    	<div class="welcome_msg">Welcome Facilitator!</div>
    
    </div>
 
	<div id="column-center">
<p>Last Roleplay worked on: X</p>

</div>
 
</div>
<p> Below are listed role plays you may launch:</p>

	<c:forEach var="roleplay" items="${roleplays}">
		<li><a href="/SeaChangePlatform/facilitating/createRPIM/${roleplay.id}/0" id="linkToRoleplay${roleplay.id}"><c:out value="${roleplay.roleplayName}" /></a></li>
	</c:forEach>



<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>