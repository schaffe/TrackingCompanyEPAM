<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title><fmt:message key="epam.welcome.title"/></title>
    </head>
    <body>
        <fmt:message key="epam.welcome.welcome"/> <c:set var="name" value="${param}"/>
        <c:out value="${name}" />
        <c:forEach var="pname" items="${param}">  
                        <tr>  
                            <td>  
                                <c:out value="${pname}" />  
                            </td>  
                            <td><c:out value="" /><!-- print param value here --></td>  
                        </tr>           
                    </c:forEach>  
        <!-- Logout button -->
        <form action="command">
            <c:set var="act" value="logout"/>
            <input type="hidden" name="action" value="${act}">
            <fmt:message key="epam.text.logout" var="buttonLogout" />
            <input type="submit" value="${buttonLogout}">
        </form>
        <!------------------->
    </body>


</html>


