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
<title>Modify Plugin Document</title>
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
<!-- InstanceEndEditable -->
</head>
<body>
	<!-- InstanceBeginEditable name="BodyRegion" -->
	<p>Add Object on Plugin: ${plugin.getPluginName()}</p>
	<p>On this page you modify the plugin document.</p>

	<p>&nbsp;</p>
	<sf:form name="form1" method="POST"
		action="${pageContext.request.contextPath}/developing/plugin/${plugin.getId()}/modifyPluginDocument/${pod.getId()}"
		modelAttribute="devModifyPluginDocumentFormBean">
		<table border="1">
			<tr>
				<td><label for="documentName">Document Name</label></td>
				<td><sf:input type="text" name="documentName" id="documentName"
						path="documentName" /></td>
			</tr>
<tr>
				<td>Document Text</td>
			<td><sf:textarea type="text" name="documentText" id="documentText"
						path="documentText" rows="5" cols="30" /></td>
			</tr>
			<tr>
				<td>Update</td>
			<td><input type="submit" name="addObject" id="addObject"
				value="Submit"></td>
			</tr>
		</table>
	</sf:form>
	<p>&nbsp;</p>
	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>