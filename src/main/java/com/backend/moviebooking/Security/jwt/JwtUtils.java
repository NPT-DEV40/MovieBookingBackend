package com.backend.moviebooking.Security.jwt;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "movie-booking.app")
public class JwtUtils {
    @Value("${movie-booking.app.jwtSecret}")
    private String jwtSecret;

}
