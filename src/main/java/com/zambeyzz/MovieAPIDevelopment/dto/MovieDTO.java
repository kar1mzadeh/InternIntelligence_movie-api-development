package com.zambeyzz.MovieAPIDevelopment.dto;

import lombok.Data;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private int releaseYear;
    private String directorName;
    private String genre;
    private double imdbRating;

    public MovieDTO(Long id, String title, int releaseYear, String directorName, String genre, double imdbRating) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.directorName = directorName;
        this.genre = genre;
        this.imdbRating = imdbRating;
    }
}


