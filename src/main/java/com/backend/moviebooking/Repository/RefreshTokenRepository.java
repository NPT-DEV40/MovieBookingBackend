package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.RefreshToken;
import com.backend.moviebooking.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    String deleteByUser(User user);

    Optional<RefreshToken> findByToken(String token);
}
