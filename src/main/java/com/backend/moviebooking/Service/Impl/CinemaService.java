package com.backend.moviebooking.Service.Impl;

import com.backend.moviebooking.Model.Cinema;
import com.backend.moviebooking.Model.Movie;
import com.backend.moviebooking.Repository.CinemaRepository;
import com.backend.moviebooking.Service.ICinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaService implements ICinemaService {

    private final CinemaRepository cinemaRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public void delete(String id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public Cinema findById(String id) {
        return cinemaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cinema> findCinemaByCinemaName(String cinemaName) {
        return cinemaRepository.findCinemaByCinemaName(cinemaName);
    }

    @Override
    public List<Cinema> getCinemaByCityThatHasMovie(String city, String movieId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cinema.id").in(
                Query.query(Criteria.where("schedule.movie.id").is(movieId)
                        .and("schedule.branch.city").is(city))
                        .fields().include("schedule.cinema.id")));
        return mongoTemplate.find(query, Cinema.class);
    }

    @Override
    public List<Cinema> getCinemasThatHasMovie(String movieId) {
        return cinemaRepository.getCinemaByMovieId(movieId);
    }

    @Override
    public void addMovieToCinema(String cinemaId, Movie movie) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElse(null);
        if (cinema != null) {
            cinema.getMovies().add(movie);
            cinemaRepository.save(cinema);
        }
    }


}
