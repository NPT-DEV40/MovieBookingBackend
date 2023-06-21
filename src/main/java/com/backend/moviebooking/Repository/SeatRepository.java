package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends MongoRepository<Seat, String> {
    @Query("{'room.id': ?0}")
    List<Seat> findSeatByRoomId(String roomId);
}
