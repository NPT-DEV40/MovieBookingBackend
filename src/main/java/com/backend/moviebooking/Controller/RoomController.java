package com.backend.moviebooking.Controller;

import com.backend.moviebooking.Dtos.RoomDto;
import com.backend.moviebooking.Model.Room;
import com.backend.moviebooking.Service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room")
public class RoomController {

    private final IRoomService roomService;

    @PostMapping("/add-room")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> addRoom(@RequestBody RoomDto roomDto) {
        Room room = transfer(roomDto);
        roomService.save(room);
        return ResponseEntity.ok().body("Room added successfully");
    }

    @PutMapping("/update-room/{roomId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto, @PathVariable String roomId) {
        Room room = transfer(roomDto);
        room.setId(roomId);
        roomService.save(room);
        return ResponseEntity.ok().body("Room updated successfully");
    }

    @PostMapping("/delete-room/{roomId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> deleteRoom(@PathVariable String roomId) {
        roomService.delete(roomId);
        return ResponseEntity.ok().body("Room deleted successfully");
    }

    private Room transfer(RoomDto roomDto) {
        Room room = new Room();
        room.setRoomCapacity(roomDto.getRoomCapacity());
        room.setRoomImage(roomDto.getRoomImage());
        room.setRoomType(roomDto.getRoomType());
        room.setRoomName(roomDto.getRoomName());
        return room;
    }
}
