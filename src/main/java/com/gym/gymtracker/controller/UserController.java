package com.gym.gymtracker.controller;

import com.gym.gymtracker.dto.UserDto;
import com.gym.gymtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Получить всех
    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll();
    }

    // Получить одного по ID
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    // Обычное создание
    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return userService.create(dto);
    }

    // Тот самый тестовый метод для защиты (Транзакции)
    @PostMapping("/transaction-test")
    public UserDto testTransaction(
        @RequestBody UserDto dto,
        @RequestParam boolean error) {
        return userService.createWithFirstWorkout(dto, error);
    }

    // Удаление
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        return userService.update(id, dto);
    }

    @GetMapping("/search")
    public UserDto getByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }

}
