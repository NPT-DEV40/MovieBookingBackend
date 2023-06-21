package com.backend.moviebooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "branches")
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
    @Id
    @Field("branchId")
    private String id;
    private String branchImage;
    private String branchName;
    private String branchAddress;
    private String branchPhone;
    private String branchCity;
}
