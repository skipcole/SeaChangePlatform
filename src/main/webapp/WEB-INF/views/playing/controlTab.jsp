<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="scp" uri="/WEB-INF/SeaChangePlatform.tld"%>
<!DOCTYPE HTML >
<html><!-- InstanceBegin template="/Templates/SeaChangePlatform.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>

    
<!-- InstanceBeginEditable name="doctitle" -->
<title>Control</title>
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



<link href="/SeaChangePlatform/Templates/AuthorTemplate.css" rel="stylesheet" type="text/css">
<link href="/SeaChangePlatform/Templates/FacilitatorTemplate.css" rel="stylesheet" type="text/css">
<!-- InstanceEndEditable -->
</head>
<body>
	<!-- InstanceBeginEditable name="BodyRegion" -->
<div id="wrapper">
 
	<div id="header">
    	<div class="welcome_msg">You are in Control</div>    
    </div>

</div>
<p>from the tag lib: <scp:actorname /></p>

	<sf:form name="formChangePhase"  modelAttribute="phase"  method="post"
		action="${pageContext.request.contextPath}/playing/changePhase"
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

<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>