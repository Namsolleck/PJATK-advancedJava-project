package com.speedrun.data.repositories;

import com.speedrun.data.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RunRepository extends JpaRepository<Run, Long> {
    Optional<Run> findByExternalId(String externalId);
}