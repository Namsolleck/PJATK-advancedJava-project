package com.speedrun.updater.service;

import com.speedrun.client.SpeedrunClient;
import com.speedrun.client.dto.GameDto;
import com.speedrun.data.model.Game;
import com.speedrun.data.repositories.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeedrunUpdaterService {

    // Definicja loggera - to on odpowiada za zapis do pliku skonfigurowanego w properties
    private static final Logger log = LoggerFactory.getLogger(SpeedrunUpdaterService.class);

    @Autowired
    private SpeedrunClient speedrunClient;

    @Autowired
    private GameRepository gameRepository;

    public void updateGame(String externalId) {
        log.info("Rozpoczynanie aktualizacji gry o ID: {}", externalId);

        GameDto dto = speedrunClient.getGameById(externalId);

        if (dto != null) {
            Game game = gameRepository.findByExternalId(dto.getId())
                    .orElse(new Game());

            game.setExternalId(dto.getId());

            if (dto.getNames() != null) {
                game.setName(dto.getNames().getInternational());
            }

            gameRepository.save(game);
            // Używamy log.info zamiast System.out.println
            log.info("Pomyślnie zaktualizowano grę w bazie: {} (ID: {})", game.getName(), game.getExternalId());
        } else {
            log.error("Nie udało się pobrać danych z API dla ID: {}", externalId);
        }
    }
}