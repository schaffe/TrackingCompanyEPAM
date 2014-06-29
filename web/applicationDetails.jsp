<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />
<!DOCTYPE html>

<!-- DECLARATION -->
<c:set var='param_application' value="<%=Constants.RequestParameters.APPLICATION%>" />
<c:set var='param_action' value="<%=Constants.RequestParameters.APPLICATION_ACTION%>" />
<c:set var='param_action_VIEW' value="<%=Constants.RequestParameters.ApplicationParam.VIEW%>" />
<c:set var='param_action_EDIT' value="<%=Constants.RequestParameters.ApplicationParam.EDIT%>" />
<c:set var='param_action_CREATE' value="<%=Constants.RequestParameters.ApplicationParam.CREATE%>" />

<c:set var="application" value="${requestScope[param_application]}" />
<c:set var="action" value="${requestScope[param_action]}" />
<!-- /DECLARATION -->


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="epam.a.title"/></title>
    </head>
    <body>
    <%@include file="/WEB-INF/jspf/headerButtons.jspf" %>
    
    <c:choose>
        <c:when test="${action == param_action_VIEW}">
        </c:when>
        <c:otherwise>
            <h3><fmt:message key="epam.a.app_id"/> <c:out value="${application.applicationId}"/></h3>
            <form>
                <table border="0">
                    <tr><td><fmt:message key="epam.a.from"/></td><td><input type="text" value="${application.from}" readonly="true"/></td></tr>
                    <tr><td><fmt:message key="epam.a.where"/></td><td><input type="text" value="${application.destination}" readonly="true"/></td></tr>
                    <tr><td><fmt:message key="epam.a.arrival_time"/></td><td><input type="datetime" value="${application.arrivalTime}" readonly="true"/></td></tr>
                    <tr><td><fmt:message key="epam.a.status"/></td><td><input type="text" value="${application.status}" readonly="true"/></td></tr>
                </table>
            </form>
        </c:otherwise>
    </c:choose>


</body>
</html>

