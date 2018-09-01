package org.coursera.ita.joaopaulo.forum.repository;

import org.coursera.ita.joaopaulo.forum.model.Usuario;
import static org.junit.Assert.assertEquals;

import java.util.List;
import org.coursera.ita.joaopaulo.forum.model.Comentario;
import org.coursera.ita.joaopaulo.forum.model.Topico;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

public class TopicoRepositoryTests {

    private JdbcDatabaseTester jdt;
    private TopicoRepository dao;

    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester(Repository.DB_DRIVER_CLASS_NAME, Repository.DATABASE, Repository.USER, Repository.PASSWORD);
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/database.xml"));
        jdt.onSetup();

        dao = new TopicoRepository();
    }

    @Test
    public void consultarTest() {
        Topico topico = dao.consultar(1);

        assertEquals("Tópico 1", topico.getTitulo());
        assertEquals("joao", topico.getLogin());
        assertEquals(1, topico.getComentarios().size());
    }

    @Test
    public void consultarTodosTest() {
        List<Topico> topicos = dao.consultarTodos();

        assertEquals(2, topicos.size());
    }
    
    @Test
    public void adicionarTest() {
        
        Topico topico = new Topico();
        topico.setConteudo("tópico");
        topico.setLogin("joao");
        topico.setTitulo("Teste");
        
        dao.adicionar(topico);
        
        List<Topico> topicos = dao.consultarTodos();

        assertEquals("tópico", topicos.get(0).getConteudo());
        assertEquals("joao", topicos.get(0).getLogin());
        assertEquals("Teste", topicos.get(0).getTitulo());
    }
    
    @Test
    public void comentarTest() {
        Comentario comentario = new Comentario();
        comentario.setComentario("Gostei!");
        comentario.setLogin("joao");
       
        dao.comentar(1, comentario);
        
        Topico topico = dao.consultar(1);

        assertEquals(2, topico.getComentarios().size());
        assertEquals("Gostei!", topico.getComentarios().get(1).getComentario());
        assertEquals("joao", topico.getComentarios().get(1).getLogin());
                
    }

}
