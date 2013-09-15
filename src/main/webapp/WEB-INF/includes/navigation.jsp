<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.seachangesimulations.platform.service.*, java.util.*"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<table width="100%">
	<tr>
		<td align="left"><img
			src="/SeaChangePlatform/resources/images/sea_change_small_logo.png"></td>
		<td align="left"><div align="left">
				<h1>
					<%
						String location = request.getRequestURI();
						if (location.startsWith("/SeaChangePlatform/login")) {
					%>
					<%
						} else if (location.startsWith("/SeaChangePlatform/admin")) {
					%>Administrator<%
						} else if (location.startsWith("/SeaChangePlatform/authoring")) {
					%>Author<%
						} else if (location.startsWith("/SeaChangePlatform/facilitating")) {
					%>Facilitator<%
						} else {
					%>
					<%
						}
					%>
				</h1>

			</div></td>
		<td align="right"><div align="right">
				<security:authorize access="isAnonymous()">
					<a href="/SeaChangePlatform/login">Login</a>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<span class="nowrap"> 
					
					<security:authorize access="hasRole('ROLE_ADMIN')" >
							<a href="${pageContext.request.contextPath}/admin/index"
								id="adminHomeLink">Admin</a> ||
</security:authorize> 

					<security:authorize access="hasRole('ROLE_DEV')" >
							<a href="${pageContext.request.contextPath}/developing/index"
								id="devHomeLink">Developer</a> ||
</security:authorize> 

					<security:authorize access="hasRole('ROLE_AUTHOR')" >
							<a href="${pageContext.request.contextPath}/authoring/index"
								id="authorHomeLink">Author</a> ||
</security:authorize> 

					<security:authorize access="hasRole('ROLE_FACILITATOR')"   >
							<a href="${pageContext.request.contextPath}/facilitating/index"
								id="facilitatorHomeLink">Facilitator</a> ||
</security:authorize> 

					<security:authorize  access="hasRole('ROLE_PLAYER')"  >
							<a href="${pageContext.request.contextPath}/playing/index"
								id="playerHomeLink">Player</a> ||
						</security:authorize> 
						
						<a href="/SeaChangePlatform/profile" id="linkToProfile"><security:authentication
								property="principal.fullName" /></a> || 
								
						<a
						href="/SeaChangePlatform/static/j_spring_security_logout"
						id="linkToLogout">Log out </a>
					</span>
				</security:authorize>
			</div></td>
	</tr>
</table>