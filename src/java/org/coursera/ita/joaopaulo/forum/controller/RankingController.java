package org.coursera.ita.joaopaulo.forum.controller;

import org.coursera.ita.joaopaulo.forum.framework.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coursera.ita.joaopaulo.forum.model.RankingService;


@WebServlet(name = "RankingController", urlPatterns = {"/ranking"})
public class RankingController extends BaseController {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!checkUserIsLogged(request, response)) {
            return;
        }
        
        RankingService service = new RankingService();
        request.setAttribute("lista", service.consultar());
        
        view("ranking", request, response);
        
    }
}
