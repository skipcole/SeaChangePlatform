<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<p>Add Objects to Plugin: ${plugin.getPluginName()}</p>
	<p>On this page you will associate objects (documents, media, injects, etc.) with your plugin that the roleplay
	author can then modify.</p>
	<table border="1">
		<tr>
			<td colspan="3"><h2>Current Objects</h2></td>
		</tr>
		<c:choose>
			<c:when test="${fn:length(pluginObjectAssociations) gt 0}">
			<tr>
				<td>Index</td>
				<td>Object (click on the link to edit it)</td>
				<td>Remove</td>
			</tr>
			<c:forEach var="pluginObjectAssociation" items="${pluginObjectAssociations}">
				<!-- TODO put in test to see if is document type object -->
				<tr>
					<td><c:out value="${pluginObjectAssociation.objectIndex}" /></td>
					<td><a href="${pageContext.request.contextPath}/developing/modifyPluginObject/plugin/${pluginObjectAssociation.pluginId}/document/${pluginObjectAssociation.objectId}/"><c:out value="${pluginObjectAssociation.objectName}" /></a></td>
					<td align="right"><a href="${pageContext.request.contextPath}/developing/removePluginObjectAssociation/plugin/${pluginObjectAssociation.objectId}/"><i>remove</i></a></td>
				</tr>
				<!--  End of test if it is document object -->
			</c:forEach>
			</c:when>
			<c:when test="${fn:length(pluginObjectAssociations) le 0}">
				<td colspan="3">None</td>
			</c:when>
		</c:choose>
	</table>
	<p>&nbsp;</p>
	<sf:form name="form1" method="POST" 
		action="${pageContext.request.contextPath}/developing/addObjectsToPlugin/${plugin.id}"
		modelAttribute="devAddObjectsToPluginFormBean" >
		<table border="1">
			<tr>
				<td colspan="2" align="center"><h2>Add Object</h2></td>
			</tr>
			<tr>
				<td><label for="objectType">Object Type</label></td>
				<td><sf:select name="objectType" id="objectType" path="objectType">
						<option value="PluginObjectDocument" selected>Document</option>
						<option value="PluginObjectMedia">Media</option>
				</sf:select></td>
			</tr>
			<tr>
				<td><label for="objectName">Object Name</label></td>
				<td><sf:input type="text" name="objectName" id="objectName" path="objectName" /></td>
			</tr>
			<tr>
				<td>Object Description</td>
				<td><sf:input type="text" name="objectDescription" id="objectDescription" path="objectDescription" /></td>
			</tr>
			<tr>
				<td>Granularity</td>
				<td><label for="objectGranularity"></label> <sf:select
					name="objectGranularity" id="objectGranularity" path="objectGranularity">
						<option value="roleplay" selected>Roleplay</option>
						<option value="rpim">Roleplay in Motion</option>
						<option value="roleplayset">Set of Roleplays</option>
				</sf:select></td>
			</tr>
			<tr>
				<td>Add</td>
				<td><input type="submit" name="addObject" id="addObject"
					value="Submit"></td>
			</tr>
		</table>
	</sf:form>
	<p>&nbsp;</p>
	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>