package com.backend.moviebooking.Service;

import com.backend.moviebooking.Model.Room;

import java.util.List;

public interface IRoomService extends GenericService<Room>{
    List<Room> getRoomByMovieAndBranch(String movieId, String branchId, String startDate, String startTime);
}
