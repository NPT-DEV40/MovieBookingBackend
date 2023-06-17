package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Dtos.MovieDto;
import com.backend.moviebooking.Model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {

    //Mod

    //Admin
//    List<Movie> findAllByIsShowing();
//    //User
//
//    //Common
//    List<Movie> findMovieByName(String name);
}
