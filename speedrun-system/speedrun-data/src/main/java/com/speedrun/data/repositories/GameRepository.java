package com.speedrun.data.repositories;

import com.speedrun.data.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GameRepository GameRepository extends JpaRepository<Game, Long{
    Optional<Game>findBtExternalId(String externalId);
}