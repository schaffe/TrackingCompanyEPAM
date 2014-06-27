<%@page import="ua.kpi.project4.controller.SessionParameters;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="m" uri="/WEB-INF/tlds/mytag_library" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title>Login page</title>
    </head>
    <body>
        welcome, 
        <%
            String name = (String) request.getAttribute("name");
            if (name != null) {
                out.print(name);
                out.print(request.getSession().getAttribute(SessionParameters.USER_ID));
            }
        %>
        
        <!-- Logout button -->
        <form action="command">
            <c:set var="act" value="logout"/>
            <input type="hidden" name="action" value="${act}">
            <fmt:message key="epam.text.logout" var="buttonLogout" />
            <input type="submit" value="${buttonLogout}">
        </form>
    </body>


</html>


