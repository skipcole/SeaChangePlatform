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
<title>Plugin Developers Section - Sea Change Simulations, LLC</title>
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
<script>
	$(document).ready(function() {
		$("#pluginSelectionId").change(function() {
			var str = $("#pluginSelectionId").val();

			$("#pluginAction option").remove();
			$("select#pluginAction").append(
			$("<option>").val("1").html("Define"));

			if (str != 0) {
				$("select#pluginAction").append($("<option>").val("2").html("Upload Files"));
				$("select#pluginAction").append($("<option>").val("3").html("Add Objects"));
				$("select#pluginAction").append($("<option>").val("4").html("Publish"));
			}
		});

	});
</script>
<!-- InstanceEndEditable -->
</head>
<body>
	<!-- InstanceBeginEditable name="BodyRegion" -->
	<h2>Plugin Development Overview</h2>
	<p>
		To create a plugin you will first write the pages that will be shown
		to the player according to <a href="plugin/pluginAPI.jsp">our API</a>.
		You will then create the definitional information using this web
		application. Finally, you will use this web application to 'publish'
		your plugin so that it may be used on this platform.
	</p>
	<p>&nbsp;</p>
	<h2>Plugin Development Steps</h2>
	<blockquote>
		<form name="form1" method="post"
			action="/SeaChangePlatform/developing/choose">
			<ol>
				<li>First Select the Plugin you want to work on: <sf:select
						name="pluginSelectionId" id="pluginSelectionId"
						path="pluginSelectionId">
						<option value="0">A New Plugin</option>
						<c:forEach var="plugin" items="${plugins}">
							<option value="${plugin.id}" >
								<c:out value="${plugin.pluginName}" />
							</option>
						</c:forEach>
					</sf:select>
				</li>
				<li>Then Select what you want to do with it: <sf:select
						name="pluginAction" id="pluginAction" path="pluginAction">
						<option value="1">Define</option>
					</sf:select>
				</li>
				<li>Then Go! <input type="submit" name="goButton" id="goButton"
					value="Submit">
				</li>
			</ol>
		</form>
		<p>
			You may also be interested in <a href="unpackage.jsp">unpackaging</a>
			an importing an existing plugin to use it as a starting point.
		</p>
	</blockquote>

	<p>&nbsp;</p>


	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>