package com.gym.gymtracker.service;

import com.gym.gymtracker.dto.UserDto;
import com.gym.gymtracker.mapper.UserMapper;
import com.gym.gymtracker.model.User;
import com.gym.gymtracker.model.Workout;
import com.gym.gymtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        return userRepository.findById(id)
            .map(userMapper::toDto)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional
    public UserDto create(UserDto dto) {
        User user = userMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public UserDto createWithFirstWorkout(UserDto dto, boolean makeError) {

        User user = userMapper.toEntity(dto);

        Workout firstWorkout = Workout.builder()
            .name("Первая тренировка")
            .workoutDate(LocalDateTime.now())
            .user(user)
            .build();

        user.getWorkouts().add(firstWorkout);

        User savedUser = userRepository.save(user);

        if (makeError) {
            throw new RuntimeException("Ошибка: транзакция откатывается, данные не будут сохранены в БД");
        }

        return userMapper.toDto(savedUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDto update(Long id, UserDto dto) {
        // 1. Ищем существующего юзера
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // 2. Обновляем поля (кроме ID)
        existingUser.setUsername(dto.getUsername());
        existingUser.setEmail(dto.getEmail());

        // 3. Сохраняем. В @Transactional методе вызов save() не всегда обязателен,
        // но это хороший тон для ясности кода.
        User updatedUser = userRepository.save(existingUser);

        // 4. Возвращаем обновленный DTO
        return userMapper.toDto(updatedUser);
    }
}
