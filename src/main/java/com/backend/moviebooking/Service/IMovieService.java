package com.backend.moviebooking.Service;

import com.backend.moviebooking.Dtos.MovieDto;
import com.backend.moviebooking.Model.Movie;

import java.util.List;

public interface IMovieService extends GenericService<Movie> {
    List<Movie> findByMovieNameIsLike(String name);

    List<Movie> findAllMoviesShowing();
}
