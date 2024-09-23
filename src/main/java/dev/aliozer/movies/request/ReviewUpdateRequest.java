package dev.aliozer.movies.request;

import lombok.Data;

@Data
public class ReviewUpdateRequest {
    private String reviewId;
    private String updatedReviewBody;
}
