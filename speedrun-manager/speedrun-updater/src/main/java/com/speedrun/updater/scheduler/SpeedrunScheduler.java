package com.speedrun.updater.scheduler;

import com.speedrun.data.repositories.GameRepository;
import com.speedrun.updater.service.SpeedrunUpdaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SpeedrunScheduler {
    private static final Logger log = LoggerFactory.getLogger(SpeedrunScheduler.class);

    @Autowired private GameRepository gameRepository;
    @Autowired private SpeedrunUpdaterService updaterService;

    // Uruchamia się co godzinę (3600000 ms)
    @Scheduled(fixedRate = 3600000)
    public void updateAllTrackedGames() {
        log.info("Sygnał Schedulera: Rozpoczynam cykliczną aktualizację bazy...");
        gameRepository.findAll().forEach(game -> {
            try {
                log.debug("Aktualizacja z automatu dla gry: {}", game.getName());
                updaterService.updateGame(game.getExternalId());
            } catch (Exception e) {
                log.error("Błąd podczas auto-aktualizacji gry {}: {}", game.getExternalId(), e.getMessage());
            }
        });
        log.info("Sygnał Schedulera: Zakończono cykl aktualizacji.");
    }
}