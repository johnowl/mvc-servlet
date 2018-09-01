package org.coursera.ita.joaopaulo.forum.controller;

import org.coursera.ita.joaopaulo.forum.framework.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.coursera.ita.joaopaulo.forum.model.Autenticacao;
import org.coursera.ita.joaopaulo.forum.model.Usuario;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends BaseController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Autenticacao autenticacao = new Autenticacao();

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        Usuario usuario = autenticacao.autenticar(login, senha);

        if (usuario != null) {
            setCurrentLogin(request, usuario);
            response.sendRedirect("topicos");
            return;
        }

        request.setAttribute("erroLogin", true);
        view("login", request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        view("login", request, response);

    }

}
