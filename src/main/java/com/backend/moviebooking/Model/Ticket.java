package com.backend.moviebooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("tickets")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @Field("ticketId")
    private String id;

    @DBRef
    private Schedule schedule;

    @DBRef
    private Seat seat;
}
