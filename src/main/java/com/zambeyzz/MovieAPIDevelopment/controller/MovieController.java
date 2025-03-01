package com.zambeyzz.MovieAPIDevelopment.controller;

import com.zambeyzz.MovieAPIDevelopment.dto.MovieDTO;
import com.zambeyzz.MovieAPIDevelopment.entity.Movie;
import com.zambeyzz.MovieAPIDevelopment.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/1.0/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie movie1 = movieService.createMovie(movie);
        return new ResponseEntity<>(movie1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findOneMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getOneMovie(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        Boolean deleted = movieService.deleteMovie(id);

        if (deleted) {
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Boolean updated = movieService.updateMovie(id, movie);
        if (updated) {
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
