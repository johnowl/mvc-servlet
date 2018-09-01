package org.coursera.ita.joaopaulo.forum.model;

import java.util.List;
import org.coursera.ita.joaopaulo.forum.repository.TopicoRepository;

public class TopicoService {

    private final TopicoRepository repository;
    private final RankingService ranking;
    
    public TopicoService() {
        repository = new TopicoRepository();
        ranking = new RankingService();
    }
    
    public List<Topico> consultarTodos() {
        return repository.consultarTodos();
    }
    
    public Topico consultar(int id) {
        return repository.consultar(id);
    }
    
    public void comentar(int idTopico, Comentario comentario) {
        repository.comentar(idTopico, comentario);
        ranking.computarPontuacao(comentario.getLogin(), RankingTipoPontuacao.COMENTARIO);
    }

    public void cadastrar(Topico topico) {
        repository.adicionar(topico);
        ranking.computarPontuacao(topico.getLogin(), RankingTipoPontuacao.POST);
    }
}
