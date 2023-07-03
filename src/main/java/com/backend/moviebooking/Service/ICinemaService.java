package com.backend.moviebooking.Service;

import com.backend.moviebooking.Model.Cinema;
import com.backend.moviebooking.Model.Movie;

import java.util.List;

public interface ICinemaService extends GenericService<Cinema> {
    List<Cinema> findCinemaByCinemaName(String branchName);

//    List<Cinema> getCinemaByCityThatHasMovie(String city, String movieId);

    List<Cinema> getCinemasThatHasMovie(String movieId);

    List<Cinema> getCinemasByCity(String city);

    void addMovieToCinema(String cinemaId, Movie movie);
}
