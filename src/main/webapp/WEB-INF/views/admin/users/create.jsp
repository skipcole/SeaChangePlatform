<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create User</title>
</head>
<body>
	<p>Create a New User </p>
	<p>&nbsp;</p>
	<sf:form method="POST" modelAttribute="adminCreatePersonFormBean">
		<table width="100%" border="1" cellspacing="0" cellpadding="2">
			<tr>
				<td><label for="username">Username</label></td>
				<td><sf:input type="text" path="username" id="username" /></td>
			</tr>
			<tr>
				<td><label for="password">password</label></td>
				<td><sf:input type="password" path="password" id="password" /></td>
			</tr>

			<tr>
				<td><label for="firstName">First Name</label></td>
				<td><sf:input type="text" path="firstName" id="firstName" /></td>
			</tr>

			<tr>
				<td><label for="lastName">Last Name</label></td>
				<td><sf:input type="text" path="lastName" id="lastName" /></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>level: <sf:select path="initialAuthLevel"
						name="initialAuthLevel" id="initialAuthLevel">
						<option value="${ADMIN_LEVEL}">admin</option>
						<option value="${DEV_LEVEL}">dev</option>
						<option value="${AUTHOR_LEVEL}">author</option>
						<option value="${FACILITATOR_LEVEL}">facilitator</option>
						<option value="${PLAYER_LEVEL}">player</option>
					</sf:select></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" name="button" id="button"
					value="Submit"></td>
			</tr>
		</table>
	</sf:form>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
</body>
</html>