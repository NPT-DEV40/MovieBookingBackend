package com.backend.moviebooking.Controller;

import com.backend.moviebooking.Dtos.MovieDto;
import com.backend.moviebooking.Model.Movie;
import com.backend.moviebooking.Service.Impl.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    //Administration
    @GetMapping("/all-movies")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok().body(movieService.findAll());
    }

    @PostMapping("/add-movie")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> addMovie(@RequestBody MovieDto movieDto) {
        Movie movie = transfer(movieDto);
        movieService.save(movie);
        return ResponseEntity.ok().body("Movie added");
    }

    @PutMapping("/update-movie/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        Movie movie = transfer(movieDto);
        movie.setId(id);
        movieService.save(movie);
        return ResponseEntity.ok().body("Movie updated");
    }

    @PostMapping("/delete-movie/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.ok().body("Movie deleted");
    }

    @GetMapping("/search-movie")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> getMovieByName(@RequestParam String name) {
        return ResponseEntity.ok().body(movieService.findMovieByName(name));
    }

    @PostMapping("/show-movie/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> showMovie(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        movie.setShowing(true);
        movieService.save(movie);
        return ResponseEntity.ok().body("Movie is now showing");
    }

    //User

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getMovieDetails(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.findById(id));
    }

    // Common

    @GetMapping("/movies-showing")
    public ResponseEntity<?> getAllMoviesIsShowing() {
        return ResponseEntity.ok().body(movieService.findAllMoviesShowing());
    }
    private Movie transfer(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setMovieName(movieDto.getName());
        movie.setMovieDescription(movieDto.getDescription());
        movie.setMovieGenre(movieDto.getGenre());
        movie.setMovieLanguage(movieDto.getLanguage());
        movie.setMovieLength(movieDto.getLength());
        movie.setMovieRating(movieDto.getRating());
        movie.setMovieReleaseDate(movieDto.getReleaseDate());
        movie.setMovieTrailer(movieDto.getTrailer());
        movie.setMovieDirector(movieDto.getDirector());
        movie.setMovieImage(movieDto.getImage());
        movie.setShowing(movieDto.isShowing());
        return movie;
    }
}