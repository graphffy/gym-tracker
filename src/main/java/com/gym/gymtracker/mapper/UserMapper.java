package com.gym.gymtracker.mapper;

import com.gym.gymtracker.dto.UserDto;
import com.gym.gymtracker.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {

    // Из Entity в DTO (для ответов контроллера)
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .build();
    }

    // Из DTO в Entity (для сохранения в базу)
    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
            .id(dto.getId())
            .username(dto.getUsername())
            .email(dto.getEmail())
            .workouts(new ArrayList<>()) // Важно для работы связей
            .build();
    }
}