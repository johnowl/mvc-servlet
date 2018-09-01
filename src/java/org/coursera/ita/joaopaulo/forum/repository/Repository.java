package org.coursera.ita.joaopaulo.forum.repository;

public class Repository {

    protected static final String DB_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    protected static final String DATABASE = "jdbc:postgresql://localhost/coursera";
    protected static final String USER = "postgres";
    protected static final String PASSWORD = "changeme";

    static {
        try {
            Class.forName(DB_DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Não foi possível carregar o driver do banco de dados", e);
        }
    }
}
