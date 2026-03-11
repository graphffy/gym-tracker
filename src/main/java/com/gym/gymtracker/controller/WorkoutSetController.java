package com.gym.gymtracker.controller;

import com.gym.gymtracker.dto.WorkoutSetDto;
import com.gym.gymtracker.service.WorkoutSetService;
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
@RequestMapping("/api/v1/sets")
@RequiredArgsConstructor
public class WorkoutSetController {

    private final WorkoutSetService workoutSetService;

    @GetMapping
    public List<WorkoutSetDto> getAll() {
        return workoutSetService.findAll();
    }

    @GetMapping("/{id}")
    public WorkoutSetDto getById(@PathVariable Long id) {
        return workoutSetService.findById(id);
    }

    @PostMapping
    public WorkoutSetDto create(@RequestBody WorkoutSetDto dto) {
        return workoutSetService.create(dto);
    }

    @PutMapping("/{id}")
    public WorkoutSetDto update(@PathVariable Long id, @RequestBody WorkoutSetDto dto) {
        return workoutSetService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        workoutSetService.delete(id);
    }
}
