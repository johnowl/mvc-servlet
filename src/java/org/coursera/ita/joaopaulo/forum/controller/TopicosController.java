package org.coursera.ita.joaopaulo.forum.controller;

import org.coursera.ita.joaopaulo.forum.framework.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coursera.ita.joaopaulo.forum.model.TopicoService;
import org.coursera.ita.joaopaulo.forum.model.Topico;

@WebServlet(name = "Topicos", urlPatterns = {"/topicos"})
public class TopicosController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!checkUserIsLogged(request, response)) {
            return;
        }
        
        String id = request.getParameter("id");
        
        TopicoService service = new TopicoService();
                
        if(id == null ||  "".equals(id)) {
            request.setAttribute("topicos", service.consultarTodos());
            view("topicos", request, response);
            return;
        }
        
        request.setAttribute("topico", service.consultar(Integer.parseInt(id)));
        view("topico", request, response);
        
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
        topico.setLogin(getCurrentLogin(request));
                
        TopicoService service = new TopicoService();
        service.cadastrar(topico);        
        
        response.sendRedirect("topicos");
    }
}
