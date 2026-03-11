package com.gym.gymtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSetDto {
    private Long id;
    private Double weight;
    private Integer reps;
    private Long workoutId;
    private Long exerciseId;
}
