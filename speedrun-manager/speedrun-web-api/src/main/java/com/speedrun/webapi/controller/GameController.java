package com.speedrun.webapi.controller;

import com.speedrun.data.model.Game;
import com.speedrun.webapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // Pobiera wszystkie gry zapisane w bazie
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    // Pobiera konkretną grę po jej ID w bazie
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameService.getGameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}