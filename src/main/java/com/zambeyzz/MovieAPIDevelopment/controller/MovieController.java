package com.zambeyzz.MovieAPIDevelopment.controller;

import com.zambeyzz.MovieAPIDevelopment.dto.MovieRequestDTO;
import com.zambeyzz.MovieAPIDevelopment.dto.MovieResponseDTO;
import com.zambeyzz.MovieAPIDevelopment.mapper.MovieMapper;
import com.zambeyzz.MovieAPIDevelopment.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/1.0/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;


    @Autowired
    public MovieController(MovieService movieService , MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper=movieMapper;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
MovieResponseDTO response = movieService.createMovie(movieRequestDTO);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> findAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> findOneMovie(@PathVariable Long id) {
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
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequestDTO) {
        Boolean updated = movieService.updateMovie(id, movieRequestDTO);
        if (updated) {
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
