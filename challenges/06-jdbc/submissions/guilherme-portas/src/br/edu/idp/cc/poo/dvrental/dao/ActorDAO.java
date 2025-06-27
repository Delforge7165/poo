package br.edu.idp.cc.poo.dvrental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import br.edu.idp.cc.poo.dvrental.util.*;
import br.edu.idp.cc.poo.dvrental.model.Actor;


public class ActorDAO {
    public static void insert(Actor actor) throws IOException, SQLException {
        Connection conn = ConnectionFactory.connectToDatabase();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO actor(actor_id, first_name, last_name) VALUES(?, ?, ?)");
        ps.setInt(1, actor.getId());
        ps.setString(2, actor.getFirstName());
        ps.setString(3, actor.getLastName());
        ps.executeUpdate();
        System.out.println("Insercao realizada com sucesso!");
        AuditLogger.log("INSERT", "Actor inserido: " + actor);
    }   

    public static void listAll() throws IOException, SQLException {
        Connection conn = ConnectionFactory.connectToDatabase();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM actor");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Actor actor = new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"));
            System.out.println();
            System.out.println("actor_id: " + actor.getId());
            System.out.println("first_name: " + actor.getFirstName());
            System.out.println("last_name: " + actor.getLastName());
            AuditLogger.log("SELECT", "Actor listado: " + actor);
        }
    }

    public static void updateId(int id, String firstName, String lastName) throws IOException, SQLException {
        Connection conn = ConnectionFactory.connectToDatabase();
        PreparedStatement ps = conn.prepareStatement("UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?");
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setInt(3, id);
        int linhasAtualizadas = ps.executeUpdate();
        for(int i = 0; i < linhasAtualizadas; i++) {
            System.out.println("Actor atualizado com sucesso!");
            AuditLogger.log("UPDATE", "Actor Atualizado.");
        }
    }

    public static void deleteId(int id) throws IOException, SQLException {
        Connection conn = ConnectionFactory.connectToDatabase();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM actor WHERE actor_id = ?");
        ps.setInt(1, id);
        int linhasDeletadas = ps.executeUpdate();
        for(int i = 0; i < linhasDeletadas; i++) {
            System.out.println("Actor removido com sucesso!");
            AuditLogger.log("DELETE", "Actor removido.");
        }
    }


    /*public static void main(String[] args) {
        insert(new Actor(202,"null", "null"));
        updateId(202, "abc", "def");
        listAll();
        deleteId(202);
    }*/
}
