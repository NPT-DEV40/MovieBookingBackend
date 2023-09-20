package com.backend.moviebooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;

    private String movieName;

    private String movieDescription;

    private String movieImage;

    private String movieTrailer;

    private Double movieRating;

    private Integer movieLength;

    private String movieReleaseDate;

    private String movieGenre;

    private String movieLanguage;

    private String movieDirector;

    private boolean isShowing = false;
}
