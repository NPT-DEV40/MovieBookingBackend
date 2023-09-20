package com.backend.moviebooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongoAuditing
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
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
