package org.coursera.ita.joaopaulo.forum.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topico {

    private int id;
    private String titulo;
    private String conteudo;
    private String login;
    private List<Comentario> comentarios;

    public Topico() {
        this.comentarios = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public List<Comentario> getComentarios() {
        return Collections.unmodifiableList(comentarios);
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
