package com.gym.gymtracker.service;

import com.gym.gymtracker.dto.WorkoutDto;
import com.gym.gymtracker.model.User;
import com.gym.gymtracker.model.Workout;
import com.gym.gymtracker.repository.UserRepository;
import com.gym.gymtracker.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public List<WorkoutDto> getAllWorkouts() {
        return workoutRepository.findAll().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public WorkoutDto createWorkout(WorkoutDto dto) {
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Workout workout = Workout.builder()
            .name(dto.getName())
            .workoutDate(dto.getWorkoutDate() != null ? dto.getWorkoutDate() : java.time.LocalDateTime.now())
            .user(user)
            .build();

        return mapToDto(workoutRepository.save(workout));
    }

    private WorkoutDto mapToDto(Workout workout) {
        return WorkoutDto.builder()
            .id(workout.getId())
            .name(workout.getName())
            .workoutDate(workout.getWorkoutDate())
            .userId(workout.getUser().getId())
            .build();
    }
}
