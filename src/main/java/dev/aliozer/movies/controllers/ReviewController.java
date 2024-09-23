package dev.aliozer.movies.controllers;

import dev.aliozer.movies.models.Review;
import dev.aliozer.movies.request.ReviewUpdateRequest;
import dev.aliozer.movies.services.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload) {
        return  new ResponseEntity<>(reviewService.createReview(payload.get("reviewBody"),payload.get("imdbId")), HttpStatus.CREATED);
    }


    @PutMapping("/{imdbId}")
    public ResponseEntity<?> updateReview(
            @PathVariable String imdbId,
            @RequestBody ReviewUpdateRequest reviewUpdateRequest) {

        ObjectId reviewId = new ObjectId(reviewUpdateRequest.getReviewId());
        String updatedBody = reviewUpdateRequest.getUpdatedReviewBody();

        reviewService.updateReview(imdbId, reviewId, updatedBody);

        return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{imdbId}/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String imdbId, @PathVariable String reviewId) {
        reviewService.deleteReview(imdbId, new ObjectId(reviewId));
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
