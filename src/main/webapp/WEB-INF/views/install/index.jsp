<%@ page contentType="text/html; charset=utf-8" language="java" 
import="java.sql.*,com.seachangesimulations.platform.controllers.*"
 errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Create First Person</title>
</head>

<body>
If the platform has not been installed yet, you can <a href="<%= request.getContextPath() %><%= CMC.getURI("I_CMC_INSTALLFORM_POST") %>" id="install_link"> install it here</a>.


    
</body>
</html>