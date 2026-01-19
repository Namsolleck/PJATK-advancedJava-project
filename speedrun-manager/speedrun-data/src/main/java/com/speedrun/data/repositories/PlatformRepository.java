package com.speedrun.data.repositories;

import com.speedrun.data.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    java.util.Optional<Platform> findByExternalId(String externalId);

    // Pobiera najpopularniejsze platformy
    @Query("SELECT p.name as name, COUNT(r) as value FROM Run r JOIN r.platform p GROUP BY p.name ORDER BY value DESC")
    List<StatProjection> findMostPopularPlatforms(Pageable pageable);
}