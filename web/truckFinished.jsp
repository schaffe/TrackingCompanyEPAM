<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />

<c:set var='param_application' value="<%=Constants.RequestParameters.APPLICATION%>" />
<c:set var="profile" value="${sessionScope[param_profile]}" />
<c:set var="application" value="${param.application}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="epam.tf.title" /></title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/headerButtons.jspf" %>

        <form action="<%=Constants.ACTION%>" method="POST">
            <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.TRUCK_FINISHED%>">
            <input type="hidden" name="<%=Constants.RequestParameters.APPLICATION%>" value="${application}">

            <p>
                <input type="checkbox" name="accomplished" value="true" checked required><fmt:message key="epam.tf.track_accomplished" /><br>
            </p>
            <p>
                <fmt:message key="epam.tf.car_state" /><br>
                <input type="radio" name="<%=Constants.RequestParameters.CAR%>" value="true" checked="true"><fmt:message key="epam.tf.car_ok" /><br>
                <input type="radio" name="<%=Constants.RequestParameters.CAR%>" value="false"><fmt:message key="epam.tf.car_broken" /><br>
            </p>
            <fmt:message key="epam.button.send" var="sendValue" />
            <input type="submit" value="${sendValue}" />
            <fmt:message key="epam.button.reset" var="clearValue" />
            <input type="reset" value="${clearValue}">
        </form>
    </body>
</html>
