<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE HTML >
<html><!-- InstanceBegin template="/Templates/createObject.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- InstanceBeginEditable name="doctitle" -->
<title>Create Organization</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>
<body>
<!-- InstanceBeginEditable name="ObjectTypeName" -->
<p>Create Organization</p>
<!-- InstanceEndEditable -->
<p><s:fielderror /></p>
<!-- InstanceBeginEditable name="CreateObjectForm" -->
<form name="createOrganizationForm" method="post" action="/SeaChangePlatform/Organization">
<table width="100%" border="1" cellspacing="0" cellpadding="2">
  <tr>
    <td>Organization Name
      <label for="username"></label></td>
    <td>
      
      <input type="text" name="organization.name" id="username">
      </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="submit" name="button" id="button" value="Submit"></td>
  </tr>
</table>
</form>
<p>&nbsp;</p>
<p>&nbsp;</p>
<!-- InstanceEndEditable -->
<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
<!-- InstanceEnd --></html>