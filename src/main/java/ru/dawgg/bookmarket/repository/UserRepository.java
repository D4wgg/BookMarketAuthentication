package ru.dawgg.bookmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dawgg.bookmarket.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
}