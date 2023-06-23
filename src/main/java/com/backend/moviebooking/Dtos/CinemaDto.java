package com.backend.moviebooking.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDto {
    private String cinemaImage;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaPhone;
    private String cinemaCity;
}
