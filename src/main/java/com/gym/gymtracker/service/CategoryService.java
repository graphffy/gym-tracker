package com.gym.gymtracker.service;

import com.gym.gymtracker.dto.CategoryDto;
import com.gym.gymtracker.model.Category;
import com.gym.gymtracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream()
            .map(category -> CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build())
            .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDto create(CategoryDto dto) {
        Category category = Category.builder()
            .name(dto.getName())
            .build();
        Category saved = categoryRepository.save(category);
        return CategoryDto.builder()
            .id(saved.getId())
            .name(saved.getName())
            .build();
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
