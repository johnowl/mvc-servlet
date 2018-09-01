package org.coursera.ita.joaopaulo.forum.model;

import java.util.List;
import org.coursera.ita.joaopaulo.forum.framework.Erro;
import org.coursera.ita.joaopaulo.forum.repository.UsuarioRepository;
import org.coursera.ita.joaopaulo.forum.validation.UsuarioValidation;

public class UsuarioService {

    private UsuarioValidation validacao;
    private final UsuarioRepository repositorio;
    
    public UsuarioService() {
        this.repositorio = new UsuarioRepository();
    }
    
    public List<Erro> getErros() {
        return validacao.getErros();
    }   
    
    public boolean cadastrar(Usuario usuario) {
        
        validacao = new UsuarioValidation(usuario);
        
        if(validacao.estaValido()) {
            repositorio.cadastrar(usuario);
            return true;
        }
        
        return false;
    }
    
}
