package com.backend.moviebooking.Controller;

import com.backend.moviebooking.Dtos.CinemaDto;
import com.backend.moviebooking.Model.Cinema;
import com.backend.moviebooking.Model.Movie;
import com.backend.moviebooking.Service.ICinemaService;
import com.backend.moviebooking.Service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final ICinemaService cinemaService;

    private final IMovieService movieService;

    // Administration
    @PostMapping("/add-cinema")
    public ResponseEntity<?> addCinema(@RequestBody CinemaDto cinemaDto) {
        Cinema cinema = transfer(cinemaDto);
        return ResponseEntity.ok().body(cinemaService.save(cinema));
    }

    @PostMapping("/delete-cinema/{id}")
    public ResponseEntity<?> deleteCinema(@PathVariable String id) {
        cinemaService.delete(id);
        return ResponseEntity.ok().body("Cinema deleted");
    }

    @PostMapping("/add-movie-to-cinema/{id}")
    public ResponseEntity<?> addMovieToCinema(@PathVariable("id") String cinemaId, @RequestParam("movieId") String movieId) {
        Movie movie = movieService.findById(movieId);
        cinemaService.addMovieToCinema(cinemaId, movie);
        return ResponseEntity.ok().body("Add Successfully");
    }

    @GetMapping("/all-cinemas")
    public ResponseEntity<?> getAllCinemas() {
        return ResponseEntity.ok().body(cinemaService.findAll());
    }


    // User
    @GetMapping("/all-cinemas-has-movie/{id}")
    public ResponseEntity<?> getAllCinemasHasMovie(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(cinemaService.getCinemasThatHasMovie(id));
    }

    // Common
    private Cinema transfer(CinemaDto cinemaDto) {
        Cinema cinema = new Cinema();
        cinema.setCinemaName(cinemaDto.getCinemaName());
        cinema.setCinemaAddress(cinemaDto.getCinemaAddress());
        cinema.setCinemaCity(cinemaDto.getCinemaCity());
        cinema.setCinemaImage(cinemaDto.getCinemaImage());
        cinema.setCinemaPhone(cinemaDto.getCinemaPhone());
        return cinema;
    }
}
