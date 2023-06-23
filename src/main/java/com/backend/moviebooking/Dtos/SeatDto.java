package com.backend.moviebooking.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    private String seatName;

    private String seatType;

    private Integer seatPrice;

    private boolean isBooked = false;
}
