package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    //Mod

    //Admin
    @Query(value = "{isShowing: true}")
    List<Movie> findAllByIsShowing();
    //User

    //Common
    List<Movie> findByMovieNameIsLike(String name);
}
