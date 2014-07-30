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
<link href="/SeaChangePlatform/resources/javascript/jquery-ui-1.10.3.custom/css/start/jquery-ui-1.10.3.custom.min.css"
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
	<c:forEach var="pra" items="${pras}">
	<form method="POST" id="facAssignPlayersFormBean${pra.actorId}"
		action="${pageContext.request.contextPath}/facilitating/assignPlayers/${rpim.id}/actorId/${pra.actorId}/praId/${pra.id}" >
		<tr>
			<td><c:out value="${pra.actorName}" />
			</td>
			<td><input type="text" name="userName" id="userName${pra.id}" /></td>
				<td>
						<input type="submit" name="assignButton${pra.id}${pra.actorId}" id="assignButton${pra.id}${pra.actorId}"
							value="Assign" />
				</td>
				<td>
					<input type="radio" name="roleType" id="roleTypeNormal${pra.id}" value="${normalValue}" ${pra.normalSelected}  />Normal<br />
					<input type="radio" name="roleType" id="roleTypeControl${pra.id}" value="${controlValue}" ${pra.controlSelected} />Control<br />
					<input type="radio" name="roleType" id="roleTypeObserver${pra.id}" value="${observerValue}" ${pra.observerSelected} />Observer
				</td>
		</tr>
		
	</form>
	</c:forEach>
	</table>
	<p>&nbsp;</p>
	<p>Move on to Launch</p>
	<a href="/SeaChangePlatform/facilitating/launchRPIM/${rpim.id}" id="linkToLaunch${rpim.id}"><c:out value="${rpim.roleplayInMotionName}" /></a>

	

	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>