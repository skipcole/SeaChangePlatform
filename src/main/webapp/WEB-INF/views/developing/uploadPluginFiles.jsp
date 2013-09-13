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
	<p>Upload Files for Plugin: ${plugin.getPluginName()}</p>
	<p>On this page you will upload files for this plugin</p>
	<p>&nbsp;</p>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/developing/uploadPluginFiles/${plugin.id}"
		modelAttribute="devUploadPluginFilesFormBean"
		enctype="multipart/form-data">
		<table width="100%" border="1" cellspacing="0" cellpadding="2">
			<tr>
				<td>File Description</td>
				<td><sf:input type="text" name="fileDescription"
						id="fileDescription" path="fileDescription" /></td>
			</tr>
			<tr>
				<td>plugin file</td>
				<td><input type="file" name="pluginFile" id="pluginFile"
					path="pluginFile" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" name="createButton" id="createButton"
					value="Create" /></td>
			</tr>
		</table>
	</sf:form>
	<p>&nbsp;</p>
	<h3>Current List of Attached Files</h3>
	<c:forEach var="pluginFile" items="${pluginFiles}">
		<li><c:out value="${pluginFile.fileName}" /></li>
	</c:forEach>
	<p>
		Move on to <a
			href="${pageContext.request.contextPath}/developing/addObjectsToPlugin/${plugin.getId()}">
			add Objects </a> for this plugin.
	</p>
	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>