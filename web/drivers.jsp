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

            <c:set var='list' value="<%=Constants.RequestParameters.LIST%>" />
            <c:forEach var="driver" items="${requestScope[list]}">
                <tr>
                    <td><c:out value="${driver.userAccount.fullName}"/></td>
                    <td><c:out value="${driver.car.model}"/></td>
                    <td><c:out value="${driver.car.placesNumber}"/></td>
                    <td>Edit</td>
                    <td>Select</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
