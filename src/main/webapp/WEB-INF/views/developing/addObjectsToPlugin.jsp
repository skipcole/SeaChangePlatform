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

<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>
<body>
	<!-- InstanceBeginEditable name="BodyRegion" -->
	<p>Add Objects to Plugin: ${plugin.getPluginName()}</p>
	<p>On this page you will</p>
	<table border="1">
		<tr>
			<td><h2>Current Objects</h2></td>
		</tr>
		<c:forEach var="pluginFileAssociation" items="${pluginFileAssociations}">
			<!-- TODO put in test to see if is document type object -->
			<tr>
				<td><a href="${pageContext.request.contextPath}/developing/modifyPluginObject/plugin/${pluginFileAssociation.pluginId}/document/${pluginFileAssociation.objectId}/"><c:out value="${pluginFileAssociation.objectName}" /></a></td>
			</tr>
			<!--  End of test if it is document object -->
		</c:forEach>
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