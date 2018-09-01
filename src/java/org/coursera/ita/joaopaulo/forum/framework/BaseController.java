package org.coursera.ita.joaopaulo.forum.framework;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coursera.ita.joaopaulo.forum.model.Usuario;

public class BaseController extends HttpServlet {

    private static final String SESSION_LOGIN = "login";
    
    protected void view(String viewName, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {                
        
        request.getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp")
                .forward(request, response);
        
    }
    
    protected void view(String viewName, HttpServletRequest request, 
            HttpServletResponse response, List<Erro> erros) throws ServletException, IOException {
        
        if(erros.isEmpty()) {
            request.setAttribute("temErros", false);
        } else {
            request.setAttribute("temErros", true);
            request.setAttribute("listaErros", erros);
        }
        
        view(viewName, request, response);
    }
    
    protected boolean checkUserIsLogged(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String login = getCurrentLogin(request);
        
        if(login == null || "".equals(login)) {
            response.sendRedirect("login?expired=true"); 
            return false;
        }
        
        return true;
    }
    
    protected String getCurrentLogin(HttpServletRequest request)  {
        return (String)request.getSession().getAttribute(SESSION_LOGIN);
    }
    
    protected void setCurrentLogin(HttpServletRequest request, Usuario usuario)  {
        request.getSession().setAttribute(SESSION_LOGIN, usuario.getLogin());
    }    

}
