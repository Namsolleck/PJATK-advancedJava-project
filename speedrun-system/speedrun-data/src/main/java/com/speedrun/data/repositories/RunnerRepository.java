package com.speedrun.data.repositories;

import com.speedrun.data.model.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<Runner, Long> { }