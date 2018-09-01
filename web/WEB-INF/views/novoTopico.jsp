<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo tópico</title>
    </head>
    <body>
        <h1>Novo tópico</h1>
        <p><a href="topicos">Ver todos os tópicos</a></p>
        
        <form method="POST" action="topicos">
            <div>
                <label>Título</label>
                <input type="text" id="titulo" name="titulo" />
            </div>
            
            <div>
                <label>Conteúdo</label>
                <textarea cols="60" rows="10" id="conteudo" name="conteudo" /></textarea>
            </div>
            <br />
            <input type="submit" name="gravar" id="gravar" value="Gravar" />
        </form>
    </body>
</html>
