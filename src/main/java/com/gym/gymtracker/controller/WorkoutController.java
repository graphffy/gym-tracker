package com.gym.gymtracker.controller;

import com.gym.gymtracker.dto.WorkoutDto;
import com.gym.gymtracker.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public List<WorkoutDto> getAll() {
        return workoutService.getAllWorkouts();
    }

    @PostMapping
    public WorkoutDto create(@RequestBody WorkoutDto dto) {
        return workoutService.createWorkout(dto);
    }
}