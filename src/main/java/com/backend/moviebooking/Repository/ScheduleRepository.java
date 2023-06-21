package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
}
