<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<%@ page import="ua.kpi.project4.Constants" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />

<c:set var='user_id' value="<%=Constants.SessionParameters.USER_ID%>" />
<c:if test="${not empty sessionScope[user_id]}">
    <c:redirect url="<%=Constants.Pages.WELCOME_PAGE%>" />
</c:if>


<!DOCTYPE html>
<html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title><fmt:message key="epam.login.title"/></title>
    </head>
    <body>
        <form>
            <select action="index.jsp" id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
            </select>
        </form>

        <div class="login_main">
            <form action="<%=Constants.ACTION%>" method="POST" >
                <input type="hidden" name="<%=Constants.RequestParameters.COMMAND_STR%>" value="<%=Constants.Commands.AUTH%>">
                <fmt:message key="epam.login.login"/><br>
                <input name="<%=Constants.RequestParameters.LOGIN%>" type="text" /><br>
                <fmt:message key="epam.login.password"/><br>
                <input name="<%=Constants.RequestParameters.PASSWORD%>" type="password" /><br>
                <fmt:message key="epam.login.sign_in" var="buttonValue" />
                <input type="submit" value="${buttonValue}" />
            </form>
        </div>
    </body>


</html>


