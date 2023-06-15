package com.backend.moviebooking.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_description")
    private String movieDescription;

    @Column(name = "movie_image")
    private String movieImage;

    @Column(name = "movie_trailer")
    private String movieTrailer;

    @Column(name = "movie_rating")
    private Double movieRating;

    @Column(name = "movie_length")
    private Integer movieLength;

    @Column(name = "movie_release_date")
    private String movieReleaseDate;

    @Column(name = "movie_genre")
    private String movieGenre;

    @Column(name = "movie_language")
    private String movieLanguage;

    @Column(name = "movie_director")
    private String movieDirector;

    @Column(name = "is_showing")
    private boolean isShowing = false;
}
