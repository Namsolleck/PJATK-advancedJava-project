package com.speedrun.updater.controller;

import com.speedrun.updater.service.SpeedrunUpdaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdaterController {

    @Autowired
    private SpeedrunUpdaterService updaterService;

    // Przykład: http://localhost:8081/updater/import?gameId=pd0n8p1x
    @GetMapping("/updater/import")
    public String importGame(@RequestParam String gameId) {
        updaterService.updateGame(gameId);
        return "Import zakończony dla ID: " + gameId;
    }
}