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
			<p>Enter Role Play Audience</p>

			<p> you are working on role play <c:out value="${sessionScope.sessionInfoBean.roleplayName}" />
			</p>

			<sf:form id="roleplay" modelAttribute="roleplay" METHOD="POST" 
				action="${pageContext.request.contextPath}/authoring/rolePlay/enterAudience/${sessionScope.sessionInfoBean.roleplayId}" label="Roleplay Audience">
				
				<sf:input type="hidden" path="roleplayName" />
				<sf:textarea path="audience" />

				<div class="input-append">
					<input type="submit"	value="Enter Audience" name="enterSave" id="enterSave" />
					<input type="submit"	value="Cancel" id="cancel" name="cancel" />
				</div>
			</sf:form>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>