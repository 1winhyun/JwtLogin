package com.example.jwt_exercise.user.repository;

import com.example.jwt_exercise.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
    boolean existsByName(String name);
}
