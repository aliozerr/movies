package dev.aliozer.movies.response;

import lombok.Data;
import org.bson.types.ObjectId;
@Data
public class ReviewResponse {
    private String id;
    private String body;
}
