package dev.aliozer.movies.repository;

import dev.aliozer.movies.models.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

}
