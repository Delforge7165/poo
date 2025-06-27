import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class IDPFlixApp {
    private static final String PROPS_FILE = "../data/db.properties.sample";
    private static final String DATA_FILE = "../data/new_films.txt";

    public static void main(String[] args) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPS_FILE)) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Erro ao carregar propriedades de banco: " + e.getMessage());
            return;
        }
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        List<Film> films = FilmFileReader.readFromFile(DATA_FILE);
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            try (PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO film (title, language_id, rental_duration, rental_rate, replacement_cost) VALUES (?, ?, ?, ?, ?)")) {
                for (Film film : films) {
                    insertStmt.setString(1, film.getTitle());
                    insertStmt.setInt(2, film.getLanguageId());
                    insertStmt.setInt(3, film.getRentalDuration());
                    insertStmt.setDouble(4, film.getRentalRate());
                    insertStmt.setDouble(5, film.getReplacementCost());
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
            }
            try (PreparedStatement updateStmt = conn.prepareStatement(
                    "UPDATE film SET rental_rate = rental_rate * 1.1")) {
                int updated = updateStmt.executeUpdate();
                System.out.println("Filmes atualizados: " + updated);
            }
            try (PreparedStatement selectStmt = conn.prepareStatement(
                    "SELECT title, rental_rate FROM film WHERE rental_duration = 99");
                 ResultSet rs = selectStmt.executeQuery()) {
                System.out.println("Filmes com duracao de locacao 99:");
                while (rs.next()) {
                    System.out.printf("%s - R$ %.2f%n", rs.getString("title"), rs.getDouble("rental_rate"));
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados: " + e.getMessage());
        }
    }
}
