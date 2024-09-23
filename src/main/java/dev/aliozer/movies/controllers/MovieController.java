package dev.aliozer.movies.controllers;

import dev.aliozer.movies.models.Movie;
import dev.aliozer.movies.request.MovieCreateRequest;
import dev.aliozer.movies.response.MovieResponse;
import dev.aliozer.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private  MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<MovieResponse>> getOneMovie(@PathVariable String imdbId) {
        return new ResponseEntity<>(movieService.findOneMovieByImdbId(imdbId),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createOneMovie(@RequestBody MovieCreateRequest movieCreateRequest) {
        Movie created = movieService.createOneMovie(movieCreateRequest);
        if (created == null)
            return new ResponseEntity<>("The movie already exists",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(created,HttpStatus.CREATED);
    }

    @DeleteMapping("/{imdbId}")
    public ResponseEntity<?> deleteMovieById(@PathVariable String imdbId) {
        movieService.deleteMovieById(imdbId);
        return new ResponseEntity<>("Deleted movie imdbId -" + imdbId,HttpStatus.OK);
    }
}
