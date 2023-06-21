package com.backend.moviebooking.Repository;

import com.backend.moviebooking.Model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    // User
    List<Ticket> getTicketByUserId(String userId); // bill
    // Admin
    @Query("{'ticket.schedule.id': ?0}")
    List<Ticket> findTicketByScheduleId(String scheduleId);
}
