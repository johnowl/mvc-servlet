package org.coursera.ita.joaopaulo.forum.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.coursera.ita.joaopaulo.forum.model.Comentario;
import org.coursera.ita.joaopaulo.forum.model.Topico;
import static org.coursera.ita.joaopaulo.forum.repository.Repository.DATABASE;

public class TopicoRepository extends Repository {

    public Topico consultar(int topicoId) {
        
        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "SELECT * FROM topico WHERE id_topico = ? ORDER BY id_topico DESC";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, topicoId);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Topico topico = popularTopico(rs);
                    topico.setComentarios(consultarComentarios(topicoId));
                    return topico;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    public List<Topico> consultarTodos() {
        List<Topico> topicos = new ArrayList<>();
       
        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "SELECT * FROM topico ORDER BY id_topico DESC";
            PreparedStatement stm = c.prepareStatement(sql);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    topicos.add(popularTopico(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return topicos;
    }
    
    private List<Comentario> consultarComentarios(int idTopico) {
        List<Comentario> comentarios = new ArrayList<>();
       
        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "SELECT id_comentario, comentario, login FROM comentario WHERE id_topico = ? ORDER BY id_topico DESC";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, idTopico);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    comentarios.add(popularComentario(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return comentarios;
    }
    
    public void adicionar(Topico t) {
        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "INSERT INTO topico(titulo, conteudo, login) VALUES (?, ?, ?);";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, t.getTitulo());
            stm.setString(2, t.getConteudo());
            stm.setString(3, t.getLogin());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void comentar(int topicoId, Comentario comentario) {
        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "INSERT INTO comentario(comentario, login, id_topico) VALUES (?, ?, ?);";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, comentario.getComentario());
            stm.setString(2, comentario.getLogin());
            stm.setInt(3, topicoId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Topico popularTopico(ResultSet rs) throws SQLException {
        Topico t = new Topico();
        t.setConteudo(rs.getString("conteudo"));
        t.setTitulo(rs.getString("titulo"));
        t.setLogin(rs.getString("login"));
        t.setId(rs.getInt("id_topico"));
        return t;        
    }

    private Comentario popularComentario(ResultSet rs) throws SQLException {        
        Comentario comentario = new Comentario();
        comentario.setComentario(rs.getString("comentario"));
        comentario.setId(rs.getInt("id_comentario"));
        comentario.setLogin(rs.getString("login"));
        return comentario;
    }
    
}
