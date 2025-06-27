package br.edu.idp.cc.poo.dvrental.model;

public class Film {
    private int film_id;
    private String title, description;

    public Film(int film_id, String title, String description) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
    }
    
    public Film(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getFilm_id() {return this.film_id;}
    public String getTitle() {return this.title;}
    public String getDescription() {return this.description;}
}
