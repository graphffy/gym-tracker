package com.gym.gymtracker.repository;

import com.gym.gymtracker.model.Workout;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    // Решаем проблему N+1: запрашиваем тренировки сразу вместе с данными юзера одним JOIN
    @Override
    @EntityGraph(attributePaths = {"user"})
    List<Workout> findAll();

    // Поиск всех тренировок конкретного пользователя
    List<Workout> findByUserId(Long userId);
}
