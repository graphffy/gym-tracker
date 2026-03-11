package com.gym.gymtracker.controller;

import com.gym.gymtracker.dto.ExerciseDto;
import com.gym.gymtracker.service.ExerciseService;
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
@RequestMapping("/api/v1/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseDto> getAll() {
        return exerciseService.findAll();
    }

    @GetMapping("/{id}")
    public ExerciseDto getById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @PostMapping
    public ExerciseDto create(@RequestBody ExerciseDto dto) {
        return exerciseService.create(dto);
    }

    @PutMapping("/{id}")
    public ExerciseDto update(@PathVariable Long id, @RequestBody ExerciseDto dto) {
        return exerciseService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exerciseService.delete(id);
    }
}
