<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seachange2 Platform Welcome Page</title>
</head>
<body>
<div>
   <h4><u><spring:message code="UserLogin.pageTitle" /></u></h4>
   <!-- MJS 2.18 - Much work done gtting redirect to work . 
    "select_actors.jsp"  
   Works get logout, post logout returns method not supported.  
   Added loginFailed to spring-security list, and showLoginFailedGet in 
   HomeController, and it worked!
   Added a post method and got it to work also. 
   Able to have loginPassed post work with HomeController method 
   without adding LoginPassed to security file.
   ------------- files in other directories. ----------------- 
   Was able to copy admin/users/create to views and got it to run, but had errors.
   Tried admin/users/create but put under SeaChangePlatform path.
   Ditto views/admin/users/create. Ditto src/main/webapp/WEB-INF/views.
       -------
   Based upon the below, tried admin/userCreate - this worked!
   Ultimately, value = "admin/userCreate" worked because it matches the file java Request Mapaping, NOT the path.
    The RequestMapping is in AdminController.java, a combo of the class RequestMapping and the function level mapping.
    at@RequestMapping(CMC.ADMIN_BASE)  for class. ADMIN_BASE = "/admin"; 
    	public class AdminController . . . 
   	at@RequestMapping(value = { "userCreate" }, method = RequestMethod.GET)
		public String showCreateUserPage(Model model) {
			model.addAttribute(new AdminCreatePersonFormBean());
			AdminController.loadLevelsIntoMap(model);
			return "admin/users/create.jsp";  // this is the location of the actual form. 
        ---------   
 
   Also tried more distinct "authoring/actor/createActor" and same with SCP at front.
   		These are found in AuthorControlleer.jave returned from 3 functions. 
   			@RequestMapping(value = { CMC.A_ROLEPLAY_ACTOR_CREATE }, method = RequestMethod.POST)
	public String createActorPost(
		if (bindingResult.hasErrors()) return "/authoring/actor/createActor.jsp";
	// public static final String A_ROLEPLAY_ACTOR_CREATE = "roleplay/{roleplayId}/actor/create/{actorId}"
   Also tried "/SeaChangePlatform/actor/create"
   
   -->
   Welcome to the SeaChange2 Roleplay Platform
   <spring:url var="authUrl" value="/static/j_spring_security_check" />  
   <form method="post" class="signin" action=${authUrl} >
   <!--    <spring:url var="authUrl" value="/static/j_spring_security_check" />  -->
   <!--    <spring:url var="authUrl" value="/admin/userCreate" />  -->
   <!-- Be sure the method is ppost (not get) or error" Authentication method not
   		supported: get"   -->
   
    <fieldset>
    <table cellspacing="0">
    <tr>
    <th><label for="username_or_email">Username or Email </label></th>
    <td><input id="username_or_email"  name="j_username" type="text" />  
      </td>
    </tr>
    <tr>
    <th><label for="password">Password</label></th>
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
               class="inline">Remember me</label></td>
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