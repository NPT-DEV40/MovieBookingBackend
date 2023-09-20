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
@Document(collection = "cinemas")
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {
    @Id
    private String id;

    private String cinemaImage;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaPhone;
    private String cinemaCity;

    @DBRef
    private List<Movie> movies = new ArrayList<>();
}
