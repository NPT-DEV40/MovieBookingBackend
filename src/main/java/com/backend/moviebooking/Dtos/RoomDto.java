package com.backend.moviebooking.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private String roomName;

    private Integer roomCapacity;

    private String roomType;

    private String roomImage;
}
