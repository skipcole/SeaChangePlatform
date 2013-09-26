<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html><!-- InstanceBegin template="/Templates/SeaChangePlatform.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="/SeaChangePlatform/resources/bootstrap.min.css" rel="stylesheet" media="screen">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="/SeaChangePlatform/resources/js/html5shiv.js"></script>
      <script src="/SeaChangePlatform/resources/js/respond.min.js"></script>
    <![endif]-->
    <!-- /Bootstrap -->
    
<!-- InstanceBeginEditable name="doctitle" -->
<title>My Profile Page</title>
<!-- InstanceEndEditable -->

<link href="/SeaChangePlatform/resources/css/SeaChangePlatform.css"
	rel="stylesheet" type="text/css">
<link href="/SeaChangePlatform/javascript/jquery-ui-1.10.3.custom/css/start/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" type="text/css">

<script src="/SeaChangePlatform/resources/javascript/jquery-1.9.1.js"></script>
<script
	src="/SeaChangePlatform/resources/javascript/seachangeplatform.js"></script>
<script
	src="/SeaChangePlatform/javascript/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
    
<!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- script src="//code.jquery.com/jquery.js"></script  -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/SeaChangePlatform/resources/js/bootstrap.min.js"></script>
<!-- Bootstrap -->

<!-- InstanceBeginEditable name="head" -->

<link href="/SeaChangePlatform/Templates/AdminTemplate.css" rel="stylesheet" type="text/css">
<!-- InstanceEndEditable -->
</head>
<body>
	<!-- InstanceBeginEditable name="BodyRegion" -->
<security:authorize access="isAuthenticated()">
				
<table><tr><td><h2>Welcome <security:authentication property="principal.fullName" /></h2></td>
<td></td></tr></table>

<p>&nbsp;</p>

</security:authorize>
<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>