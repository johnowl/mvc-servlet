
package org.coursera.ita.joaopaulo.forum.controller;

import org.coursera.ita.joaopaulo.forum.framework.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coursera.ita.joaopaulo.forum.model.Usuario;
import org.coursera.ita.joaopaulo.forum.model.UsuarioService;


@WebServlet(name = "CadastroController", urlPatterns = {"/cadastro"})
public class CadastroController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("nome", "");
        request.setAttribute("login", "");
        request.setAttribute("email", "");
        request.setAttribute("senha", "");
        
        view("cadastro", request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario usuario = new Usuario();
        usuario.setNome(request.getParameter("nome"));
        usuario.setLogin(request.getParameter("login"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setSenha(request.getParameter("senha"));
        
        UsuarioService service = new UsuarioService();
        
        if(service.cadastrar(usuario)) {            
            response.sendRedirect("login?mensagem=1");
            return;
        }
        
        request.setAttribute("nome", usuario.getNome());
        request.setAttribute("login", usuario.getLogin());
        request.setAttribute("email", usuario.getEmail());
        request.setAttribute("senha", usuario.getSenha());       
        
        view("cadastro", request, response, service.getErros());
        
    }
    
}
