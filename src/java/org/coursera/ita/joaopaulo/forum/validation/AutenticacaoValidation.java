package org.coursera.ita.joaopaulo.forum.validation;

import java.util.ArrayList;
import java.util.List;

import org.coursera.ita.joaopaulo.forum.framework.Erro;
import org.coursera.ita.joaopaulo.forum.framework.Validation;

public class AutenticacaoValidation implements Validation {

    private List<Erro> erros;
    private String login;
    private String senha;

    public AutenticacaoValidation(String login, String senha) {
        this();
        this.login = login;
        this.senha = senha;
    }

    @Override
    public List<Erro> getErros() {
        return this.erros;
    }

    private AutenticacaoValidation() {
        this.erros = new ArrayList<>();
    }

    @Override
    public boolean estaValido() {

        if("".equals(login))
                this.erros.add(new Erro("login", "O campo login não pode ficar em branco."));

        if("".equals(senha))
                this.erros.add(new Erro("senha", "O campo senha não pode ficar em branco."));

        return this.erros.isEmpty();
    }
	
}
