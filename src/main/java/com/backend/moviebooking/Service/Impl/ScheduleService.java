package com.backend.moviebooking.Service.Impl;

import com.backend.moviebooking.Model.Schedule;
import com.backend.moviebooking.Repository.ScheduleRepository;
import com.backend.moviebooking.Service.IScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public void delete(String id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule findById(String id) {
        return scheduleRepository.findById(id).orElseThrow();
    }


}
