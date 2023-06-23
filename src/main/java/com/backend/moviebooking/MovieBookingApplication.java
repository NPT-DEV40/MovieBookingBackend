package com.backend.moviebooking;

import com.backend.moviebooking.Model.ERole;
import com.backend.moviebooking.Model.Role;
import com.backend.moviebooking.Repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoAuditing
public class MovieBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieBookingApplication.class, args);
    }


//    @Bean
//    CommandLineRunner runner (RoleRepository repository) {
//        return args -> {
//            Role role = new Role(ERole.ROLE_MODERATOR);
//            repository.insert(role);
//        };
//    }
}
