<!DOCTYPE HTML >
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Sea Change Online Roleplay Platform | <decorator:title
		default="" /></title>

<link href="/SeaChangePlatform/resources/css/SeaChangePlatform.css"
	rel="stylesheet" type="text/css">
	
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
    
	<decorator:head />
</head>

<body>
	<table class="mainTable" id="page-container" cellpadding="5"
		cellspacing="0" border="0" align="center" width="100%" height="100%">
		<tr>
			<td id="nav-container"><%@ include
					file="/WEB-INF/includes/navigation.jsp"%></td>
		</tr>
		<tr>
			<td id="content-container"><decorator:body /></td>
		</tr>
		<tr>
			<td id="page-footer"><%@ include
					file="/WEB-INF/includes/footer.jsp"%></td>
		</tr>
	</table>
</body>
</html>