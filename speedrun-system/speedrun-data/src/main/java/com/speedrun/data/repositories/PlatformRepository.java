package com.speedrun.data.repositories;

import com.speedrun.data.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> { }