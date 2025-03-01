package com.zambeyzz.MovieAPIDevelopment.service;

import com.zambeyzz.MovieAPIDevelopment.dto.MovieDTO;
import com.zambeyzz.MovieAPIDevelopment.entity.Movie;

import java.util.List;


public interface MovieService{
    Movie createMovie(Movie movie);
    List<MovieDTO> getAllMovies();
MovieDTO getOneMovie(Long id);
Boolean deleteMovie(Long id);
Boolean updateMovie(Long id, Movie movie);
}
