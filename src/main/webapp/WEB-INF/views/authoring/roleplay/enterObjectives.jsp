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
<link href="/SeaChangePlatform/resources/javascript/jquery-ui-1.10.3.custom/css/start/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" type="text/css">
<link href="/SeaChangePlatform/resources/javascript/jQuery-TE_v.1.4.0/jquery-te-1.4.0.css"
	rel="stylesheet" type="text/css">
	
    
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
<script src="/SeaChangePlatform/resources/javascript/jquery-1.9.1.js"></script>
<script
	src="/SeaChangePlatform/resources/javascript/seachangeplatform.js"></script>
<script
	src="/SeaChangePlatform/javascript/resources/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
	
<script
	src="/SeaChangePlatform/resources/javascript/jQuery-TE_v.1.4.0/jquery-te-1.4.0.min.js"></script>
<script>
$( document ).ready(function() {
	$(".editor").jqte();
});
</script>
	<!-- InstanceBeginEditable name="BodyRegion" -->
			<p>Enter Roleplay Objectives x</p>

			<p> You are working on role play <em></em><c:out value="${sessionScope.sessionInfoBean.roleplayName}" /></em>
			</p>

			<sf:form id="roleplay" modelAttribute="roleplay" METHOD="POST" 
				action="${pageContext.request.contextPath}/authoring/roleplay/enterObjectives/${sessionScope.sessionInfoBean.roleplayId}" label="Roleplay Objectives">
				
				<sf:input type="hidden" path="roleplayName" />
				<sf:textarea class="editor" name="objectives" id="objectives" path="objectives" height="100%" />

				<div class="input-append">
					<input type="submit"	value="Enter Objectives" name="enterSave" id="enterSave" />
					<input type="submit"	value="Cancel" id="cancel" name="cancel" />
				</div>
			</sf:form>

			<p>&nbsp;</p>
			<p align="right"> 
				Next Step: 
				<a href="${pageContext.request.contextPath}/authoring/roleplay/enterAudience/${sessionScope.sessionInfoBean.roleplayId}">Enter Your Audience </a>
			</p>
			<p>&nbsp;</p>
			<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>