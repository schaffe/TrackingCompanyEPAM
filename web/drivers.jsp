<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="epam.d.title"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/headerButtons.jspf" %>


        <table border="1">
            <caption><fmt:message key="epam.d.title"/></caption>
            <tr>
                <th><fmt:message key="epam.d.name"/></th>
                <th><fmt:message key="epam.d.car_model"/></th>
                <th><fmt:message key="epam.d.places"/></th>
                <th></th>
                <th></th>
            </tr>

            <c:set var='application_attr' value="<%=Constants.RequestParameters.APPLICATION%>" />
            <c:set var='application' value="${requestScope[application_attr]}" />
            <c:set var='list' value="<%=Constants.RequestParameters.LIST%>" />
            <c:forEach var="driver" items="${requestScope[list]}">
                <tr>
                    <td><c:out value="${driver.userAccount.fullName}"/></td>
                    <td><c:out value="${driver.car.model}"/></td>
                    <td><c:out value="${driver.car.placesNumber}"/></td>
                    <td><a href="${pageContext.request.contextPath}/<%=Constants.ACTION%>?<%=Constants.RequestParameters.COMMAND_STR%>=<%=Constants.Commands.SHOW_CARS%>&<%=Constants.RequestParameters.DRIVER%>=${driver.driverId}&<%=Constants.RequestParameters.APPLICATION%>=${application.applicationId}">
                            <fmt:message key="epam.text.edit"/></a></td>
                    <td><a href="${pageContext.request.contextPath}/<%=Constants.ACTION%>?<%=Constants.RequestParameters.COMMAND_STR%>=<%=Constants.Commands.SET_DRIVER%>&<%=Constants.RequestParameters.APPLICATION%>=${application.applicationId}&<%=Constants.RequestParameters.DRIVER%>=${driver.driverId}">
                            <fmt:message key="epam.text.set"/></a></td>
                </tr>
            </c:forEach>
        </table>
        <!-- Back button -->
        <form action="<%=Constants.ACTION%>" method="POST" >
            <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.VIEW_APPLICATION%>">
            <input type="hidden" name="<%=Constants.RequestParameters.ID%>" value="${application.applicationId}">
            <fmt:message key="epam.text.back" var="buttonBack" />
            <input type="submit" value="${buttonBack}">
        </form>
        <!-- /Back button -->

    </body>
</html>
