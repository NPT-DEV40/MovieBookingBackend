package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
}
