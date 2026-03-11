package com.gym.gymtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDto {
    private Long id;
    private String name;
    private LocalDateTime workoutDate;
    private Long userId; // Передаем только ID пользователя
}
