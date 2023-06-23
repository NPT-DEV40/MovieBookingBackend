package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends MongoRepository<Cinema, String> {
    List<Cinema> findCinemaByCinemaName(String cinemaName);
    @Query(value = "{ 'cinema.movie.id' : ?0 }")
    List<Cinema> getCinemaByMovieId(String MovieId);

}
