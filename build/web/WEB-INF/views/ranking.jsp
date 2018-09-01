<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ranking</title>
    </head>
    <body>
        <h1>Ranking</h1>
        <table>
                <tr>
                    <th>Login</th>
                    <th>Pontos</th>
                </tr>            
            <c:forEach var="u" items="${lista}">
                <tr>
                    <td>${u.login}</td>
                    <td>${u.pontos}</td>
                </tr>
            </c:forEach>
            </table>
    </body>
</html>
