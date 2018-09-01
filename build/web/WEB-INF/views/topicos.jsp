<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tópicos</title>
    </head>
    <body>
        
        <h1>Tópicos</h1>
        <p><a href="novo">Criar tópico</a></p>                
        <p><a href="ranking">Ver ranking</a></p>
        
        <c:if test="${empty topicos}">
          <p>Nenhum tópico cadastrado, seja o primeiro a <a href="novo">criar um tópico</a>.</p>            
        </c:if>
    
        <c:if test="${not empty topicos}">
            <table>
                <tr>
                    <th>Título</th>
                    <th>Autor</th>
                </tr>            
            <c:forEach var="topico" items="${topicos}">
                <tr>
                    <td><a href="topicos?id=${topico.id}">${topico.titulo}</a></td>
                    <td>${topico.login}</td>
                </tr>
            </c:forEach>
            </table>
        </c:if>
        
    </body>
</html>