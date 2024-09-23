package dev.aliozer.movies.services;

import com.mongodb.client.result.UpdateResult;
import dev.aliozer.movies.models.Movie;
import dev.aliozer.movies.models.Review;
import dev.aliozer.movies.repository.MovieRepository;
import dev.aliozer.movies.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }

    public void updateReview(String imdbId, ObjectId reviewId, String updatedReviewBody) {
        Query query = new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId)
                .and("reviewIds._id").is(reviewId));
        Update update = new Update().set("reviewIds.$.body", updatedReviewBody);
        mongoTemplate.updateFirst(query, update, Movie.class);
    }

    public void deleteReview(String imdbId, ObjectId reviewId) {
        reviewRepository.deleteById(reviewId);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().pull("reviewIds", Query.query(Criteria.where("_id").is(reviewId))))
                .first();
    }
}
