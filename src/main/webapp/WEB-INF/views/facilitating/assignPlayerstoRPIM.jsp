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

    
<!-- InstanceBeginEditable name="doctitle" -->
<title>Assign Players</title>
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
    
<!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- script src="//code.jquery.com/jquery.js"></script  -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/SeaChangePlatform/resources/js/bootstrap.min.js"></script>
<!-- Bootstrap -->

<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>
<body>
	<!-- InstanceBeginEditable name="BodyRegion" -->
	<h2>Assign Players to Roleplay in Motion</h2>

<table width="100%" border="1" cellspacing="0" cellpadding="2">
	<c:forEach var="actor" items="${actorsForThisRoleplay}">
	<sf:form method="POST" id="facAssignPlayersFormBean${actor.id}"
		action="${pageContext.request.contextPath}/facilitating/assignPlayers/${rpim.id}/actorId/${actor.id}"
		modelAttribute="facAssignPlayersFormBean">
		<tr>
			<td><c:out value="${actor.actorName}" /></td>
			<td><sf:input type="text" name="userName" id="userName${actor.id}" path="userName" /></td>
				<td>
						<input type="submit" name="assignButton" id="assignButton"
							value="Assign" />
				</td>
			</tr>
		
	</sf:form>
	</c:forEach>
	</table>
	<p>&nbsp;</p>
	<p>Move on to Launch</p>
	<a href="/SeaChangePlatform/facilitating/launchRPIM/${rpim.id}" id="linkToLaunch${rpim.id}"><c:out value="${rpim.roleplayInMotionName}" /></a>

	

	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>