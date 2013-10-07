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
	<p>Customize Plugin</p>
	<p>&nbsp;</p>
<p>
On this page we allow the author to set objects used by this plugin.
</p>
<hr />
<p>Loop Over Documents</p>
	<p>&nbsp;</p>
	<c:forEach var="pluginObjectDocument" items="${pluginObjectDocuments}">
	<sf:form name="form_pod_${pluginObjectDocument.getId()}" method="POST"
		action="${pageContext.request.contextPath}/authoring/plugin/${plugin.getId()}/customizePluginDocument/${pluginObjectDocument.getId()}"
		modelAttribute="acpdfb_${pluginObjectDocument.getId()}">
		<table border="1">
			<tr>
				<td><label for="documentName">Document Name</label></td>
				<td><sf:input type="text" name="documentName" id="documentName_${pluginObjectDocument.getId()}"
						path="documentName" /></td>
			</tr>
<tr>
				<td>Document Text</td>
			<td><sf:textarea type="text" name="documentText" id="documentText_${pluginObjectDocument.getId()}"
						path="documentText" rows="5" cols="30" /></td>
			</tr>
			<tr>
				<td>Update</td>
			<td><input type="submit" name="addObject" id="addObject"
				value="Submit"></td>
			</tr>
		</table>
	</sf:form>
	</c:forEach>
<hr />
<p>
<a href="${pageContext.request.contextPath}/authoring/rpId/${sessionScope.sessionInfoBean.roleplayId}/pluginPlacement/" id="authorPlacePlugins">Back </a>
</p>

	<p>&nbsp;</p>
<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>