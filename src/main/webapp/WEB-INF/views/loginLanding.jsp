<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.seachangesimulations.platform.service.*, java.util.*"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h2>Welcome <security:authentication property="principal.fullName" /></h2>
		<table width="100%" border="1" cellspacing="0" cellpadding="2">

			<tr>
				<security:authorize url="/admin/**">
					<td><a href="${pageContext.request.contextPath}/admin/index" >
							<input type="image" name="adminImage" id="adminImage" border ="0"
								src="${pageContext.request.contextPath}/resources/images/admins.png" /></a>
							</td>
				</security:authorize>
				<security:authorize url="/developing/**">
					<td><a href="${pageContext.request.contextPath}/developing/index" >
							<input type="image" name="devImage" id="devImage" border ="0"
								src="${pageContext.request.contextPath}/resources/images/dev.png" /></a>
							</td>
				</security:authorize>
				<security:authorize url="/authoring/**">
					<td><a href="${pageContext.request.contextPath}/authoring/index" ><input
								type="image" name="authoringImage" id="authoringImage" border ="0"
								src="${pageContext.request.contextPath}/resources/images/authors.png" /></a>
						</td>
				</security:authorize>
				<security:authorize url="/facilitating/**">
					<td><a href="${pageContext.request.contextPath}/facilitating/index" >
							<input type="image" name="facilitateImage" id="facilitateImage" border ="0"
								src="${pageContext.request.contextPath}/resources/images/facilitate.png" /></a></td>
				</security:authorize>
				<security:authorize url="/playing/**">
					<td><a href="${pageContext.request.contextPath}/playing/index" >
							<input type="image" name="playImage" id="playImage" border ="0"
								src="${pageContext.request.contextPath}/resources/images/players.png" /></a></td>
				</security:authorize>
			</tr>
			<tr>
				<security:authorize url="/admin/**">
					<td>admin words</td>
				</security:authorize>
				<security:authorize url="/developing/**">
					<td>developer words</td>
				</security:authorize>
				<security:authorize url="/authoring/**">
					<td>author words</td>
				</security:authorize>
				<security:authorize url="/facilitating/**">
					<td>facilitator words</td>
				</security:authorize>
				<security:authorize url="/playing/**">
					<td>player words</td>
				</security:authorize>
			</tr>
		</table>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
</body>
</html>