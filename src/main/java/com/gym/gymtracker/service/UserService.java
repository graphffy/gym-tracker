package com.gym.gymtracker.service;

import com.gym.gymtracker.dto.UserDto;
import com.gym.gymtracker.model.User;
import com.gym.gymtracker.model.Workout;
import com.gym.gymtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    // CREATE
    public UserDto create(UserDto dto) {
        User user = User.builder()
            .username(dto.getUsername())
            .email(dto.getEmail())
            .build();
        return convertToDto(userRepository.save(user));
    }

    // READ ALL
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    // READ ONE
    public UserDto findById(Long id) {
        return userRepository.findById(id)
            .map(this::convertToDto)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE
    @Transactional
    public UserDto update(Long id, UserDto dto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        // save() вызывать не обязательно из-за @Transactional, но для ясности можно
        return convertToDto(userRepository.save(user));
    }

    @Transactional
    public UserDto createWithFirstWorkout(UserDto dto, boolean makeError) {
        // 1. Создаем юзера
        User user = User.builder()
            .username(dto.getUsername())
            .email(dto.getEmail())
            .workouts(new ArrayList<>())
            .build();

        // 2. Создаем тренировку и СРАЗУ привязываем к юзеру
        Workout firstWorkout = Workout.builder()
            .name("First Training")
            .workoutDate(LocalDateTime.now()) // Обязательно, если поле в БД NOT NULL
            .user(user)
            .build();

        // Добавляем в список (Cascade сработает)
        user.getWorkouts().add(firstWorkout);

        // Сохраняем (одного save(user) достаточно из-за CascadeType.ALL)
        User savedUser = userRepository.save(user);

        if (makeError) {
            throw new RuntimeException("Демонстрация отката транзакции");
        }

        return convertToDto(savedUser);
    }

    // DELETE
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return convertToDto(user);
    }
}
