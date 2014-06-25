<%-- 
    Document   : trackFinished
    Created on : Jun 25, 2014, 8:21:02 PM
    Author     : Dzyiu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="command">
            <input type="hidden" name="action" value="track_finished">
            <p>
                <input type="checkbox" name="accomplished" value="true" checked required>Track accomplished<br>
            </p>
            <p>
                State of car:<br>
                <input type="radio" name="car" value="true" checked="true">OK<br>
                <input type="radio" name="car" value="false">Broken<br>
            </p>
            <input type="submit" value="Send">
            <input type="reset" value="Clear">
        </form>
    </body>
</html>
