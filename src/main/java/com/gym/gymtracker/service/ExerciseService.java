package com.gym.gymtracker.service;

import com.gym.gymtracker.dto.ExerciseDto;
import com.gym.gymtracker.model.Category;
import com.gym.gymtracker.model.Exercise;
import com.gym.gymtracker.repository.CategoryRepository;
import com.gym.gymtracker.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;

    public List<ExerciseDto> getAll() {
        return exerciseRepository.findAll().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public ExerciseDto create(ExerciseDto dto) {
        Set<Category> categories = new HashSet<>(
            categoryRepository.findAllById(dto.getCategoryIds())
        );

        Exercise exercise = Exercise.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .categories(categories)
            .build();

        return mapToDto(exerciseRepository.save(exercise));
    }

    private ExerciseDto mapToDto(Exercise exercise) {
        return ExerciseDto.builder()
            .id(exercise.getId())
            .name(exercise.getName())
            .description(exercise.getDescription())
            .categoryIds(exercise.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet()))
            .build();
    }
}
