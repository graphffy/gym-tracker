package com.gym.gymtracker.repository;

import com.gym.gymtracker.model.WorkoutSet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {

    // Решаем N+1: подгружаем тренировку и упражнение сразу
    @Override
    @EntityGraph(attributePaths = {"workout", "exercise"})
    List<WorkoutSet> findAll();

    List<WorkoutSet> findByWorkoutId(Long workoutId);
}
