package com.backend.moviebooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "refreshToken")
public class RefreshToken {
    @Id
    private String id;

    private String token;

    private Instant expiryDate;

    @DBRef
    private User user;
}
