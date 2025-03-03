package com.zambeyzz.MovieAPIDevelopment.service;

import com.zambeyzz.MovieAPIDevelopment.dto.MovieRequestDTO;
import com.zambeyzz.MovieAPIDevelopment.dto.MovieResponseDTO;
import java.util.List;

public interface MovieService {
    MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO);
    List<MovieResponseDTO> getAllMovies();
    MovieResponseDTO getOneMovie(Long id);
    Boolean deleteMovie(Long id);
    Boolean updateMovie(Long id, MovieRequestDTO movieRequestDTO);
}
