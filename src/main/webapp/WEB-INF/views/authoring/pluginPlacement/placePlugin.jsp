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
<title>Authoring Section - Sea Change Simulations, LLC</title>
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
	<form name="formChangeActor" method="post"
		action="${pageContext.request.contextPath}/authoring/pluginPlacement/changeActor">
		<p>
			Actor:
			<c:if test="${sessionScope.sessionInfoBean.actorId == 0}">All Actors</c:if>
			<c:if test="${sessionScope.sessionInfoBean.actorId != 0}">${sessionScope.sessionInfoBean.actorId}</c:if>
		</p>

		<label for="selectActor"></label> <select name="selectActor"
			id="selectActor">
			<option value="0">All Actors</option>
			<option value="1">Hard Coded</option>
			<c:forEach var="actor" items="${actorsForThisRoleplay}">
				<option value="<c:out value="${actor.id}" />"><c:out
						value="${actor.actorName}" /></option>
			</c:forEach>

		</select> <input type="submit" name="selectActorButton" id="selectActorButton"
			value="Submit">
	</form>
	<form name="formChangePhase" method="post"
		action="${pageContext.request.contextPath}/authoring/pluginPlacement/changePhase">
		<p>
			Phase:
			<c:if test="${sessionScope.sessionInfoBean.phaseId == 0}">All Phases</c:if>
			<c:if test="${sessionScope.sessionInfoBean.phaseId != 0}">${sessionScope.sessionInfoBean.phaseId}</c:if>
		</p>

		<label for="selectPhase"></label> <select name="selectPhase"
			id="selectPhase">
			<option value="1">Phase1</option>
		</select> <input type="submit" name="selectPhaseButton" id="selectPhaseButton"
			value="Submit">
	</form>
	<hr/>
	<h2>Current Plugins</h2>
	<p>Click to Modify</p>
		<ol>
		
		<c:forEach var="pluginPointer" items="${pluginPointers}">
			<li><a href ="${pageContext.request.contextPath}/authoring/pluginPlacement/customizePlugin/plugin/${pluginPointer.pluginId}"
				id="linkToPlugin_${pluginPointer.pluginId}">
				<c:out value="${pluginPointer.pluginHeading}" />
			</a></li>
		</c:forEach>
		</ol>
	<hr />
	<p>Index:</p>
	<p>Plugins</p>
	<sf:form name="addPlugin" action="${pageContext.request.contextPath}/authoring/pluginPlacement/addPlugin"
		modelAttribute="authorAddPluginFormBean" method="POST">

		<sf:select name="rawPluginId" id="rawPluginId" path="rawPluginId">
			<c:forEach var="plugin" items="${plugins}">
				<option value="${plugin.id}"><c:out value="${plugin.pluginName}" /></option>
			</c:forEach>
		</sf:select>
		<sf:input type="text" name="pluginHeading" id="pluginHeading" path="pluginHeading" />
		<input type="submit" name="addPluginButton" id="addPluginButton"
			value="Add">
	</sf:form>
	<p>&nbsp;</p>
	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>