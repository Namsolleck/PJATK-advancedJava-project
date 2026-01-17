package com.speedrun.data.repositories;

import com.speedrun.data.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepository extends JpaRepository<Run, Long> { }