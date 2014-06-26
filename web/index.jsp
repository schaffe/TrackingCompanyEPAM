<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="bundle" />

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title><fmt:message key="epam.login.title"/></title>
    </head>
    <body>
        <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
            </select>
        </form>

        <div class="login_main">
            <form action = "./command" method = "GET" >
                <input type="hidden" name="action" value="auth">
                <fmt:message key="epam.login.login"/><br>
                <input name="login" type="text" /><br>
                <fmt:message key="epam.login.password"/><br>
                <input name="password" type="password" /><br>
                <fmt:message key="epam.login.sign_in" var="buttonValue" />
                <input type="submit" value="${buttonValue}" />
            </form>
        </div>
    </body>


</html>


