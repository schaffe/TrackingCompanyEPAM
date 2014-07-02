<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />
<!DOCTYPE html>

<!-- DECLARATION -->
<c:set var='param_from' value="<%=Constants.RequestParameters.FROM%>" />
<c:set var='param_to' value="<%=Constants.RequestParameters.DESTINATION%>" />
<c:set var='param_time' value="<%=Constants.RequestParameters.TIME%>" />
<c:set var='param_places' value="<%=Constants.RequestParameters.PLACES%>" />
<!-- /DECLARATION -->


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="epam.a.create"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/headerButtons.jspf" %>
        <h3><fmt:message key="epam.a.create"/></h3>
        <form action="<%=Constants.ACTION%>" method="POST">
            <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.CREATE_APPLICATION%>">
            <table border="0">
                <tr>
                    <td><fmt:message key="epam.a.from" /></td>
                    <td><input type="text" name="${param_from}" required></td>
                </tr>
                <tr>
                    <td><fmt:message key="epam.a.where" /></td>
                    <td><input type="text" name="${param_to}" required></td>
                </tr>
                <tr>
                    <td><fmt:message key="epam.a.arrival_time" /></td>
                    <td><input type="date" name="${param_time}" required></td>
                </tr>
                <tr>
                    <td><fmt:message key="epam.a.places_num" /></td>
                    <td><input type="number" name="${param_places}" min="1" required></td>
                </tr>
            </table>

            <fmt:message key="epam.button.send" var="sendValue" />
            <input type="submit" value="${sendValue}" />
            <fmt:message key="epam.button.reset" var="clearValue" />
            <input type="reset" value="${clearValue}">
        </form>
    </body>
</html>

