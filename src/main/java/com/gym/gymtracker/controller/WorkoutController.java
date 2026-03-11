package com.gym.gymtracker.controller;

import com.gym.gymtracker.dto.WorkoutDto;
import com.gym.gymtracker.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public List<WorkoutDto> getAll() {
        return workoutService.findAll();
    }

    @GetMapping("/{id}")
    public WorkoutDto getById(@PathVariable Long id) {
        return workoutService.findById(id);
    }

    @PostMapping
    public WorkoutDto create(@RequestBody WorkoutDto dto) {
        return workoutService.create(dto);
    }

    @PutMapping("/{id}")
    public WorkoutDto update(@PathVariable Long id, @RequestBody WorkoutDto dto) {
        return workoutService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        workoutService.delete(id);
    }
}
