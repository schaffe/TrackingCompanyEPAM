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
<c:set var='param_action_VIEW' value="<%=Constants.RequestParameters.ApplicationParam.VIEW.name()%>" />
<c:set var='param_action_EDIT' value="<%=Constants.RequestParameters.ApplicationParam.EDIT.name()%>" />
<c:set var='param_action_CREATE' value="<%=Constants.RequestParameters.ApplicationParam.CREATE.name()%>" />
<c:set var='param_profile' value="<%=Constants.SessionParameters.PROFILE_ID%>" />
<c:set var='param_profile_DISPATCHER' value="<%=Constants.Profiles.DISPATCHER%>" />

<c:set var="profile" value="${sessionScope[param_profile]}" />
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
            <c:choose>
                <c:when test="${profile == param_profile_DISPATCHER}">
                    <form action="<%=Constants.ACTION%>">
                        <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.VIEW_APPLICATION%>">
                        <input type="hidden" name="<%=Constants.RequestParameters.ID%>" value="${application.applicationId}">
                        <input type="hidden" name="${param_action}" value="${param_action_EDIT}">
                        <fmt:message key="epam.text.edit" var="buttonEdit" />
                        <input type="submit" value="${buttonEdit}">
                    </form>
                    <form action="<%=Constants.ACTION%>" method="POST">
                        <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.CHOOSE_DRIVER%>">
                        <input type="hidden" name="<%=Constants.RequestParameters.ID%>" value="${application.applicationId}">
                        <fmt:message key="epam.a.choose_driver" var="buttonDriver" />
                        <input type="submit" value="${buttonDriver}">
                    </form>
                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <form action="<%=Constants.ACTION%>" method="POST" >
        <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.SHOW_ALL_APPLICATIONS%>">
        <fmt:message key="epam.text.back" var="buttonBack" />
        <input type="submit" value="${buttonBack}">
    </form>

</body>
</html>

