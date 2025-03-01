package com.zambeyzz.MovieAPIDevelopment.entity;

import com.zambeyzz.MovieAPIDevelopment.enums.Genre;
import com.zambeyzz.MovieAPIDevelopment.exceptions.InvalidReleaseYearException;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Director cannot be empty")
    private String director;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;
    @DecimalMin(value = "0.0", message = "ImdbRating cannot be below 0.0")
    @DecimalMax(value = "10.0", message = "ImdbRating cannot be above 10.0")
    private double imdbRating;
    @Min(value = 1800, message = "Release year must be after 1800 year")
    private int releaseYear;

    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null.");
        }
        this.genre = genre;
    }

    public void setImdbRating(double imdbRating) {
        if (imdbRating < 0.0 || imdbRating > 10.0) {
            throw new IllegalArgumentException("IMDb rating must be between 0 and 10.");
        }
        this.imdbRating = imdbRating;
    }
    public void setReleaseYear(int releaseYear) {
        int currentYear = java.time.Year.now().getValue();

        if (releaseYear > currentYear) {
            throw new InvalidReleaseYearException();
        }

        this.releaseYear = releaseYear;
    }


}

