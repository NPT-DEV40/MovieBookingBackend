package com.backend.moviebooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "seats")
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    private String id;

    private String seatName;

    private String seatType;

    private Integer seatPrice;

    private boolean isBooked = false;

    @DBRef
    private Room room;
}
