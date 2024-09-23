package dev.aliozer.movies.services;

import dev.aliozer.movies.models.Movie;
import dev.aliozer.movies.models.Review;
import dev.aliozer.movies.repository.MovieRepository;
import dev.aliozer.movies.request.MovieCreateRequest;
import dev.aliozer.movies.response.MovieResponse;
import dev.aliozer.movies.response.ReviewResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    public Optional<MovieResponse> findOneMovieByImdbId(String imdbId) {
        Optional<Movie> movie = movieRepository.findMovieByImdbId(imdbId);
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setBackdrops(movie.get().getBackdrops());
        movieResponse.setImdbId(movie.get().getImdbId());
        movieResponse.setId(movie.get().getId().toString());
        movieResponse.setPoster(movie.get().getPoster());
        movieResponse.setGenres(movie.get().getGenres());
        movieResponse.setTitle(movie.get().getTitle());
        movieResponse.setReleaseDate(movie.get().getReleaseDate());
        movieResponse.setTrailerLink(movie.get().getTrailerLink());    // Initialize the reviews list if it's null
        if (movieResponse.getReviews() == null) {
            movieResponse.setReviews(new ArrayList<>());
        }

        for (Review review : movie.get().getReviewIds()) {
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setBody(review.getBody());
            reviewResponse.setId(review.getId().toString());
            movieResponse.getReviews().add(reviewResponse);
        }

        return Optional.of(movieResponse);
    }


    public Movie createOneMovie(MovieCreateRequest movieCreateRequest) {
        Optional<Movie> movie = movieRepository.findMovieByImdbId(movieCreateRequest.getImdbId());
        if (movie.isPresent())
            return null;
        else {
            Movie toSave = new Movie();
            toSave.setBackdrops(movieCreateRequest.getBackdrops());
            toSave.setGenres(movieCreateRequest.getGenres());
            toSave.setPoster(movieCreateRequest.getPoster());
            toSave.setTitle(movieCreateRequest.getTitle());
            toSave.setImdbId(movieCreateRequest.getImdbId());
            toSave.setReleaseDate(movieCreateRequest.getReleaseDate());
            toSave.setTrailerLink(movieCreateRequest.getTrailerLink());
            return movieRepository.save(toSave);
        }
    }

    public void deleteMovieById(String imdbId) {
        Optional<MovieResponse> oneMovieByImdbId = findOneMovieByImdbId(imdbId);
        ObjectId objectId = new ObjectId(oneMovieByImdbId.get().getId());
        movieRepository.deleteById(objectId);
    }
}
