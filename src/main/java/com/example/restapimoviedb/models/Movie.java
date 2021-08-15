package com.example.restapimoviedb.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
public class Movie {

    @Id
    private String id;
    private String title;
    private String description;
    private String rating;
    private String backdrops;
    private String posters;
    
    public Movie() {
    }

    public Movie(String id, String title, String description, String rating, String backdrops, String posters) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.backdrops = backdrops;
        this.posters = posters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public void setBackdrops(String backdrops) {
        this.backdrops = backdrops;
    }
    
        public String getBackdrops() {
        return backdrops;
    }
    public void setPosters(String posters) {
        this.posters = posters;
    }
    
        public String getPosters() {
        return posters;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
