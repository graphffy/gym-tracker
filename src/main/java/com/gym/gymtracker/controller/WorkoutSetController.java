package com.gym.gymtracker.controller;

import com.gym.gymtracker.dto.WorkoutSetDto;
import com.gym.gymtracker.service.WorkoutSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sets")
@RequiredArgsConstructor
public class WorkoutSetController {

    private final WorkoutSetService workoutSetService;

    @PostMapping
    public WorkoutSetDto create(@RequestBody WorkoutSetDto dto) {
        return workoutSetService.create(dto);
    }

    @GetMapping("/workout/{workoutId}")
    public List<WorkoutSetDto> getByWorkout(@PathVariable Long workoutId) {
        return workoutSetService.getByWorkout(workoutId);
    }
}
