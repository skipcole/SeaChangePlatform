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
<title>Publish Roleplay</title>
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
	<h1>Publish Roleplay</h1>
	<p>&nbsp;</p>
	<sf:form method="POST"
		action="${pageContext.request.contextPath}/authoring/roleplay/publish/${roleplay.id}"
		modelAttribute="authorPublishRoleplayFormBean">
		<table width="100%" border="1" cellspacing="0" cellpadding="2">
			<tr>
				<td>Role Play Name</td>
				<td>${roleplay.roleplayName}</td>
			</tr>
			<tr>
				<td>Roleplay State</td>
				<td>
				<c:if test="${roleplay.publishedState == 0}">
					<spring:message code="author.roleplay.publish.notpublished" />
				</c:if>
				<c:if test="${roleplay.publishedState == 1}">
					<spring:message code="author.roleplay.publish.readyfortesting" />
				</c:if>
				<c:if test="${roleplay.publishedState == 2}">
					<spring:message code="author.roleplay.publish.published" />
				</c:if>
				
				</td>
			</tr>
			<c:if test="${roleplay.publishedState == 0}">
				<tr>
					<td><spring:message code="author.roleplay.publish.action" /></td>
					<td><sf:input type="submit" name="makeTestableButton"
							id="makeTestableButton" path="makeTestableButton"
							value="Make Testable" /></td>
				</tr>
			</c:if>
			<c:if test="${roleplay.publishedState == 1}">
				<tr>
					<td><spring:message code="author.roleplay.publish.action" /></td>
					<td><sf:input type="submit" name="publishButton"
							id="publishButton" path="publishButton"
							value="Publish" /></td>
				</tr>
			</c:if>
		</table>
	</sf:form>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>