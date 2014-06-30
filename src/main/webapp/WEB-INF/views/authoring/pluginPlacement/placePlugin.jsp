<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
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
	<sf:form name="formChangeActor"  modelAttribute="actor" method="post"
		action="${pageContext.request.contextPath}/authoring/pluginPlacement/changeActor"
	>
		<p>
			Actor:
			<c:if test="${sessionScope.sessionInfoBean.actorId == 0}">All Actors</c:if>
			<c:if test="${sessionScope.sessionInfoBean.actorId != 0}">${sessionScope.sessionInfoBean.actorId}</c:if>
		</p>

		<sf:select path="id" id="selectActorList" >
			<sf:option value="0">All Actors</sf:option>
			<sf:options items="${actorsForThisRoleplay}"  itemValue="id" itemLabel="actorName" ></sf:options>
		</sf:select>

		<input type="submit" name="selectActorButton" id="selectActorButton" value="Submit">
	</sf:form>
	<sf:form name="formChangePhase"  modelAttribute="phase"  method="post"
		action="${pageContext.request.contextPath}/authoring/pluginPlacement/changePhase"
	>
		<p>
			Phase:
			<c:if test="${sessionScope.sessionInfoBean.phaseId != 0}">${sessionScope.sessionInfoBean.phaseId}</c:if>
		</p>
		
		<sf:select path="id" id="selectPhaseList" >
			<sf:options items="${phasesForThisRoleplay}"  itemValue="id" itemLabel="phaseName" ></sf:options>
		</sf:select>
		
		
		<input type="submit" name="selectPhaseButton" id="selectPhaseButton" value="Submit">
	</sf:form>
	<hr />
	<h2>Current Plugins</h2>
	<p>Click to Modify</p>
	<ol>

		<c:forEach var="pluginPointer" items="${pluginPointers}">
			<li><a
				href="${pageContext.request.contextPath}/authoring/pluginPlacement/customizePlugin/plugin/${pluginPointer.pluginId}"
				id="linkToPlugin_${pluginPointer.pluginId}"
			> <c:out value="${pluginPointer.pluginHeading}" />
			</a></li>
		</c:forEach>
	</ol>
	<hr />
	<p>Raw Plugins</p>
	<sf:form name="addPlugin" action="${pageContext.request.contextPath}/authoring/pluginPlacement/addPlugin"
		modelAttribute="authorAddPluginFormBean" method="POST"
	>

		<sf:select id="rawPluginId" path="rawPluginId">
			<c:forEach var="plugin" items="${plugins}">
				<option value="${plugin.id}"><c:out value="${plugin.pluginName}" /></option>
			</c:forEach>
		</sf:select>
		<sf:input type="text" id="pluginHeading" path="pluginHeading" />
		<input type="submit" name="addPluginButton" id="addPluginButton" value="Add">
	</sf:form>
	<p>&nbsp;</p>
	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>