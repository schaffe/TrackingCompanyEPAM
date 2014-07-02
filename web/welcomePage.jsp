<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />
<c:set var='param_profile' value="<%=Constants.SessionParameters.PROFILE_ID%>" />
<c:set var='param_profile_DISPATCHER' value="<%=Constants.Profiles.DISPATCHER%>" />
<c:set var='param_profile_DRIVER' value="<%=Constants.Profiles.DRIVER%>" />
<c:set var='param_profile_CUSTOMER' value="<%=Constants.Profiles.CUSTOMER%>" />

<c:set var="profile" value="${sessionScope[param_profile]}" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title><fmt:message key="epam.welcome.title"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/headerButtons.jspf" %>

        <c:set var='fullname_param' value="<%=Constants.RequestParameters.FULLNAME%>" />
        <c:set var='profile_param' value="<%=Constants.SessionParameters.PROFILE_ID%>" />
        <fmt:message key="epam.welcome.welcome"/> <c:out value="${sessionScope[fullname_param]}"/><br>
        <fmt:message key="epam.welcome.logged_as"/> <c:out value="${sessionScope[profile_param]}"/><br>
        <a href="${pageContext.request.contextPath}/<%=Constants.ACTION%>?<%=Constants.RequestParameters.COMMAND_STR%>=<%=Constants.Commands.SHOW_ALL_APPLICATIONS%>"><fmt:message key="epam.welcome.applications"/></a><br>
        <c:if test="${profile == param_profile_DISPATCHER}">
            <a href="${pageContext.request.contextPath}/<%=Constants.ACTION%>?<%=Constants.RequestParameters.COMMAND_STR%>=<%=Constants.Commands.SHOW_DRIVERS%>"><fmt:message key="epam.welcome.drivers"/></a>
        </c:if>
        <c:if test="${profile == param_profile_CUSTOMER}">
            <a href="${pageContext.request.contextPath}/<%=Constants.Pages.CREATE_APPLICATION%>"><fmt:message key="epam.a.create"/></a>
        </c:if>
    </body>


</html>


