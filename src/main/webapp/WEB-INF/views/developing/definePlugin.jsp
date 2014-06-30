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
			<h2>Create Plugin			</h2>
			<p>The plugin creation process consists of the following steps:</p>
			<p>1.) Define your Plugin (Done using the form below.)</p>
			<p>2.) Add objects, such as text or media elements, to your plugin.</p>
			<p>3.) Upload your Plugin files. (The API used to create these is described in a separate document.</p>
			<p>You may then go on to publish and/or export it.</p>
			<p>&nbsp;</p>
			<h2>Plugin Definition Information
			<c:if test="${devDefinePluginFormBean.pluginName.length() > 0}">
			: ${devDefinePluginFormBean.pluginName}
			</c:if>
			
			</h2>
			<p>
			
			</p>
			
			<sf:form method="POST"
				action="${pageContext.request.contextPath}/developing/definePlugin/${plugin.id}"
				modelAttribute="devDefinePluginFormBean" enctype="multipart/form-data">
				<table width="100%" border="1" cellspacing="0" cellpadding="2">
					<tr>
						<td>Author</td>
						<td><sf:input type="text" name="author" id="author" path="author" /></td>
					</tr>
					<tr>
						<td>Author URI</td>
						<td><sf:input type="text" name="authorURI" id="authorURI" path="authorURI" /></td>
					</tr>
					<tr>
						<td>Plugin Name</td>
						<td><sf:input type="text" name="pluginName" id="pluginName" path="pluginName" /></td>
					</tr>
					<tr>
						<td>Plugin URI</td>
						<td><sf:input type="text" name="pluginURI" id="pluginURI" path="pluginURI" /></td>
					</tr>
					<tr>
						<td>Plugin Description</td>
						<td><sf:input type="text" name="description" id="description" path="description" /></td>
					</tr>
					<tr>
						<td>Plugin Version</td>
						<td><sf:input type="text" name="pluginVersion"
							id="pluginVersion" path="pluginVersion" /></td>
					</tr>
					<tr>
						<td>Plugin License</td>
						<td><sf:input type="text" name="license" id="license" path="license" /></td>
					</tr>
										<tr>
						<td>Organization Code</td>
						<td><sf:input type="text" name="shortFormOrgName" id="shortFormOrgName" path="shortFormOrgName" /></td>
					</tr>
										<tr>
						<td>Plugin Short Name</td>
						<td><sf:input type="text" name="shortFormPluginName" id="shortFormPluginName" path="shortFormPluginName" /></td>
					</tr>
					<tr>
						<td>Major Release Number</td>
						<td><sf:input type="text" name="pluginReleaseMajorNumber" id="pluginReleaseMajorNumber" path="pluginReleaseMajorNumber" length="3" /></td>
					</tr>
					<tr>
						<td>Minor Release Number</td>
						<td><sf:input type="text" name="pluginReleaseMinorNumber" id="pluginReleaseMinorNumber" path="pluginReleaseMinorNumber" length="3" /></td>
					</tr>
					<tr>
						<td>Micro Release Number</td>
						<td><sf:input type="text" name="pluginReleaseMicroNumber" id="pluginReleaseMicroNumber" path="pluginReleaseMicroNumber" length="3" /></td>
					</tr>
					<tr>
						<td>Release Letters</td>
						<td><sf:input type="text" name="pluginReleaseLetters" id="pluginReleaseLetters" path="pluginReleaseLetters" length="3" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
						<c:if test="${plugin.id == 0}">
						<input type="submit" name="createButton" id="createButton" value="Create" />
						</c:if>
						<c:if test="${plugin.id != 0}">
						<input type="submit" name="updateButton" id="updateButton" value="Update" />
						</c:if>
							</td>
					</tr>
				</table>
			</sf:form>
			<p>&nbsp;</p>
			<c:if test="${plugin.id != 0}">
			<ol>
			<li><a href="${pageContext.request.contextPath}/developing/uploadPluginFiles/${devDefinePluginFormBean.getId()}" >
				Upload Files for this plugin.</a></li>
			<li><a href="${pageContext.request.contextPath}/developing/addObjectsToPlugin/${devDefinePluginFormBean.getId()}" >
				Add Objects to this plugin.</a>
			</li>
			<li><a href="${pageContext.request.contextPath}/developing/publishPlugin/${devDefinePluginFormBean.getId()}" >
				Publish plugin.</a>
			</li>
			</ol>
			</c:if>
			
			
			
			<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>