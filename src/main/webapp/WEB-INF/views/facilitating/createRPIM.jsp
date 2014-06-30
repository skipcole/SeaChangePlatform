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
<title>Developer Section - Sea Change Simulations, LLC</title>
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
			<h2>Create Roleplay in Motion		</h2>
			
			</h2>
			
			<sf:form method="POST"
				action="${pageContext.request.contextPath}/facilitating/createRPIM/${roleplay.id}/${rpim.id}"
				modelAttribute="facCreateRPIMFormBean">
				<table width="100%" border="1" cellspacing="0" cellpadding="2">
					<tr>
						<td>Roleplay Name</td>
						<td>${roleplay.roleplayName}</td>
					</tr>
										<tr>
						<td>name</td>
						<td><sf:input type="text" name="roleplayInMotionName" id="roleplayInMotionName" path="roleplayInMotionName" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
						<c:if test="${rpim.id == 0}">
						<input type="submit" name="createButton" id="createButton" value="Create" />
						</c:if>
						<c:if test="${rpim.id != 0}">
						<input type="submit" name="updateButton" id="updateButton" value="Update" />
						</c:if>
							</td>
					</tr>
				</table>
			</sf:form>
			<p>&nbsp;</p>
			
			<p> Below are all current rpims:</p>

	<c:forEach var="this_rpim" items="${all_rpims}">
		<li><a href="/SeaChangePlatform/facilitating/assignPlayers/${this_rpim.id}" id="linkToAssignPlayers${roleplay.id}"><c:out value="${this_rpim.roleplayInMotionName}" /></a></li>
	</c:forEach>
			
			<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>