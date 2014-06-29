<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title><fmt:message key="epam.welcome.title"/></title>
    </head>
    <body>
        <c:set var='fullname_param' value="<%=Constants.RequestParameters.FULLNAME%>" />
        <fmt:message key="epam.welcome.welcome"/> <c:out value="${requestScope[fullname_param]}"/><br>
        <a href="${pageContext.request.contextPath}/<%=Constants.ACTION%>?<%=Constants.RequestParameters.COMMAND_STR%>=<%=Constants.Commands.SHOW_ALL_APPLICATIONS%>">Show all applications</a>

        <!-- Logout button -->
        <form action="<%=Constants.ACTION%>" method="POST" >
            <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.LOGOUT%>">
            <fmt:message key="epam.text.logout" var="buttonLogout" />
            <input type="submit" value="${buttonLogout}">
        </form>
        <!------------------->
    </body>


</html>


