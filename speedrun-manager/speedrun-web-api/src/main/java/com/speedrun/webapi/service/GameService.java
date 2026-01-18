package com.speedrun.webapi.service;

import com.speedrun.data.SpeedrunDataCatalog;
import com.speedrun.data.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private SpeedrunDataCatalog dataCatalog;

    public List<Game> getAllGames() {
        return dataCatalog.getGames().findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return dataCatalog.getGames().findById(id);
    }
}