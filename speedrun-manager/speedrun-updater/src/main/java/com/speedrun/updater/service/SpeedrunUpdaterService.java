package com.speedrun.updater.service;

import com.speedrun.client.SpeedrunClient;
import com.speedrun.client.dto.*;
import com.speedrun.data.model.*;
import com.speedrun.data.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SpeedrunUpdaterService {
    private static final Logger log = LoggerFactory.getLogger(SpeedrunUpdaterService.class);

    @Autowired private SpeedrunClient speedrunClient;
    @Autowired private GameRepository gameRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private RunRepository runRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PlatformRepository platformRepository;

    @Transactional
    public void updateGame(String gameExternalId) {
        GameDto gameDto = speedrunClient.getGameById(gameExternalId);
        if (gameDto == null) return;

        Game game = gameRepository.findByExternalId(gameDto.getId()).orElse(new Game());
        game.setExternalId(gameDto.getId());
        game.setName(gameDto.getNames().getInternational());
        game = gameRepository.save(game);

        List<CategoryDto> categories = speedrunClient.getCategoriesByGameId(gameExternalId);
        for (CategoryDto catDto : categories) {
            Category category = categoryRepository.findByExternalId(catDto.getId()).orElse(new Category());
            category.setExternalId(catDto.getId());
            category.setName(catDto.getName());
            category.setGame(game);
            category = categoryRepository.save(category);

            List<RankDto> ranks = speedrunClient.getLeaderboard(game.getExternalId(), category.getExternalId(), 5);
            log.info("Kategoria: {}, znaleziono biegów: {}", category.getName(), ranks.size());

            for (RankDto rank : ranks) {
                saveRun(rank.getRun(), category);
            }
        }
    }

    private void saveRun(RunDto runDto, Category category) {
        if (runDto == null) return;

        // 1. User (obsługa braku ID dla gości)
        User user = null;
        String extUserId = runDto.getFirstPlayerId();
        if (extUserId != null) {
            user = userRepository.findByExternalId(extUserId).orElseGet(() -> {
                UserDto uDto = speedrunClient.getUserById(extUserId);
                User newUser = new User();
                newUser.setExternalId(extUserId);
                newUser.setUsername(uDto != null ? uDto.getNames().getInternational() : "User " + extUserId);
                return userRepository.save(newUser);
            });
        }

        // 2. Platform
        // 2. Platform - Pobieranie prawdziwej nazwy z API
        Platform platform = null;
        String extPlatId = runDto.getPlatformId();
        if (extPlatId != null) {
            platform = platformRepository.findByExternalId(extPlatId).orElseGet(() -> {
                log.info("Pobieranie danych o nowej platformie z API: {}", extPlatId);
                PlatformDto pDto = speedrunClient.getPlatformById(extPlatId);

                Platform newPlat = new Platform();
                newPlat.setExternalId(extPlatId);
                // Jeśli API zwróciło dane, weź nazwę, w przeciwnym razie zostaw ID jako backup
                newPlat.setName(pDto != null ? pDto.getName() : "Platform " + extPlatId);

                return platformRepository.save(newPlat);
            });
        }

        // 3. Run
        Run run = runRepository.findByExternalId(runDto.getId()).orElse(new Run());
        run.setExternalId(runDto.getId());
        run.setTimeSeconds(runDto.getPrimaryTime());
        run.setCategory(category);
        run.setUser(user);
        run.setPlatform(platform);
        runRepository.save(run);
    }
}