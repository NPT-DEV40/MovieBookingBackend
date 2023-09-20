package com.backend.moviebooking.Controller;

import com.backend.moviebooking.Dtos.RoomDto;
import com.backend.moviebooking.Dtos.SeatDto;
import com.backend.moviebooking.Model.Seat;
import com.backend.moviebooking.Service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seat")
public class SeatController {
    private final ISeatService seatService;

    @PostMapping("/add-seat")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> addSeat(@RequestBody SeatDto seatDto) {
        Seat seat = transfer(seatDto);
        seatService.save(seat);
        return ResponseEntity.ok().body("Added successfully");
    }

    @PutMapping("/update-seat/{seatId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> updateSeat(@RequestBody SeatDto seatDto, @PathVariable String seatId) {
        Seat seat = transfer(seatDto);
        seat.setId(seatId);
        seatService.save(seat);
        return ResponseEntity.ok().body("Updated successfully");
    }

    @PostMapping("/delete-seat/{seatId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> deleteSeat(@PathVariable String seatId) {
        seatService.delete(seatId);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    private Seat transfer(SeatDto seatDto) {
        Seat seat = new Seat();
        seat.setSeatName(seatDto.getSeatName());
        seat.setSeatType(seatDto.getSeatType());
        seat.setSeatPrice(seatDto.getSeatPrice());
        return seat;
    }


}
