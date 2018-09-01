package org.coursera.ita.joaopaulo.forum.controller;

import org.coursera.ita.joaopaulo.forum.framework.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coursera.ita.joaopaulo.forum.model.Comentario;
import org.coursera.ita.joaopaulo.forum.model.TopicoService;

@WebServlet(name = "ComentariosController", urlPatterns = {"/comentarios"})
public class ComentariosController extends BaseController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!checkUserIsLogged(request, response)) {
            return;
        }

        int idTopico = Integer.parseInt(request.getParameter("idTopico"));

        Comentario comentario = new Comentario();
        comentario.setComentario(request.getParameter("novoComentario"));
        comentario.setLogin(getCurrentLogin(request));

        TopicoService service = new TopicoService();
        service.comentar(idTopico, comentario);

        response.sendRedirect("topicos?id=" + idTopico);
    }

}
