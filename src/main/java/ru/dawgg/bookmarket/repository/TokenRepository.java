package ru.dawgg.bookmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dawgg.bookmarket.model.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findOneByValue(String value);
}