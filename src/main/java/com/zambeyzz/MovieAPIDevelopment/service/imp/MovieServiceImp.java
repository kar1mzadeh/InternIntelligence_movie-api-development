package com.zambeyzz.MovieAPIDevelopment.service.imp;

import com.zambeyzz.MovieAPIDevelopment.dto.MovieRequestDTO;
import com.zambeyzz.MovieAPIDevelopment.dto.MovieResponseDTO;
import com.zambeyzz.MovieAPIDevelopment.entity.Movie;
import com.zambeyzz.MovieAPIDevelopment.enums.Genre;
import com.zambeyzz.MovieAPIDevelopment.mapper.MovieMapper;
import com.zambeyzz.MovieAPIDevelopment.repository.MovieRepository;
import com.zambeyzz.MovieAPIDevelopment.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImp implements MovieService {


    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO) {
        Movie movie = movieMapper.toEntity(movieRequestDTO);
        Movie savedMo= movieRepository.save(movie);
        return movieMapper.toResponseDTO(savedMo);
    }

    @Override
    public List<MovieResponseDTO> getAllMovies() {
        return  movieRepository.findAll()
                .stream()
                .map(movieMapper ::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MovieResponseDTO getOneMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        return movieMapper.toResponseDTO(movie);
    }

    @Override
    public Boolean deleteMovie(Long id) {

        if (!movieRepository.existsById(id)) {
            return false;
        }
        movieRepository.deleteById(id);
        return true;
    }
    @Override
    public Boolean updateMovie(Long id, MovieRequestDTO movieRequestDTO) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        existingMovie.setTitle(movieRequestDTO.getTitle());
        existingMovie.setDirector(movieRequestDTO.getDirector());
        existingMovie.setReleaseYear(movieRequestDTO.getReleaseYear());
        existingMovie.setImdbRating(movieRequestDTO.getImdbRating());
        existingMovie.setGenre(movieRequestDTO.getGenre());

        movieRepository.save(existingMovie);
        return true;
    }


}
