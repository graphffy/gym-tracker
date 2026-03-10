package com.gym.gymtracker.repository;

import com.gym.gymtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Метод для @RequestParam (поиск по имени)
    User findByUsername(String username);
}
