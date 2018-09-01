package org.coursera.ita.joaopaulo.forum.repository;

import org.coursera.ita.joaopaulo.forum.model.Usuario;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

public class UsuarioRepositoryTests {

    private JdbcDatabaseTester jdt;
    private UsuarioRepository dao;

    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester(Repository.DB_DRIVER_CLASS_NAME, Repository.DATABASE, Repository.USER, Repository.PASSWORD);
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/database.xml"));
        jdt.onSetup();

        dao = new UsuarioRepository();
    }

    @Test
    public void consultarTest() {
        Usuario joao = dao.consultar("joao", "abc123");
        assertEquals("joao@email.com.br", joao.getEmail());
        assertEquals("joao", joao.getLogin());
        assertEquals("João da Silva", joao.getNome());
        assertEquals("abc123", joao.getSenha());
        assertEquals(100, joao.getPontos());
    }

    @Test
    public void cadastrarTest() {
        Usuario jose = new Usuario();
        jose.setNome("José de Oliveira");
        jose.setEmail("jose@dasilva.com");
        jose.setLogin("jose");
        jose.setPontos(50);
        jose.setSenha("p4ssw0rd");
        dao.cadastrar(jose);

        Usuario joseDb = dao.consultar("jose", "p4ssw0rd");
        assertEquals(jose.getPontos(), joseDb.getPontos());
    }

    @Test
    public void incrementarPontuacaoTest() {

        dao.incrementarPontuacao("joao", 120);
        Usuario joao = dao.consultar("joao", "abc123");

        assertEquals(220, joao.getPontos());
    }

    @Test
    public void consultarRankingTest() {
        List<Usuario> ranking = dao.consultarRanking();
        assertEquals(2, ranking.size());
    }
}
