<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<div>
		<h2>Install Platform</h2>

		<sf:form method="POST" modelAttribute="adminInstallationFormBean">
			<fieldset>
				<table cellspacing="0">
					<tr>
						<th>Installation Key</th>
						<td><sf:input path="installationKey" size="15"
								id="installation_key" /></td>
						<sf:errors path="installationKey" cssClass="error" />
					</tr>
					<tr>
						<th><label for="first_name">first name:</label></th>
						<td><sf:input path="firstName" size="15"
								id="first_name" /></td>
					</tr>
					<tr>
						<th>Organization</th>
						<td><sf:input path="organizationName" size="15"
								id="organization_name" /></td>
					</tr>
					<tr>
						<th><label for="user_screen_name">Email/Username:</label></th>
						<td><sf:input path="username" size="50" maxlength="50"
								id="user_screen_name" /> <small id="username_msg">Valid
								email only.</small><br /> <sf:errors path="username"
								cssClass="error" /></td>
					</tr>
					<tr>
						<th><label for="user_password">Password:</label></th>
						<td><sf:password path="password" size="30"
								showPassword="true" id="user_password" /> <small>6
								characters or more</small></td>
					</tr>
					<tr>
						<th></th>
						<td><input name="commit" type="submit"
							value="Create Admin Account." /></td>
					</tr>

				</table>
			</fieldset>
		</sf:form>
	</div>

</body>
</html>