package org.coursera.ita.joaopaulo.forum.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.coursera.ita.joaopaulo.forum.model.Usuario;
import static org.coursera.ita.joaopaulo.forum.repository.Repository.DATABASE;

public class UsuarioRepository extends Repository {

    public Usuario consultar(String usuario, String senha) {

        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, usuario);
            stm.setString(2, senha);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return popularUsuario(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Usuario> consultarRanking() {
        List<Usuario> ranking = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "SELECT * FROM usuario ORDER BY pontos DESC;";
            PreparedStatement stm = c.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Usuario usuario = popularUsuario(rs);
                ranking.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ranking;
    }

    public void incrementarPontuacao(String login, int pontuacao) {
        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?;";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, pontuacao);
            stm.setString(2, login);            
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrar(Usuario u) {
        try (Connection c = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, u.getLogin());
            stm.setString(2, u.getEmail());
            stm.setString(3, u.getNome());
            stm.setString(4, u.getSenha());
            stm.setInt(5, u.getPontos());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario popularUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setLogin(rs.getString("login"));
        usuario.setEmail(rs.getString("email"));
        usuario.setNome(rs.getString("nome"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setPontos(rs.getInt("pontos"));
        return usuario;
    }

}
