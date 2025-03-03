package com.zambeyzz.MovieAPIDevelopment.dto;

import com.zambeyzz.MovieAPIDevelopment.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MovieRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Director is required")
    private String director;

    @NotNull(message = "Genre is required")
    private Genre genre;

    @Min(value = 1800, message = "Release year must be at least 1800")
    private int releaseYear;

    @Min(value = 0, message = "IMDb rating must be at least 0")
    @Max(value = 10, message = "IMDb rating must be at most 10")
    private double imdbRating;
}
