package com.backend.moviebooking.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDto {
    private String name;
    private String description;
    private String genre;
    private String language;
    private String director;
    private String releaseDate;
    private String trailer;
    private String image;
    private Double rating;
    private Integer length;
    private boolean isShowing = false;
}
