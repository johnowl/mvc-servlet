package org.coursera.ita.joaopaulo.forum.model;

import org.coursera.ita.joaopaulo.forum.framework.Erro;
import java.util.List;

import org.coursera.ita.joaopaulo.forum.repository.UsuarioRepository;
import org.coursera.ita.joaopaulo.forum.validation.AutenticacaoValidation;

public class Autenticacao {

    private UsuarioRepository repositorio;
    private AutenticacaoValidation validacao;

    public List<Erro> getErros() {
        return this.validacao.getErros();
    }

    public Autenticacao() {
        this.repositorio = new UsuarioRepository();
    }

    public Usuario autenticar(String login, String senha) {

        this.validacao = new AutenticacaoValidation(login, senha);

        if (validacao.estaValido()) {
            return repositorio.consultar(login, senha);
        }

        return null;

    }

}
