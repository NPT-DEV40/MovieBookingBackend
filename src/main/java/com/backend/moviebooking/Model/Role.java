package com.backend.moviebooking.Model;

import com.backend.moviebooking.Model.Enum.ERole;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Field("roleId")
    private String id;

    private ERole name;

    public Role(@Nonnull ERole name) {
        this.name = name;
    }
}
