<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <title>Login page</title>
    </head>
    <body>
        <c:choose>   

            <c:when test="${not empty sessionScope.username}">
                
                <h1>Hello, ${sessionScope.username}!</h1>
                <form action="./default">
                    <input name="command" type="submit" value="Exit" />
                </form>
            </c:when>
            <c:otherwise>
                <div class="login_main">
                    <form action = "./default" method = "GET" >
                        Login<br>
                        <input name="login" type="text" />
                        <br>Password<br>
                        <input name="password" type="password" />
                        <br>
                        <input name="command" type="submit" value="Sign In" />
                    </form>
                </div>
            </c:otherwise>
        </c:choose>

    </body>


</html>


