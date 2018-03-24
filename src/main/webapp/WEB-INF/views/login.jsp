<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- MJS 2.18 - This page reached upon login failure from welcome page.  
     Edited to show file info in names.
     MJS 3.18 - Took out file info in names.  -->
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seachange Platform Login Page</title>
</head>
<body>
<div>
   <h2><spring:message code="UserLogin.pageTitle" /></h2>
   
   <spring:url var="authUrl" value="/static/j_spring_security_check" />
   <!--                      value="/static/j_spring_security_check" /> -->
   <!--                      value="select_actors.jsp" /> -->
   <form method="post" class="signin" action="${authUrl}">
   
   
    <fieldset>
    <table cellspacing="0">
    <tr>
    <th><label for="username_or_email">Username or Email </label></th>
    <td><input id="username_or_email" 
               name="j_username" 
               type="text" />  
      </td>
    </tr>
    <tr>
    <th><label for="password">Password </label></th>
      <td><input id="password" 
                 name="j_password" 
                 type="password" /> 
          <small><a href="/account/resend_password">Forgot?</a></small>
      </td>
    </tr>
    <tr>
    <th></th>
    <td><input id="remember_me" 
        name="_spring_security_remember_me" 
        type="checkbox"/> 
        <label for="remember_me" 
               class="inline">Remember me </label></td>
    </tr>
    <tr>
    <th></th>
    <td><input name="login" id="login" type="submit" value="Sign In" /></td>
    </tr>
    </table>
    </fieldset>
   </form>
   
   <script type="text/javascript">
    document.getElementById('username_or_email').focus();
   </script>
</div>
</body>
</html>