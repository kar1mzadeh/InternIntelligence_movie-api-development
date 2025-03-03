//package com.zambeyzz.MovieAPIDevelopment.service.imp;
//
//import com.zambeyzz.MovieAPIDevelopment.dto.MovieDTO;
//import com.zambeyzz.MovieAPIDevelopment.entity.Movie;
//import com.zambeyzz.MovieAPIDevelopment.repository.MovieRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class MovieServiceImpTest {
//
//    @Mock
//    private MovieRepository movieRepository;
//
//    @InjectMocks
//    private MovieServiceImp movieService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this); // Initialize mocks
//    }
//
//    @Test
//    void testCreateMovie() {
//        // Arrange
//        Movie movie = new Movie(null, "Inception", "Christopher Nolan", "Sci-Fi", 8.8, 2010);
//        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
//
//        // Act
//        Movie createdMovie = movieService.createMovie(movie);
//
//        // Assert
//        assertNotNull(createdMovie);
//        assertEquals("Inception", createdMovie.getTitle());
//        verify(movieRepository, times(1)).save(movie);
//    }
//
//    @Test
//    void testGetAllMovies() {
//        // Arrange
//        List<Movie> movies = List.of(
//                new Movie(1L, "Inception", "Christopher Nolan", "Sci-Fi", 8.8, 2010),
//                new Movie(2L, "Interstellar", "Christopher Nolan", "Sci-Fi", 8.6, 2014)
//        );
//        when(movieRepository.findAll()).thenReturn(movies);
//
//        // Act
//        List<MovieDTO> result = movieService.getAllMovies();
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("Inception", result.get(0).getTitle());
//        verify(movieRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testGetOneMovie_Found() {
//        // Arrange
//        Movie movie = new Movie(1L, "Inception", "Christopher Nolan", "Sci-Fi", 8.8, 2010);
//        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
//
//        // Act
//        MovieDTO result = movieService.getOneMovie(1L);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("Inception", result.getTitle());
//        verify(movieRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testGetOneMovie_NotFound() {
//        // Arrange
//        when(movieRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        Exception exception = assertThrows(RuntimeException.class, () -> movieService.getOneMovie(1L));
//        assertTrue(exception.getMessage().contains("Movie not found"));
//        verify(movieRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testDeleteMovie_Success() {
//        // Arrange
//        when(movieRepository.existsById(1L)).thenReturn(true);
//        doNothing().when(movieRepository).deleteById(1L);
//
//        // Act
//        boolean result = movieService.deleteMovie(1L);
//
//        // Assert
//        assertTrue(result);
//        verify(movieRepository, times(1)).existsById(1L);
//        verify(movieRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    void testDeleteMovie_NotFound() {
//        // Arrange
//        when(movieRepository.existsById(1L)).thenReturn(false);
//
//        // Act
//        boolean result = movieService.deleteMovie(1L);
//
//        // Assert
//        assertFalse(result);
//        verify(movieRepository, times(1)).existsById(1L);
//        verify(movieRepository, never()).deleteById(1L);
//    }
//
//    @Test
//    void testUpdateMovie_Success() {
//        // Arrange
//        Movie existingMovie = new Movie(1L, "Old Title", "Old Director", "Old Genre", 5.0, 2000);
//        Movie updatedMovie = new Movie(null, "New Title", "New Director", "New Genre", 8.5, 2023);
//        when(movieRepository.findById(1L)).thenReturn(Optional.of(existingMovie));
//        when(movieRepository.save(any(Movie.class))).thenReturn(existingMovie);
//
//        // Act
//        boolean result = movieService.updateMovie(1L, updatedMovie);
//
//        // Assert
//        assertTrue(result);
//        assertEquals("New Title", existingMovie.getTitle());
//        verify(movieRepository, times(1)).findById(1L);
//        verify(movieRepository, times(1)).save(existingMovie);
//    }
//
//    @Test
//    void testUpdateMovie_NotFound() {
//        // Arrange
//        when(movieRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Act
//        boolean result = movieService.updateMovie(1L, new Movie());
//
//        // Assert
//        assertFalse(result);
//        verify(movieRepository, times(1)).findById(1L);
//        verify(movieRepository, never()).save(any(Movie.class));
//    }
//}
