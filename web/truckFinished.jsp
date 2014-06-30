<%-- 
    Document   : trackFinished
    Created on : Jun 25, 2014, 8:21:02 PM
    Author     : Artur Dzidzoiev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<fmt:setBundle basename="bundle" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="epam.tf.title" /></title>
    </head>
    <body>
                <%@include file="/WEB-INF/jspf/headerButtons.jspf" %>

        <form action="command">
            <input type="hidden" name="action" value="track_finished">
            <p>
                <input type="checkbox" name="accomplished" value="true" checked required><fmt:message key="epam.tf." /><br>
            </p>
            <p>
                <fmt:message key="epam.tf.car_state" /><br>
                <input type="radio" name="car" value="true" checked="true"><fmt:message key="epam.tf.car_ok" /><br>
                <input type="radio" name="car" value="false"><fmt:message key="epam.tf.car_broken" /><br>
            </p>
            <fmt:message key="epam.button.send" var="sendValue" />
            <input type="submit" value="${sendValue}" />
            <fmt:message key="epam.button.reset" var="clearValue" />
            <input type="reset" value="${clearValue}">
        </form>
    </body>
</html>
