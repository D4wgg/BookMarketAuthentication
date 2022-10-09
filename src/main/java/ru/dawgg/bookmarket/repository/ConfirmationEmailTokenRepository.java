package ru.dawgg.bookmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dawgg.bookmarket.model.ConfirmationEmailToken;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationEmailTokenRepository extends JpaRepository<ConfirmationEmailToken, Long> {
    Optional<ConfirmationEmailToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationEmailToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int setConfirmedAt(String token, LocalDateTime dateTime);
}
