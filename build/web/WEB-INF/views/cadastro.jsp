<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastre-se</title>
    </head>
    <body>
        <h1>Cadastre-se</h1>
        
        <c:if test="${temErros}">
            <p>Foram encontrados erros, por favor corrija-os para continuar.</p>
            <ul>
                <c:forEach var="erro" items="${listaErros}">
                    <li>${erro.mensagem}</li>
                </c:forEach>
            </ul>
        </c:if>
        
        
        
        <form method="post">
            <div>
                <label>Nome</label>
                <input type="text" name="nome" id="nome" value="${nome}" />
            </div>
            
            <div>
                <label>Login</label>
                <input type="text" name="login" id="login" value="${login}" />
            </div>
            
            <div>
                <label>E-mail</label>
                <input type="text" name="email" id="email" value="${email}" />
            </div>
                
            <div>
                <label>Senha</label>
                <input type="password" name="senha" id="senha" value="${senha}" />
            </div>
            
            <div>
                <input type="submit" value="Cadastrar" id="cadastrar" />
            </div>
        </form>
    </body>
</html>
