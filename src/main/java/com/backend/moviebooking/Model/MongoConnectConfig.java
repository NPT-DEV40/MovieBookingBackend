package com.backend.moviebooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoConnectConfig {
    private String host;
    private int port;
    private String database;
    private String user;
    private String password;
}
