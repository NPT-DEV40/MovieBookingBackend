package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Dtos.MovieDto;
import com.backend.moviebooking.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //Mod

    //Admin
    @Query("select m from Movie m where m.isShowing = true")
    List<Movie> findAllByIsShowing();
    //User

    //Common
    @Query("select m from Movie m where m.movieName like %?1%")
    List<Movie> findMovieByName(String name);
}
