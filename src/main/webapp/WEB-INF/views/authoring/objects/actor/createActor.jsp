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
<title>Create Actor in a scenario - MJS authoring/actor/createActor.jsp </title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>
<body>
<!-- InstanceBeginEditable name="ObjectTypeName" -->
<p>Create Actor</p>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="CreateObjectForm" -->
<form name="createActorForm" method="post" action="/SeaChangePlatform/actor/create">
<table width="100%" border="1" cellspacing="0" cellpadding="2">
  <tr>
    <td>Actor Name
      <label for="username"></label></td>
    <td>
      
      <input type="text" name="actor.actorName" id="actor.actorName">
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