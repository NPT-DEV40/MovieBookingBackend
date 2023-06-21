package com.backend.moviebooking.Service;

import com.backend.moviebooking.Model.Seat;

import java.util.List;

public interface ISeatService extends GenericService<Seat> {
    List<Seat> getSeatsByScheduleId(String scheduleId);
}
