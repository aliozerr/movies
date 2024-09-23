package dev.aliozer.movies.response;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;
@Data
public class MovieResponse {
    private String id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    // this annotation will cause the database to store only the IDs of the reviews and
    // the reviews will be in the separate collection. This is called manuel reference relationship.
    private List<ReviewResponse> reviews;
}
