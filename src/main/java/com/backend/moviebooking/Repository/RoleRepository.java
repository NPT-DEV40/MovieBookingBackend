package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.ERole;
import com.backend.moviebooking.Model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
