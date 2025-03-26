package com.example.movie_api_development.controller;

import com.example.movie_api_development.dao.entity.Movie;
import com.example.movie_api_development.model.dto.request.MovieRequest;
import com.example.movie_api_development.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
@GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id){
        Movie movie = movieService.getById(id);
        return ResponseEntity.ok(movie);
    }
    @GetMapping
    public ResponseEntity<Page<Movie>> getAll(Pageable pageable){
        Page<Movie> movies = movieService.getAll(pageable);
        return ResponseEntity.ok(movies);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Movie>> add(@Valid @RequestBody List<MovieRequest> movie) {
        List<Movie> savedMovie = movieService.add(movie);
        return ResponseEntity.ok(savedMovie);
}
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest movie) {
        movieService.update(id, movie);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
