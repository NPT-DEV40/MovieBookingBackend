package com.backend.moviebooking.Service.Impl;

import com.backend.moviebooking.Model.Room;
import com.backend.moviebooking.Model.Seat;
import com.backend.moviebooking.Model.Ticket;
import com.backend.moviebooking.Repository.ScheduleRepository;
import com.backend.moviebooking.Repository.SeatRepository;
import com.backend.moviebooking.Repository.TicketRepository;
import com.backend.moviebooking.Service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService implements ISeatService {

    private final SeatRepository seatRepository;

    private final ScheduleRepository scheduleRepository;

    private final TicketRepository ticketRepository;
    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public void delete(String id) {
        seatRepository.deleteById(id);
    }

    @Override
    public Seat findById(String id) {
        return seatRepository.findById(id).orElse(null);
    }

    @Override
    public List<Seat> getSeatsByScheduleId(String scheduleId) {
        Room room = scheduleRepository.findById(scheduleId).orElseThrow().getRoom();
        List<Seat> seats = seatRepository.findSeatByRoomId(room.getId());

        // Ticket Repository
        List<Seat> seatIsOccupied = ticketRepository.findTicketByScheduleId(scheduleId)
                .stream().map(Ticket::getSeat).toList();

        seats.stream().filter(seatIsOccupied::contains).forEach(seat -> seat.setBooked(true));

        return seats;
    }
}
