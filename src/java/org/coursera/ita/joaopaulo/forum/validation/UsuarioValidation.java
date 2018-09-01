package org.coursera.ita.joaopaulo.forum.validation;

import java.util.ArrayList;
import java.util.List;
import org.coursera.ita.joaopaulo.forum.framework.Erro;
import org.coursera.ita.joaopaulo.forum.framework.Validation;
import org.coursera.ita.joaopaulo.forum.model.Usuario;

public class UsuarioValidation implements Validation {

    private final Usuario usuario;
    private List<Erro> erros;
    
    public UsuarioValidation(Usuario usuario) {
        this.usuario = usuario;
        this.erros = new ArrayList<>();
    }

    @Override
    public List<Erro> getErros() {
        return erros;
    }

    @Override
    public boolean estaValido() {
        
        this.erros = new ArrayList<>();
        
        if(usuario.getNome() == null || "".equals(usuario.getNome())) {
            this.erros.add(new Erro("nome", "O nome é obrigatório"));
        }
        
        if(usuario.getEmail() == null || "".equals(usuario.getEmail())) {
            this.erros.add(new Erro("email", "O e-mail é obrigatório"));
        }
                
        if(usuario.getLogin() == null || "".equals(usuario.getLogin())) {
            this.erros.add(new Erro("login", "O login é obrigatório"));
        }
        
        if(usuario.getSenha() == null || "".equals(usuario.getSenha())) {
            this.erros.add(new Erro("senha", "A senha é obrigatória"));
        }        
        
        return this.erros.isEmpty();
    }

}
