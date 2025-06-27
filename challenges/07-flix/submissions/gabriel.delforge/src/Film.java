import java.util.Objects;

public class Film {
    private String title;
    private int languageId;
    private int rentalDuration;
    private double rentalRate;
    private double replacementCost;

    public Film(String title, int languageId, int rentalDuration, double rentalRate, double replacementCost) {
        this.title = title;
        this.languageId = languageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.replacementCost = replacementCost;
    }

    public String getTitle() {
        return title;
    }

    public int getLanguageId() {
        return languageId;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    @Override
    public String toString() {
        return String.format("Film[title=%s, languageId=%d, rentalDuration=%d, rentalRate=%.2f, replacementCost=%.2f]",
                             title, languageId, rentalDuration, rentalRate, replacementCost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return languageId == film.languageId &&
               rentalDuration == film.rentalDuration &&
               Double.compare(film.rentalRate, rentalRate) == 0 &&
               Double.compare(film.replacementCost, replacementCost) == 0 &&
               Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, languageId, rentalDuration, rentalRate, replacementCost);
    }
}
