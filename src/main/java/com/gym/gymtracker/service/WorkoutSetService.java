package com.gym.gymtracker.service;

import com.gym.gymtracker.dto.WorkoutSetDto;
import com.gym.gymtracker.model.Exercise;
import com.gym.gymtracker.model.Workout;
import com.gym.gymtracker.model.WorkoutSet;
import com.gym.gymtracker.repository.ExerciseRepository;
import com.gym.gymtracker.repository.WorkoutRepository;
import com.gym.gymtracker.repository.WorkoutSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutSetService {

    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public WorkoutSetDto create(WorkoutSetDto dto) {
        Workout workout = workoutRepository.findById(dto.getWorkoutId())
            .orElseThrow(() -> new RuntimeException("Workout not found"));
        Exercise exercise = exerciseRepository.findById(dto.getExerciseId())
            .orElseThrow(() -> new RuntimeException("Exercise not found"));

        WorkoutSet workoutSet = WorkoutSet.builder()
            .weight(dto.getWeight())
            .reps(dto.getReps())
            .workout(workout)
            .exercise(exercise)
            .build();

        return mapToDto(workoutSetRepository.save(workoutSet));
    }

    public List<WorkoutSetDto> getByWorkout(Long workoutId) {
        return workoutSetRepository.findByWorkoutId(workoutId).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private WorkoutSetDto mapToDto(WorkoutSet workoutSet) {
        return WorkoutSetDto.builder()
            .id(workoutSet.getId())
            .weight(workoutSet.getWeight())
            .reps(workoutSet.getReps())
            .workoutId(workoutSet.getWorkout().getId())
            .exerciseId(workoutSet.getExercise().getId())
            .build();
    }
}
