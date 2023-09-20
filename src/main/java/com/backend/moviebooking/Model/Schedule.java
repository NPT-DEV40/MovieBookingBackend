package com.backend.moviebooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "schedule")
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    private String id;

    private String startDate;

    private String startTime;

    private Boolean isEndShowing;

    @DBRef
    private Movie movie;

    @DBRef
    private Cinema cinema;
}
