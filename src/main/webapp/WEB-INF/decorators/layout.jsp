<!DOCTYPE HTML >
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Sea Change Online Roleplay Platform | <decorator:title
		default="" /></title>

<link href="/SeaChangePlatform/resources/css/SeaChangePlatform.css"
	rel="stylesheet" type="text/css">
	<decorator:head />
</head>

<body>
	<table class="mainTable" id="page-container" cellpadding="5"
		cellspacing="0" border="0" align="center" width="100%" height="100%">
		<tr>
			<td id="nav-container"><%@ include
					file="/WEB-INF/includes/navigation.jsp"%></td>
		</tr>
		<tr>
			<td id="content-container"><decorator:body /></td>
		</tr>
		<tr>
			<td id="page-footer"><%@ include
					file="/WEB-INF/includes/footer.jsp"%></td>
		</tr>
	</table>
</body>
</html>