<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Admininstration views/admin/adminIndex.jsp</h1>
	<h2>Core Activities</h2>
<ol>
      <li><a href="<%= request.getContextPath() %>/admin/userAdmin" id="linkToUserAdmin">User Administration</a></li>
</ol>
<h2>Occasional Activities</h2>
<ol>
  <li><a href="<%= request.getContextPath() %>/admin/orgAdmin" id="linkToOrgAdmin">Organization Administration</a></li>
</ol>
<h2>Rare Activities</h2>
	<p><br />
</p>
	<table width="100%" border="1" cellspacing="2" cellpadding="1">
		<tr valign="top">
			<td><a href="simulation_admin_backuprelated.jsp">Backup/Restore
					Tasks</a></td>
			<td>Allows you to archive and restore database components
				(users, simulation sections, running simulations. simulations, etc.)
				for archival or transport purposes.</td>
		</tr>
		<tr valign="top">
			<td><a href="../simulation_contest/contest_administration.jsp">Contest
					Administration</a></td>
			<td>Allows you to set up contests and administer them.</td>
		</tr>
		<tr valign="top">
			<td><a href="simulation_maintenancerelated.jsp">Database
					Administration</a></td>
			<td>Allows one to see all of the installed databases, and to
				edit or install a new database. Also here one can send test the
				email system and set the next planned down time.</td>
		</tr>
		<tr valign="top">
			<td>Email Administration</td>
			<td>&nbsp;</td>
		</tr>
		<tr valign="top">
			<td><a href="organization_administration.jsp">Organization
					Administration</a></td>
			<td>Allows you to add organizations (and their logos) on to this
				platform.</td>
		</tr>
		<tr valign="top">
			<td><a href="class_administration.jsp">Class Administration</a></td>
			<td>Completely experimental.</td>
		</tr>
		<tr valign="top">
			<td><a href="software_development_section.jsp">Software
					Development Tasks</a></td>
			<td>Section useful to developers only.</td>
		</tr>

		<tr valign="top">
			<td width="24%"><a href="simulation_admin_userrelated.jsp">User
					Tasks</a></td>
			<td width="72%">Allows you to add administrative users and see
				last login times.</td>
		</tr>
	</table>
</body>
</html>