package com.backend.moviebooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "schedule")
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @Field("scheduleId")
    private String id;

    private String startDate;

    private String endDate;

    private String startTime;

    private double price;

    @DBRef
    private Movie movie;

    @DBRef
    private Branch branch;

    @DBRef
    private Room room;
}
