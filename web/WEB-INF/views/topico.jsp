<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tópico</title>
    </head>
    <body>
        <h1>${topico.titulo}</h1>
        
        <p><a href="topicos">Ver tópicos</a></p>
        
        <p>${topico.login}: ${topico.conteudo}</p>
               
        <c:forEach items="${topico.comentarios}" var="c">
        <p>${c.login} disse: ${c.comentario}</p>
        </c:forEach>

        <form method="post" action="comentarios">
            <input type="hidden" name="idTopico" id="idTopico" value="${topico.id}" />
            <textarea cols="30" rows="10" id="novoComentario" name="novoComentario" /></textarea>
            <br />
            <input type="submit" name="gravar" id="gravar" value="Gravar" />
        </form>
    </body>
</html>
