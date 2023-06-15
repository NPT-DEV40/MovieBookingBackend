package com.backend.moviebooking.Service.Impl;

import com.backend.moviebooking.Dtos.MovieDto;
import com.backend.moviebooking.Model.Movie;
import com.backend.moviebooking.Repository.MovieRepository;
import com.backend.moviebooking.Service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow();
    }


    @Override
    public List<Movie> findMovieByName(String name) {
        return movieRepository.findMovieByName(name);
    }

    @Override
    public List<Movie> findAllMoviesShowing() {
        return movieRepository.findAllByIsShowing();
    }
}
