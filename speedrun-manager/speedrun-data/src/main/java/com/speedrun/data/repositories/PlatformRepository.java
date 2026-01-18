package com.speedrun.data.repositories;

import com.speedrun.data.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Optional<Platform> findByExternalId(String externalId);
}