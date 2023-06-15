package com.backend.moviebooking.Service;

import com.backend.moviebooking.Dtos.MovieDto;
import com.backend.moviebooking.Model.Movie;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMovieService extends GenericService<Movie> {
    List<Movie> findMovieByName(String name);

    List<Movie> findAllMoviesShowing();
}