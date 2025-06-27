package br.edu.idp.cc.poo.dvrental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import br.edu.idp.cc.poo.dvrental.util.*;
import br.edu.idp.cc.poo.dvrental.model.Film;


public class FilmDAO {
    public static void insert(Film film) throws IOException, SQLException {
        Connection conn = ConnectionFactory.connectToDatabase();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO film(title, description, language_id) VALUES(?, ?, 1)");
        ps.setString(1, film.getTitle());
        ps.setString(2, film.getDescription());
        ps.executeUpdate();
        AuditLogger.log("INSERT", "Film inserido: " + film);
    }   

    public static Film buscaID(int id) throws IOException, SQLException {
        Connection conn = ConnectionFactory.connectToDatabase();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM film WHERE film_id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Film film = new Film(rs.getInt("film_id"), rs.getString("title"), rs.getString("description"));
        AuditLogger.log("SELECT", "Film procurado: " + film);
        return film;
    }

    public static Film buscaTitle(String title) throws IOException, SQLException {
        Connection conn = ConnectionFactory.connectToDatabase();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM film WHERE title = ?");
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Film film = new Film(rs.getInt("film_id"), rs.getString("title"), rs.getString("description"));
        AuditLogger.log("SELECT", "Film procurado: " + film);
        return film;
    }

/*    public static void main(String[] args) {
        insert(new Film("null", "null"));
        buscaID(1001);
        buscaTitle("null");
    }*/
}
