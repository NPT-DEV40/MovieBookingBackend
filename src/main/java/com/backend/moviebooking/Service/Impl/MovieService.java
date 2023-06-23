package com.backend.moviebooking.Service.Impl;

import com.backend.moviebooking.Dtos.MovieDto;
import com.backend.moviebooking.Model.Cinema;
import com.backend.moviebooking.Model.Movie;
import com.backend.moviebooking.Repository.CinemaRepository;
import com.backend.moviebooking.Repository.MovieRepository;
import com.backend.moviebooking.Service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private final MovieRepository movieRepository;

    private final CinemaRepository cinemaRepository;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public void delete(String id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie findById(String id) {
        return movieRepository.findById(id).orElseThrow();
    }


    @Override
    public List<Movie> findByMovieNameIsLike(String name) {
        return movieRepository.findByMovieNameIsLike(name);
    }

    @Override
    public List<Movie> findAllMoviesShowing() {
        return movieRepository.findAllByIsShowing();
    }

    @Override
    public List<Movie> findAllMoviesByCinema(String cinemaId) {
        return cinemaRepository.findById(cinemaId).orElseThrow().getMovies();
    }
}
