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
<title>Authoring Section</title>
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

<p>Working on Roleplay <c:out value="${sessionScope.sessionInfoBean.roleplayName}" /></p>
<h2>Authoring Steps</h2>
<ol>
  <li><a href="${pageContext.request.contextPath}/authoring/roleplay/create/${sessionScope.sessionInfoBean.roleplayId}" id="authorCreateRoleplay" >Edit Roleplay Basic Data </a>(?)</li>
  <li><a href="${pageContext.request.contextPath}/authoring/roleplay/enterObjectives/${sessionScope.sessionInfoBean.roleplayId}" id="authorEnterRoleplayObjectives">Enter Learning Objectives </a>(?)</li>
  <li><a href="${pageContext.request.contextPath}/authoring/roleplay/enterAudience/${sessionScope.sessionInfoBean.roleplayId}"id="authorEnterRoleplayAudience">Enter Your Audience </a>(?)</li>
  <li><a href="${pageContext.request.contextPath}/authoring/enterRoleplayPlannedPlayIdeas" id="authorEnterPlannedPlayIdeas">Enter Planned Play Ideas </a>(?)</li>
  <li><a href="${pageContext.request.contextPath}/authoring/roleplay/${sessionScope.sessionInfoBean.roleplayId}/actor/create/${sessionScope.sessionInfoBean.actorId}" id="authorCreateActors">Create/Edit Actors </a>(?)</li>
  <li><a href="${pageContext.request.contextPath}/authoring/actor/manage"  id="authorManageActors">Manage Actors </a>(?)</li>
  <li><a href="${pageContext.request.contextPath}/authoring/roleplay/${sessionScope.sessionInfoBean.roleplayId}/phase/create/${sessionScope.sessionInfoBean.phaseId}"  id="authorCreatePhases">Create/Edit Phases </a>(?)</li>
  <li><a href="${pageContext.request.contextPath}/authoring/roleplay/${sessionScope.sessionInfoBean.roleplayId}/pluginPlacement/" id="authorPlacePlugins">Place Plugins </a>(?)</li>
  <li><a href="<%= request.getContextPath() %>/authoring/review"  id="authorReviewRoleplay">Review Roleplay </a>(?)</li>
  <li><a href="${pageContext.request.contextPath}/authoring/roleplay/publish/${sessionScope.sessionInfoBean.roleplayId}" id="authorPublishRoleplay">Publish Roleplay</a></li>
</ol>
<p>If you would like to work on a different roleplay:</p>
<ul>
  <li><a href="${pageContext.request.contextPath}/authoring/roleplay/create/0">Create a new Roleplay </a></li>
  <li><a href="${pageContext.request.contextPath}/authoring/selectRoleplay">Select an existing Roleplay to work on</a></li>
</ul>
<p>&nbsp;</p>
    <!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>