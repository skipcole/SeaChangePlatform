<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	// I was going to add something so that if the user reached this page other 
	// than coming through the Spring logout apparatus, they would still get logged out.
	//request.getSession().invalidate();

%>
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sea Change Platform Logout Page</title>
</head>
<body>
	<h2>You have logged out.</h2>
	<p>&nbsp;</p>
	<a href="<%= request.getContextPath() %>/login">Click here </a>to login again.
	
	<p>There should be no roleplay name here: <c:out value="${sessionScope.sessionInfoBean.roleplayName}" /></p>

</body>
</html>