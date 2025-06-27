import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilmFileReader {
    public static List<Film> readFromFile(String filePath) {
        List<Film> films = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.trim().split("\\s*;\\s*");
                String title = parts[0];
                int languageId = Integer.parseInt(parts[1]);
                int rentalDuration = Integer.parseInt(parts[2]);
                double rentalRate = Double.parseDouble(parts[3]);
                double replacementCost = Double.parseDouble(parts[4]);
                films.add(new Film(title, languageId, rentalDuration, rentalRate, replacementCost));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return films;
    }
}
