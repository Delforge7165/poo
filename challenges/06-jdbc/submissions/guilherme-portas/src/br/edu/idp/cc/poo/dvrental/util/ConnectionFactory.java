package br.edu.idp.cc.poo.dvrental.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConnectionFactory {
    public static Connection connectToDatabase() throws SQLException, IOException {
        Properties props = new Properties();
            // tem que inserir no arquivo db.properties o link, o usuario e a senha
            var inputStream = Files.newInputStream(Paths.get("br/edu/idp/cc/poo/dvrental/db.properties"));
            props.load(inputStream);
        Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
        return conn;
    }
}
