package com.gym.gymtracker.repository;

import com.gym.gymtracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Можно добавить поиск по названию для удобства
    Category findByName(String name);
}
