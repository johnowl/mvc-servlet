package org.coursera.ita.joaopaulo.forum.model;

import java.util.List;
import org.coursera.ita.joaopaulo.forum.repository.UsuarioRepository;

public class RankingService {

    private final UsuarioRepository repository;

    public RankingService() {
        repository = new UsuarioRepository();
    }
    
    public List<Usuario> consultar() {
        return repository.consultarRanking();
    }
    
    public void computarPontuacao(String login, RankingTipoPontuacao acao) {
        int pontuacao = 0;
        
        if(acao == RankingTipoPontuacao.COMENTARIO) {
            pontuacao = 3;
        }
        
        if(acao == RankingTipoPontuacao.POST) {
            pontuacao = 10;
        }
        
        if(pontuacao > 0) {
            repository.incrementarPontuacao(login, pontuacao);
        }
    }
}
