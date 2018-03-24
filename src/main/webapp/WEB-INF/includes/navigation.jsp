<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.seachangesimulations.platform.service.*, java.util.*"
%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<table width="100%">
	<tr>
		<td align="left"><img src="/SeaChangePlatform/resources/images/sea_change_small_logo.png"></td>
		<td align="left"><div align="left">
				<h1>
					<c:if test="${sessionInfoBean.platformZone == 'admin'}">
						<spring:message code="platform.zone.admin" />
					</c:if>
					<c:if test="${sessionInfoBean.platformZone == 'developer'}">
						<spring:message code="platform.zone.developer" />
					</c:if>
					<c:if test="${sessionInfoBean.platformZone == 'author'}">
						<spring:message code="platform.zone.author" />
					</c:if>
					<c:if test="${sessionInfoBean.platformZone == 'facilitator'}">
						<spring:message code="platform.zone.facilitator" />
					</c:if>
					<c:if test="${sessionInfoBean.platformZone == 'player'}">
						<spring:message code="platform.zone.player" />
					</c:if>
				</h1>

			</div></td>
		<td align="right"><div align="right">
				<security:authorize access="isAnonymous()">
					<a href="/SeaChangePlatform/login">LoginMJS</a>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<span class="nowrap"> <c:if test="${sessionInfoBean.platformZone == 'admin'}">
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<a href="${pageContext.request.contextPath}/admin/index" id="adminHomeLink">Admin</a> ||
</security:authorize>
						</c:if> <c:if test="${sessionInfoBean.platformZone == 'developer'}">
							<security:authorize access="hasRole('ROLE_DEV')">
								<a href="${pageContext.request.contextPath}/developing/index" id="devHomeLink">Developer</a> ||
</security:authorize>
						</c:if> <c:if test="${sessionInfoBean.platformZone == 'author'}">
							<security:authorize access="hasRole('ROLE_AUTHOR')">
								<a href="${pageContext.request.contextPath}/authoring/index" id="authorHomeLink">Author</a> ||
</security:authorize>
						</c:if> <c:if test="${sessionInfoBean.platformZone == 'facilitator'}">
							<security:authorize access="hasRole('ROLE_FACILITATOR')">
								<a href="${pageContext.request.contextPath}/facilitating/index" id="facilitatorHomeLink">Facilitator</a> ||
</security:authorize>
						</c:if> <c:if test="${sessionInfoBean.platformZone == 'player'}">
							<security:authorize access="hasRole('ROLE_PLAYER')">
								<a href="${pageContext.request.contextPath}/playing/index" id="playerHomeLink">Player</a> ||
						</security:authorize>
						</c:if> <a href="/SeaChangePlatform/profile" id="linkToProfile"> <security:authentication
								property="principal.fullName"
							/></a> || <a href="/SeaChangePlatform/static/j_spring_security_logout" id="linkToLogout">Log out </a>
					</span>
				</security:authorize>
			</div></td>
	</tr>
</table>