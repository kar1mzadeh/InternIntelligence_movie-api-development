package com.zambeyzz.MovieAPIDevelopment.service.imp;

import com.zambeyzz.MovieAPIDevelopment.dto.MovieDTO;
import com.zambeyzz.MovieAPIDevelopment.entity.Movie;
import com.zambeyzz.MovieAPIDevelopment.enums.Genre;
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

@Service
public class MovieServiceImp implements MovieService {


    private final MovieRepository movieRepository;


    @Autowired
    public MovieServiceImp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOs = new ArrayList<>();

        for (Movie movie : movies) {
            movieDTOs.add(new MovieDTO(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getReleaseYear(),
                    movie.getDirector(),
                    movie.getGenre() != null ? movie.getGenre().name() : null,
                    movie.getImdbRating()
            ));
        }
        return movieDTOs;
    }

    public MovieDTO getOneMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getDirector(),
                movie.getGenre() != null ? movie.getGenre().name() : null,
                movie.getImdbRating()

        );
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
    public Boolean updateMovie(Long id, Movie movie) {

        Movie updatedMovie = movieRepository.findById(id).orElse(null);
        if (updatedMovie != null) {
            updatedMovie.setTitle(movie.getTitle());
            updatedMovie.setDirector(movie.getDirector());
            updatedMovie.setReleaseYear(movie.getReleaseYear());
            updatedMovie.setImdbRating(movie.getImdbRating());
            updatedMovie.setGenre(movie.getGenre());

            movieRepository.save(updatedMovie);
            return true;
        }
        return false;

    }

    private void validateGenre(Genre genre) {
        if (genre == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Genre cannot be null.");
        }
    }

}
