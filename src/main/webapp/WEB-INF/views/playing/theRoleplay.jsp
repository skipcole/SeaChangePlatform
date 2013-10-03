<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html>
<!-- InstanceBegin template="/Templates/SeaChangePlatform.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<head>


<!-- InstanceBeginEditable name="doctitle" -->
<title>Playing</title>
<!-- InstanceEndEditable -->

<script src="/SeaChangePlatform/resources/javascript/jquery-1.9.1.js"></script>
<script src="/SeaChangePlatform/resources/javascript/seachangeplatform.js"></script>

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
	


<div id="wrapper">

<div class="tabbable">
		<ul class="nav nav-tabs">
			<c:forEach var="pluginPointer" items="${pluginPointers}">
				<li><a href="#pane_${pluginPointer.id}" id="tabpane_${pluginPointer.id}"  data-toggle="tab">
						<c:out value="${pluginPointer.pluginHeading}" />
				</a></li>
			</c:forEach>
		</ul>
		
		<div class="tab-content">

			<c:forEach var="pluginPointer" items="${pluginPointers}">
				<div class="tab-pane" id="pane_${pluginPointer.id}"  >
              		<p>Placeholder <c:out value="${pluginPointer.id}" /></p>
              		<iframe src="${pageContext.request.contextPath}/playing/showPlugin/${pluginPointer.id}">
              		</iframe>
            	</div>
			</c:forEach>
            
		</div> <!--  End of div tab content -->

</div> <!--  end of div tabbable -->
	
	
</div> <!--  end of div wrapper -->
	<!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd -->
</html>