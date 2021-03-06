<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />
<!DOCTYPE html>

<!-- DECLARATION -->
<c:set var='param_application' value="<%=Constants.RequestParameters.APPLICATION%>" />
<c:set var='param_profile' value="<%=Constants.SessionParameters.PROFILE_ID%>" />
<c:set var='param_profile_DISPATCHER' value="<%=Constants.Profiles.DISPATCHER%>" />
<c:set var='param_profile_DRIVER' value="<%=Constants.Profiles.DRIVER%>" />
<c:set var='param_status_PROCESSED' value="<%=Constants.ApplicationStatus.PROCESSED%>" />
<c:set var='param_status_TRUCKING' value="<%=Constants.ApplicationStatus.TRUCKING%>" />
<c:set var='param_status_PENDING' value="<%=Constants.ApplicationStatus.PENDING%>" />


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

        <h3><fmt:message key="epam.a.app_id"/> <c:out value="${application.applicationId}"/></h3>
        <form>
            <table border="0">
                <tr><td><fmt:message key="epam.a.status"/></td>
                    <td><b><c:out value="${application.status}" /></b></td></tr>
                <tr><td><fmt:message key="epam.a.from"/></td>
                    <td><c:out value="${application.from}" /></td></tr>
                <tr><td><fmt:message key="epam.a.where"/></td>
                    <td><c:out value="${application.destination}" /></td></tr>
                <tr><td><fmt:message key="epam.a.arrival_time"/></td>
                    <td><fmt:formatDate  value="${application.arrivalTime}" type="BOTH"/></td></tr>
                <tr><td><fmt:message key="epam.a.places"/></td>
                    <td><c:out  value="${application.passengersNum}" /></td></tr>
                <tr><td><fmt:message key="epam.a.driver"/></td>
                    <td><c:out  value="${application.driver.userAccount.fullName}" /></td></tr>
                <tr><td><fmt:message key="epam.a.date_create"/></td>
                    <td><fmt:formatDate value="${application.dateCreate}" type="BOTH"/></td></tr>
            </table>
        </form>
        <c:if test="${profile == param_profile_DISPATCHER and (application.status == param_status_PENDING or application.status == param_status_PROCESSED)}">
            <form action="<%=Constants.ACTION%>" method="POST">
                <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.SHOW_DRIVERS%>">
                <input type="hidden" name="<%=Constants.RequestParameters.APPLICATION%>" value="${application.applicationId}">
                <fmt:message key="epam.a.choose_driver" var="buttonDriver" />
                <input type="submit" value="${buttonDriver}">
            </form>
        </c:if>
        <c:if test="${profile == param_profile_DRIVER}">
            <c:choose>
                <c:when test="${application.status == param_status_PROCESSED}">
                    <form action="<%=Constants.ACTION%>" method="POST">
                        <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.START_TRUCKING%>">
                        <input type="hidden" name="<%=Constants.RequestParameters.APPLICATION%>" value="${application.applicationId}">
                        <input type="hidden" name="<%=Constants.RequestParameters.DRIVER%>" value="${application.driver.driverId}">

                        <fmt:message key="epam.a.start_trucking" var="buttonDriver" />
                        <input type="submit" value="${buttonDriver}">
                    </form>
                </c:when>
                <c:when test="${application.status == param_status_TRUCKING}">
                    <form action="<%=Constants.Pages.TRUCK_FINISHED%>">
                        <input type="hidden" name="<%=Constants.RequestParameters.APPLICATION%>" value="${application.applicationId}">
                        <fmt:message key="epam.a.finish_trucking" var="buttonDriver" />
                        <input type="submit" value="${buttonDriver}">
                    </form>
                </c:when>
            </c:choose>
        </c:if>

        <!-- Back button -->
        <form action="<%=Constants.ACTION%>" method="POST" >
            <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.SHOW_ALL_APPLICATIONS%>">
            <fmt:message key="epam.text.back" var="buttonBack" />
            <input type="submit" value="${buttonBack}">
        </form>
        <!-- /Back button -->


    </body>
</html>

