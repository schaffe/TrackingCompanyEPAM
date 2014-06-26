<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="m" uri="/WEB-INF/tlds/mytag_library" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title>Login page</title>
    </head>
    <body>
        welcome, 
        <%
            String name = (String) request.getAttribute("login");
            if (name != null) {
                out.print(name);
            }
        %>
    </body>


</html>


