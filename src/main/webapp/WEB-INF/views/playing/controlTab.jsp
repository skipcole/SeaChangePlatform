<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="scp" uri="/WEB-INF/SeaChangePlatform.tld"%>
<!DOCTYPE HTML >
<html>
<!-- InstanceBegin template="/Templates/SeaChangePlatform.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>


<!-- InstanceBeginEditable name="doctitle" -->
<title>Control</title>
<!-- InstanceEndEditable -->

<link href="/SeaChangePlatform/resources/css/SeaChangePlatform.css"
	rel="stylesheet" type="text/css">
<link
	href="/SeaChangePlatform/resources/javascript/jquery-ui-1.10.3.custom/css/start/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" type="text/css">
<link href="/SeaChangePlatform/Templates/AuthorTemplate.css"
	rel="stylesheet" type="text/css">
<link href="/SeaChangePlatform/Templates/FacilitatorTemplate.css"
	rel="stylesheet" type="text/css">
<!-- InstanceEndEditable -->
</head>
<body>

<script src="/SeaChangePlatform/resources/javascript/jquery-1.9.1.js"></script>
<script
	src="/SeaChangePlatform/resources/javascript/seachangeplatform.js"></script>
<script
	src="/SeaChangePlatform/javascript/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="/SeaChangePlatform/resources/js/bootstrap.min.js"></script>

	<div id="wrapper">

		<div id="header">
			<div class="welcome_msg">You are in Control</div>
		</div>

	</div>
	<br />
	<p>
		Your Role:
		<scp:actorname />
	</p>

	<form id="formChangePhase" method="get" action="#">
		<p>Phase:${sessionScope.sessionInfoBean.phaseName}</p>
		<select id="selectPhaseList">
			<c:forEach var="phaseForThisRoleplay"
				items="${phasesForThisRoleplay}">
				<option value="${phaseForThisRoleplay.id}">
					<c:out value="${phaseForThisRoleplay.phaseName}" /></option>
			</c:forEach>

		</select> <input type="submit" name="selectPhaseButton" id="selectPhaseButton"
			value="Submit">
	</form>

	<script>
	$( document ).ready(function() {
		$("#formChangePhase")
				.submit(
						function() {

							var url = "${pageContext.request.contextPath}/playing/changePhase/" + $("#selectPhaseList").val();

							$.ajax({
								type : "GET",
								url : url,
								success: function()
						           {
						               alert('shot at url'); // show response from the php script.
						           }
							});
							
							alert(url);

							return false; // avoid to execute the actual submit of the form.
						});
	});
	</script>
</body>
</html>