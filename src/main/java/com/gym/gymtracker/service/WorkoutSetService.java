package com.gym.gymtracker.service;

import com.gym.gymtracker.dto.WorkoutSetDto;
import com.gym.gymtracker.mapper.WorkoutSetMapper;
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

@Service
@RequiredArgsConstructor
public class WorkoutSetService {

    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutSetMapper workoutSetMapper;

    @Transactional(readOnly = true)
    public List<WorkoutSetDto> findAll() {
        return workoutSetMapper.toDtoList(workoutSetRepository.findAll());
    }

    @Transactional(readOnly = true)
    public WorkoutSetDto findById(Long id) {
        return workoutSetRepository.findById(id)
            .map(workoutSetMapper::toDto)
            .orElseThrow(() -> new RuntimeException("Set not found"));
    }

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

        return workoutSetMapper.toDto(workoutSetRepository.save(workoutSet));
    }

    @Transactional
    public WorkoutSetDto update(Long id, WorkoutSetDto dto) {
        WorkoutSet existingSet = workoutSetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Set not found"));

        existingSet.setWeight(dto.getWeight());
        existingSet.setReps(dto.getReps());

        // 1. Добавляем обновление тренировки (Workout)
        if (dto.getWorkoutId() != null && !dto.getWorkoutId().equals(existingSet.getWorkout().getId())) {
            Workout newWorkout = workoutRepository.findById(dto.getWorkoutId())
                .orElseThrow(() -> new RuntimeException("Workout not found"));
            existingSet.setWorkout(newWorkout);
        }

        // 2. Обновление упражнения (Exercise) - уже должно быть, но проверь
        if (dto.getExerciseId() != null && !dto.getExerciseId().equals(existingSet.getExercise().getId())) {
            Exercise newExercise = exerciseRepository.findById(dto.getExerciseId())
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
            existingSet.setExercise(newExercise);
        }

        return workoutSetMapper.toDto(workoutSetRepository.save(existingSet));
    }

    @Transactional
    public void delete(Long id) {
        workoutSetRepository.deleteById(id);
    }
}
