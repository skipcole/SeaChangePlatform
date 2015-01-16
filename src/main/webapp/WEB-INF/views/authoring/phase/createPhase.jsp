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
	<p>Create Phase</p>
	test
	<p>&nbsp;</p>
	<p>Create Phase	</p>
	<sf:form method="POST" action="${pageContext.request.contextPath}/authoring/roleplay/${sessionScope.sessionInfoBean.roleplayId}/phase/create/${phase.id}"
		modelAttribute="authorCreatePhaseFormBean" >
		<table width="100%" border="1" cellspacing="0" cellpadding="2">
			<tr>
				<td>Phase Name</td>
				<td><input type="text" name="phaseName" id="phaseName" /></td>
			</tr>
						<tr>
				<td>Public Description</td>
				<td><input type="text" name="publicDescription" id="publicDescription" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				<!--  if phase id != 0, then do edit button -->
				<input type="submit" name="createButton" id="createButton" value="Create" />
				<!--  } -->
				<input type="submit" name="editButton" id="editButton" value="Edit" />
				<!--  }  -->
				</td>
			</tr>
		</table>
	</sf:form>
	<p>&nbsp;</p>
	<p>list phases here: </p>
	<c:forEach var="phase" items="${phasesForThisRoleplay}">
		Phase: <c:out value="${phase.phaseName}" /><br />
	</c:forEach>
	<p>&nbsp;</p>
<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>