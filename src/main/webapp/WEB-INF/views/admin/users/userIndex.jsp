<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sea Change Platform Organization Administration</title>
</head>
<body>
<p>Administer Users </p>
<p>&nbsp;</p>
<ol>
      <li><a href="<%= request.getContextPath() %>/admin/userCreate" id="linkToAdminCreateUser">Create User</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/usersDisplayTable" id="linkToAdminListUser">List All Users</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/usersSelect" id="linkToAdminListUser">Select Users</a></li>
</ol>
<p>&nbsp;</p>
</body>
</html>