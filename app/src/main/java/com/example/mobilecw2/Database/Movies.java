package com.example.mobilecw2.Database;

public class Movies {

    int movieId;
    int year;
    String movieTitle;
    String director;
    String cast;
    String description;
    int rating;
    int isFavourite;



    public Movies(){

    }

    public Movies(int movieId, String movieTitle, int year, String director, String cast, String description, int rating, int isFavourite) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.year = year;
        this.director = director;
        this.cast = cast;
        this.description = description;
        this.rating = rating;
        this.isFavourite = isFavourite;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

}
