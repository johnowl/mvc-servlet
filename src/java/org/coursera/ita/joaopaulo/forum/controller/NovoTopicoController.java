
package org.coursera.ita.joaopaulo.forum.controller;

import org.coursera.ita.joaopaulo.forum.framework.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coursera.ita.joaopaulo.forum.model.Topico;
import org.coursera.ita.joaopaulo.forum.model.TopicoService;

@WebServlet(name = "NovoTopicoController", urlPatterns = {"/novo"})
public class NovoTopicoController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
        if (!checkUserIsLogged(request, response)) {
            return;
        }        
        
        view("novoTopico", request, response);
        
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!checkUserIsLogged(request, response)) {
            return;
        }        
        
        Topico topico = new Topico();
        topico.setTitulo(request.getParameter("titulo"));
        topico.setConteudo(request.getParameter("conteudo"));
        topico.setLogin(this.getCurrentLogin(request));
        
        TopicoService service = new TopicoService();
        service.cadastrar(topico);    
        
        response.sendRedirect("topicos");
        
    }
}
