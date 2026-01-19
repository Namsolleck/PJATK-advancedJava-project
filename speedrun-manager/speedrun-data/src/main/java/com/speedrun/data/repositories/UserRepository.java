package com.speedrun.data.repositories;

import com.speedrun.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    java.util.Optional<User> findByExternalId(String externalId);

    // Pobiera najaktywniejszych graczy
    @Query("SELECT u.username as name, COUNT(r) as value FROM Run r JOIN r.user u GROUP BY u.username ORDER BY value DESC")
    List<StatProjection> findMostActiveUsers(Pageable pageable);
}