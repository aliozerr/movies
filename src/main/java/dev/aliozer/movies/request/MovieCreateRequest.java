package dev.aliozer.movies.request;

import lombok.Data;

import java.util.List;
@Data
public class MovieCreateRequest {
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
}
