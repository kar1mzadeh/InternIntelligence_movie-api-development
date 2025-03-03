package com.zambeyzz.MovieAPIDevelopment.dto;

import com.zambeyzz.MovieAPIDevelopment.enums.Genre;
import lombok.Data;

@Data
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String director;
    private Genre genre;
    private int releaseYear;
    private double imdbRating;
}

