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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/search/jpql")
    public Page<WorkoutSetDto> searchByUserAndExerciseJpql(
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String exerciseName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return workoutSetService.searchByUserAndExerciseJpql(username, exerciseName, page, size);
    }

    @GetMapping("/search/native")
    public Page<WorkoutSetDto> searchByUserAndExerciseNative(
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String exerciseName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return workoutSetService.searchByUserAndExerciseNative(username, exerciseName, page, size);
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
