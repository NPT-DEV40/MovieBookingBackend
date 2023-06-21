package com.backend.moviebooking.Service.Impl;

import com.backend.moviebooking.Model.Movie;
import com.backend.moviebooking.Model.Room;
import com.backend.moviebooking.Repository.RoomRepository;
import com.backend.moviebooking.Service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public void delete(String id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room findById(String id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Room> getRoomByMovieAndBranch(String movieId, String branchId, String startDate, String startTime) {
        Query query = new Query();
        // Query sentence to find room by movie, branch, start date and start time
        query.addCriteria(Criteria.where("room.id").in(
                Query.query(Criteria.where("schedule.movie.id").is(movieId)
                        .and("schedule.branch.id").is(branchId)
                        .and("schedule.startDate").is(startDate)
                        .and("schedule.startTime").is(startTime))
                        .fields().include("schedule.room.id"))); // Only return room id
        return mongoTemplate.find(query, Room.class);
    }
}
