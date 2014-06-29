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
        <title><fmt:message key="epam.a.title"/></title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/headerButtons.jspf" %>


        <table border="1">
            <caption><fmt:message key="epam.a.title"/></caption>
            <tr>
                <th><fmt:message key="epam.a.id"/></th>
                <th><fmt:message key="epam.a.from"/></th>
                <th><fmt:message key="epam.a.where"/></th>
                <th><fmt:message key="epam.a.arrival_time"/></th>
                <th><fmt:message key="epam.a.status"/></th>
            </tr>

            <c:set var='application_list' value="<%=Constants.RequestParameters.APPLICATIONS%>" />
            <c:forEach var="application" items="${requestScope[application_list]}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/<%=Constants.ACTION%>?<%=Constants.RequestParameters.COMMAND_STR%>=<%=Constants.Commands.VIEW_APPLICATION%>&<%=Constants.RequestParameters.ID%>=${application.applicationId}">
                            <c:out value="${application.applicationId}"/></a></td>
                    <td><c:out value="${application.from}"/></td>
                    <td><c:out value="${application.destination}"/></td>
                    <td><c:out value="${application.arrivalTime}"/></td>
                    <td><c:out value="${application.status}"/></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
