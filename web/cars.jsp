<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />
<c:set var='application_attr' value="<%=Constants.RequestParameters.APPLICATION%>" />
<c:set var='application' value="${requestScope[application_attr]}" />
<c:set var='list' value="<%=Constants.RequestParameters.LIST%>" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="epam.c.title"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/headerButtons.jspf" %>


        <table border="1">
            <caption><fmt:message key="epam.c.title"/></caption>
            <tr>
                <th><fmt:message key="epam.c.model"/></th>
                <th><fmt:message key="epam.d.places"/></th>
                <th><fmt:message key="epam.c.valid"/></th>
                <th></th>
            </tr>

            <fmt:message key="epam.text.true" var="yes"/>    
            <fmt:message key="epam.text.false" var="no"/>    

            <c:choose>
                <c:when test="${empty application}">
                    <c:forEach var="car" items="${requestScope[list]}">
                        <tr>
                            <td><c:out value="${car.model}"/></td>
                            <td><c:out value="${car.placesNumber}"/></td>
                            <td><c:out value="${car.isValid == 'true' ? yes : no}"/></td>
                            <c:if test="${car.isValid == 'true'}">
                                <td><a href="${pageContext.request.contextPath}/<%=Constants.ACTION%>?<%=Constants.RequestParameters.COMMAND_STR%>=<%=Constants.Commands.SET_CAR%>&<%=Constants.RequestParameters.DRIVER%>=${driver.driverId}&<%=Constants.RequestParameters.CAR%>=${car.carId}">
                                        <fmt:message key="epam.text.set"/></a></td>
                                    </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <!-- Back button -->
                <form action="<%=Constants.ACTION%>" method="POST" >
                    <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.SHOW_DRIVERS%>">
                    <input type="hidden" name="<%=Constants.RequestParameters.DRIVER%>" value="${driver.driverId}">
                    <fmt:message key="epam.text.back" var="buttonBack" />
                    <input type="submit" value="${buttonBack}">
                </form>
                <!-- /Back button -->
            </c:when>
            <c:otherwise>
                <c:forEach var="car" items="${requestScope[list]}">
                <tr>
                    <td><c:out value="${car.model}"/></td>
                    <td><c:out value="${car.placesNumber}"/></td>
                    <td><c:out value="${car.isValid == 'true' ? yes : no}"/></td>
                    <td><a href="${pageContext.request.contextPath}/<%=Constants.ACTION%>?<%=Constants.RequestParameters.COMMAND_STR%>=<%=Constants.Commands.SET_CAR%>&<%=Constants.RequestParameters.APPLICATION%>=${application}&<%=Constants.RequestParameters.DRIVER%>=${driver.driverId}&<%=Constants.RequestParameters.CAR%>=${car.carId}">
                            <fmt:message key="epam.text.set"/></a></td>
                </tr>
            </c:forEach>
        </table>
        <!-- Back button -->
        <form action="<%=Constants.ACTION%>" method="POST" >
            <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.SHOW_DRIVERS%>">
            <input type="hidden" name="<%=Constants.RequestParameters.APPLICATION%>" value="${application}">
            <input type="hidden" name="<%=Constants.RequestParameters.DRIVER%>" value="${driver.driverId}">
            <fmt:message key="epam.text.back" var="buttonBack" />
            <input type="submit" value="${buttonBack}">
        </form>
        <!-- /Back button -->
    </c:otherwise>
</c:choose>
</body>
</html>
